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
import project.lonelywheeler.db.entity.offfer.vehicle.pedestrian.PedestrianVehicleEntity
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.di.viewmodel.PedestrianVehicleResponse
import project.lonelywheeler.di.viewmodel.SellerResponse
import project.lonelywheeler.model.domain.offer.vehicle.pedestrian.PedestrianVehicle
import project.lonelywheeler.model.domain.offer.vehicle.pedestrian.toEntity
import project.lonelywheeler.util.string.MyStringUtils
import project.lonelywheeler.util.constants.RESOLUTION_1080X768
import project.lonelywheeler.util.extensions.compressTo
import project.lonelywheeler.util.extensions.increase


//TODO: Kreiraj EquipmentViewModelModule.kt kako bi injectovao sve zavisnosti
class PedestrianVehicleViewModel
@ViewModelInject
constructor(
    var vehicle: PedestrianVehicle,
    val repository: Repository,
    @PedestrianVehicleResponse
    val responseEntity: MutableLiveData<MyResponse<PedestrianVehicleEntity>>,
    @SellerResponse
    val responseSeller: MutableLiveData<MyResponse<UserEntity>>
) : ViewModel() {

    private val TAG = "PedestrianVehicleViewMo"
    var currentPictureIndex: ObservableInt = ObservableInt(-1)
    var lastPictureIndex: ObservableInt = ObservableInt(-1)
    var likeTriggered: ObservableBoolean = ObservableBoolean(false)

    fun persist() {
        vehicle.sellerId = MyApplication.currentUser?.id
        CoroutineScope(Dispatchers.IO).launch {
            responseEntity.postValue(
                repository.create(vehicle.toEntity())
            )
        }
    }

    fun readOffer(offerId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            responseEntity.postValue(
                repository.readPedestrianVehicle(offerId)
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
        return vehicle.id.isNullOrEmpty()
    }

    fun getIndexOfCurrentPicture(): Int {
        return currentPictureIndex.get()
    }

    fun resetIndexes() {
        currentPictureIndex.set(-1)
        lastPictureIndex.set(-1)
    }

    fun getCurrentPicture(): Bitmap? =
        if (getIndexOfCurrentPicture() == -1)
            null
        else
            vehicle.pictures[getIndexOfCurrentPicture()]

    fun getCurrentPictureFromEntity(): BitmapDrawable? {
        return responseEntity.value?.entity?.pictures?.get(getIndexOfCurrentPicture())
            ?.let { pictureString ->
                MyStringUtils.convertToBitmap(
                    pictureString
                )
            }
    }

    fun attachPicture(picture: Bitmap) {
        if (isNewVehicle()) {
            vehicle.pictures.add(picture.compressTo(RESOLUTION_1080X768))
        } else {
            vehicle.pictures.add(picture)
        }

        currentPictureIndex.increase()
        lastPictureIndex.increase()
    }

    fun sellerHasMobileNumber(): Boolean =
        responseSeller.value?.entity?.personalInfoEntity?.mobileNumber.isNullOrEmpty()

    fun getMobileNumber(): String =
        responseSeller.value!!.entity!!.personalInfoEntity.mobileNumber

    fun readIfOfferLiked(offerId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            likeTriggered.set(
                repository.hasUserLikedOffer(
                    MyApplication.currentUserId!!,
                    offerId
                ).entity!!
            )
        }
    }

    fun like() {
        val offer = LikedOfferEntity(
            MyApplication.currentUserId!!,
            responseEntity.getId()
        )
        CoroutineScope(Dispatchers.IO).launch {
            repository.createOrDelete(offer)
        }
        likeTriggered.changeValue()
    }
}