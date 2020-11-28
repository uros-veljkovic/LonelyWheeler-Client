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
import project.lonelywheeler.db.entity.offer.vehicle.pedestrian.PedestrianVehicleEntity
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.di.viewmodel.PedestrianVehicleResponse
import project.lonelywheeler.di.viewmodel.SellerResponse
import project.lonelywheeler.model.observable.offer.vehicle.pedestrian.PedestrianVehicleObservable
import project.lonelywheeler.util.compressTo
import project.lonelywheeler.util.constants.RESOLUTION_1080X768
import project.lonelywheeler.util.extensions.decrease
import project.lonelywheeler.util.extensions.increase
import project.lonelywheeler.util.string.MyStringUtils


//TODO: Kreiraj ViewModelEquipmentModule.kt kako bi injectovao sve zavisnosti
class ViewModelPedestrianVehicle
@ViewModelInject
constructor(
    var vehicle: PedestrianVehicleObservable,
    val repository: Repository,
    @PedestrianVehicleResponse
    var responseEntity: MutableLiveData<MyResponse<PedestrianVehicleEntity>>,
    @SellerResponse
    var responseSeller: MutableLiveData<MyResponse<UserEntity>>,
) : ViewModel() {


    var likeTriggered: ObservableBoolean = ObservableBoolean(false)

    var currentPictureIndex: ObservableInt = ObservableInt(-1)
    var lastPictureIndex: ObservableInt = ObservableInt(-1)

    fun persist() {
        vehicle.sellerId = MyApplication.currentUser?.id
        CoroutineScope(Dispatchers.IO).launch {
            responseEntity.postValue(
                repository.createOrUpdate(vehicle.toEntity())
            )
        }
    }

    suspend fun readOffer(offerId: String) {
//        CoroutineScope(Dispatchers.IO).async {
        responseEntity.postValue(
            repository.readPedestrianVehicle(offerId)
        )
//        }
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
        vehicle.pictures.apply {
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
        vehicle.addPicture(picture.compressTo(RESOLUTION_1080X768))

        val lastIndex = vehicle.pictures.lastIndex

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
        vehicle = PedestrianVehicleObservable()
        resetIndexes()
        resetResponses()
    }

    fun update() {
        vehicle = responseEntity.value?.entity?.toObservable() ?: PedestrianVehicleObservable()
        if (vehicle.pictures.size >= 0) {
            currentPictureIndex.set(0)
            lastPictureIndex.set(vehicle.pictures.lastIndex)
        } else {
            resetIndexes()
        }
        vehicle.notifyChange()
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