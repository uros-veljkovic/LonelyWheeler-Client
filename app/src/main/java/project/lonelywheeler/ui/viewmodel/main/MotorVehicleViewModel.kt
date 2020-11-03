package project.lonelywheeler.ui.viewmodel.main

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.db.entity.liked.LikedOfferEntity
import project.lonelywheeler.db.entity.offfer.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.di.viewmodel.MotorVehicleResponse
import project.lonelywheeler.di.viewmodel.SellerResponse
import project.lonelywheeler.model.domain.offer.vehicle.motor.MotorVehicle
import project.lonelywheeler.model.domain.offer.vehicle.motor.toEntity
import project.lonelywheeler.util.string.MyStringUtils

class MotorVehicleViewModel
@ViewModelInject
constructor(
    var motorVehicle: MotorVehicle,
    val repository: Repository,
    @MotorVehicleResponse
    val responseEntity: MutableLiveData<MyResponse<MotorVehicleEntity>>,
    @SellerResponse
    val responseSeller: MutableLiveData<MyResponse<UserEntity>>
) : ViewModel() {

    val TAG = "MotorVehicleViewModel"
    var currentPictureIndex: ObservableInt = ObservableInt(-1)
    var lastPictureIndex: ObservableInt = ObservableInt(-1)
    var likeTriggered: ObservableBoolean = ObservableBoolean(false)

    fun persist() {
        motorVehicle.sellerId = MyApplication.currentUser?.id
        CoroutineScope(IO).launch {
            responseEntity.postValue(
                repository.create(motorVehicle.toEntity())
            )
        }
    }

    fun readOffer(offerId: String) {
        CoroutineScope(IO).launch {
            launch {
                responseEntity.postValue(
                    repository.readMotorVehicle(offerId)
                )
            }
        }
    }

    fun readIfOfferLiked(offerId: String) {
        CoroutineScope(IO).launch {

            val isLikedOffer = repository.hasUserLikedOffer(
                MyApplication.currentUserId!!,
                offerId
            ).entity!!

            likeTriggered.set(isLikedOffer)
            Log.d(TAG, "readIfOfferLiked: ${likeTriggered.get()}")
        }
    }

    fun readSeller(sellerId: String) {
        CoroutineScope(IO).launch {
            responseSeller.postValue(
                repository.readSeller(sellerId)
            )
        }
    }

    fun isNewVehicle(): Boolean {
        return motorVehicle.id.isNullOrEmpty()
    }

    fun getIndexOfCurrentPicture(): Int {
        return currentPictureIndex.get()
    }

    fun getCurrentPicture(): Bitmap? =
        if (getIndexOfCurrentPicture() == -1)
            null
        else
            motorVehicle.pictures[getIndexOfCurrentPicture()]

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

    fun sellerHasMobileNumber(): Boolean =
        responseSeller.value?.entity?.personalInfoEntity?.mobileNumber.isNullOrEmpty()

    fun getMobileNumber(): String =
        responseSeller.value!!.entity!!.personalInfoEntity.mobileNumber

    fun like() {
        val likedOffer = LikedOfferEntity(
            MyApplication.currentUserId!!,
            responseEntity.getId()
        )
        CoroutineScope(IO).launch {
            repository.createOrDelete(likedOffer)
        }
        likeTriggered.changeValue()
    }

}

fun ObservableBoolean.changeValue() {
    this.set(!this.get())
}
