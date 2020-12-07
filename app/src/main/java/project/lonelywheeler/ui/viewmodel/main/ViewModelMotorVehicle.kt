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
import project.lonelywheeler.db.entity.liked.UserLikingOfferEntity
import project.lonelywheeler.db.entity.offer.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.di.viewmodel.MyResponseMotorVehicle
import project.lonelywheeler.di.viewmodel.SellerResponse
import project.lonelywheeler.model.observable.offer.vehicle.motor.MotorVehicleObservable
import project.lonelywheeler.util.compressTo
import project.lonelywheeler.util.constants.RESOLUTION_1080X768
import project.lonelywheeler.util.extensions.decrease
import project.lonelywheeler.util.extensions.increase
import project.lonelywheeler.util.string.MyStringUtils

class ViewModelMotorVehicle
@ViewModelInject
constructor(
    var motorVehicle: MotorVehicleObservable,
    val repository: Repository,
    @MyResponseMotorVehicle
    var responseEntity: MutableLiveData<MyResponse<MotorVehicleEntity>>,
    @SellerResponse
    var responseSeller: MutableLiveData<MyResponse<UserEntity>>,
) : ViewModel() {

    val TAG = "MotorVehicleViewModel"
    var likeTriggered: ObservableBoolean = ObservableBoolean(false)

    var currentPictureIndex: ObservableInt = ObservableInt(-1)
    var lastPictureIndex: ObservableInt = ObservableInt(-1)

    fun persist() {
        CoroutineScope(Dispatchers.IO).launch {
            val entity = motorVehicle.toEntity()
            responseEntity.postValue(
                repository.createOrUpdate(entity)
            )
        }
    }

    suspend fun readOffer(offerId: String) {
        responseEntity.postValue(
            repository.readMotorVehicle(offerId)
        )
    }

    suspend fun readSeller(sellerId: String) {
        responseSeller.postValue(
            repository.readSeller(sellerId)
        )
    }


    fun getIndexOfCurrentPicture(): Int {
        return currentPictureIndex.get()
    }


    fun removePicture() {
        motorVehicle.pictures.apply {
            if (lastPictureIndex.get() == currentPictureIndex.get()) { //0

                currentPictureIndex.decrease()
                lastPictureIndex.decrease()
                removeLast() // 0
                currentPictureIndex.notifyChange()
            } else {
                removeAt(currentPictureIndex.get())
                lastPictureIndex.decrease()
            }
        }

    }

    fun getCurrentPictureFromEntity(): BitmapDrawable? {
        return responseEntity.value?.entity?.pictures?.get(getIndexOfCurrentPicture())
            ?.let { pictureString ->
                MyStringUtils.convertToBitmap(
                    pictureString
                )
            }
    }

    fun getMobileNumber(): String =
        responseSeller.value!!.entity!!.personalInfoEntity.mobileNumber


    fun nextPicture() {
        currentPictureIndex.increase()
    }

    fun previousPicture() {
        currentPictureIndex.decrease()
    }

    fun attach(picture: Bitmap) {
        motorVehicle.addPicture(picture.compressTo(RESOLUTION_1080X768))

        val lastIndex = motorVehicle.pictures.lastIndex

        currentPictureIndex.set(lastIndex)
        lastPictureIndex.set(lastIndex)
    }

    fun sellerHasMobileNumber(): Boolean =
        responseSeller.value?.entity?.personalInfoEntity?.mobileNumber.isNullOrEmpty()

    fun like(offerId: String, sellerId: String) {
        val likedOffer = UserLikingOfferEntity(
            MyApplication.getCurrentUserID(),
            offerId,
            sellerId
        )
        CoroutineScope(Dispatchers.IO).launch {
            repository.createOrDelete(likedOffer)
        }
        likeTriggered.changeValue()
    }

    suspend fun readIfOfferLiked(offerId: String) {
        likeTriggered.set(
            repository.hasUserLikedOffer(
                MyApplication.getCurrentUserID(),
                offerId
            ).entity!!
        )
    }


    fun reset() {
        resetObservable()
        resetIndexes()
        resetResponses()
    }

    fun resetObservable() {
        motorVehicle = MotorVehicleObservable()
    }

    fun update() {
        motorVehicle = responseEntity.value?.entity?.toObservable() ?: MotorVehicleObservable()
        if (motorVehicle.pictures.size >= 0) {
            currentPictureIndex.set(0)
            lastPictureIndex.set(motorVehicle.pictures.lastIndex)
        } else {
            resetIndexes()
        }
        motorVehicle.notifyChange()
    }

    private fun resetResponses() {
        responseEntity = MutableLiveData()
        responseSeller = MutableLiveData()
    }

    fun resetIndexes() {
        currentPictureIndex.set(-1)
        lastPictureIndex.set(-1)
    }

}

fun ObservableBoolean.changeValue() {
    this.set(!this.get())
}
