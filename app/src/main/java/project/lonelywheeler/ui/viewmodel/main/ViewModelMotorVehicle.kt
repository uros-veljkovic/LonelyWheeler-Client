package project.lonelywheeler.ui.viewmodel.main

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.db.entity.liked.LikedOfferEntity
import project.lonelywheeler.db.entity.offer.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.di.viewmodel.MyResponseListMotorVehicle
import project.lonelywheeler.di.viewmodel.SellerResponse
import project.lonelywheeler.model.observable.offer.vehicle.motor.MotorVehicleObservable
import project.lonelywheeler.util.compressTo
import project.lonelywheeler.util.constants.RESOLUTION_1080X768
import project.lonelywheeler.util.extensions.decrease
import project.lonelywheeler.util.extensions.increase
import project.lonelywheeler.util.string.MyStringUtils

class MotorVehicleViewModel
@ViewModelInject
constructor(
    var motorVehicle: MutableLiveData<MotorVehicleObservable>,
    val repository: Repository,
    @MyResponseListMotorVehicle
    val responseEntity: MutableLiveData<MyResponse<MotorVehicleEntity>>,
    @SellerResponse
    val responseSeller: MutableLiveData<MyResponse<UserEntity>>,
) : ViewModel() {

    val TAG = "MotorVehicleViewModel"
    var likeTriggered: ObservableBoolean = ObservableBoolean(false)

    var displayedPicture: MutableLiveData<Bitmap?> = MutableLiveData()
    var currentPictureIndex: ObservableInt = ObservableInt(-1)
    var lastPictureIndex: ObservableInt = ObservableInt(-1)


    fun persist() {
        motorVehicle.value!!.sellerId = MyApplication.currentUser?.id
        CoroutineScope(Dispatchers.IO).launch {
            responseEntity.postValue(
                repository.createOrUpdate(motorVehicle.value!!.toEntity())
            )
        }
    }

    fun readOffer(offerId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            responseEntity.postValue(
                repository.readMotorVehicle(offerId)
            )
        }
    }


    fun readSeller(sellerId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            responseSeller.postValue(
                repository.readSeller(sellerId)
            )
        }
    }

    fun isNewVehicle(): Boolean {
        return motorVehicle.value!!.id.isNullOrEmpty()
    }

    fun getIndexOfCurrentPicture(): Int {
        return currentPictureIndex.get()
    }


    fun getCurrentPicture(): Bitmap? =
        if (
            getIndexOfCurrentPicture() == -1 ||
            getIndexOfCurrentPicture() > motorVehicle.value!!.pictures.size - 1
        )
            null
        else
            motorVehicle.value!!.pictures[getIndexOfCurrentPicture()]

    fun removePicture() {
        motorVehicle.value!!.pictures.also { pictures ->
            if (pictures.lastIndex == getIndexOfCurrentPicture()) { //0
                if (pictures.size == 1) {
                    displayedPicture.value = null
                } else {
                    displayedPicture.value = pictures[getIndexOfCurrentPicture() - 1]
                }
                pictures.removeAt(getIndexOfCurrentPicture()) // 0
                currentPictureIndex.decrease()
                lastPictureIndex.decrease()
            } else {
                displayedPicture.value = pictures[getIndexOfCurrentPicture() + 1]
                lastPictureIndex.decrease()
                pictures.removeAt(getIndexOfCurrentPicture()) // 0
            }
        }

    }

    fun resetIndexes() {
        currentPictureIndex.set(-1)
        lastPictureIndex.set(-1)
    }


    fun getCurrentPictureFromEntity(): BitmapDrawable? {
        return responseEntity.value?.entity?.pictures?.get(getIndexOfCurrentPicture())
            ?.let { pictureString ->
                MyStringUtils.convertToBitmap(
                    pictureString
                )
            }
    }

    fun nextPicture() {
        currentPictureIndex.increase()
        displayedPicture.value = getCurrentPicture()
    }

    fun previousPicture() {
        currentPictureIndex.decrease()
        displayedPicture.value = getCurrentPicture()
    }

    fun attachPicture(picture: Bitmap) {
        motorVehicle.value!!.pictures.also { pictures ->
            if (isNewVehicle()) {
                pictures.add(picture.compressTo(RESOLUTION_1080X768))
            } else {
                pictures.add(picture)
            }
            val lastIndex = pictures.lastIndex
            displayedPicture.value = pictures[lastIndex]

            currentPictureIndex.set(lastIndex)
            lastPictureIndex.set(lastIndex)
        }

    }

    fun sellerHasMobileNumber(): Boolean =
        responseSeller.value?.entity?.personalInfoEntity?.mobileNumber.isNullOrEmpty()

    fun getMobileNumber(): String =
        responseSeller.value!!.entity!!.personalInfoEntity.mobileNumber

    fun like(offerId: String, sellerId: String) {
        val likedOffer = LikedOfferEntity(
            MyApplication.getCurrentUserID(),
            offerId,
            sellerId
        )
        CoroutineScope(Dispatchers.IO).launch {
            repository.createOrDelete(likedOffer)
        }
        likeTriggered.changeValue()
    }

    fun readIfOfferLiked(offerId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            likeTriggered.set(
                repository.hasUserLikedOffer(
                    MyApplication.getCurrentUserID(),
                    offerId
                ).entity!!
            )
        }
    }

    fun setUp() {
        currentPictureIndex.set(-1)
        lastPictureIndex.set(-1)
    }
}

fun ObservableBoolean.changeValue() {
    this.set(!this.get())
}
