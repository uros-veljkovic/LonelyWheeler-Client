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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.db.entity.liked.LikedOfferEntity
import project.lonelywheeler.db.entity.offfer.OfferEntity
import project.lonelywheeler.db.entity.offfer.equipment.EquipmentEntity
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.di.viewmodel.EquipmentResponse
import project.lonelywheeler.di.viewmodel.SellerResponse
import project.lonelywheeler.model.domain.offer.equipment.Equipment
import project.lonelywheeler.model.domain.offer.equipment.toEntity
import project.lonelywheeler.util.string.MyStringUtils
import project.lonelywheeler.util.constants.RESOLUTION_1080X768
import project.lonelywheeler.util.extensions.compressTo
import project.lonelywheeler.util.extensions.decrease
import project.lonelywheeler.util.extensions.increase


//TODO: Kreiraj EquipmentViewModelModule.kt kako bi injectovao sve zavisnosti
class EquipmentViewModel
@ViewModelInject
constructor(
    var equipment: Equipment,
    val repository: Repository,
    @EquipmentResponse
    val responseEntity: MutableLiveData<MyResponse<EquipmentEntity>>,
    @SellerResponse
    val responseSeller: MutableLiveData<MyResponse<UserEntity>>
) : ViewModel() {

    private val TAG = "EquipmentViewModel"
    var currentPictureIndex: ObservableInt = ObservableInt(-1)
    var lastPictureIndex: ObservableInt = ObservableInt(-1)
    var likeTriggered: ObservableBoolean = ObservableBoolean(false)
    var displayedPicture: MutableLiveData<Bitmap?> = MutableLiveData(null)

    fun persist() {
        equipment.sellerId = MyApplication.currentUser?.id
        CoroutineScope(Dispatchers.IO).launch {
            responseEntity.postValue(
                repository.create(equipment.toEntity())
            )
        }
    }

    fun readOffer(offerId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            responseEntity.postValue(
                repository.readEquipment(offerId)
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
        return equipment.id.isNullOrEmpty()
    }

    fun getIndexOfCurrentPicture(): Int {
        return currentPictureIndex.get()
    }

    fun removePicture() {
        equipment.pictures.also { pictures ->
            if (pictures.lastIndex == getIndexOfCurrentPicture()) {
                if (pictures.size == 1) {
                    displayedPicture.value = null
                } else {
                    displayedPicture.value = pictures[getIndexOfCurrentPicture() - 1]
                }
                currentPictureIndex.decrease()

            } else {
                displayedPicture.value = pictures[getIndexOfCurrentPicture() + 1]
            }
            lastPictureIndex.decrease()
            pictures.removeAt(getIndexOfCurrentPicture())
        }
    }

    fun resetIndexes() {
        currentPictureIndex.set(-1)
        lastPictureIndex.set(-1)
    }

    fun getCurrentPicture(): Bitmap? =
        if (getIndexOfCurrentPicture() == -1)
            null
        else
            equipment.pictures[getIndexOfCurrentPicture()]

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
        if (isNewVehicle()) {
            equipment.pictures.add(picture.compressTo(RESOLUTION_1080X768))
        } else {
            equipment.pictures.add(picture)
        }

        currentPictureIndex.increase()
        lastPictureIndex.increase()
    }

    fun sellerHasMobileNumber(): Boolean =
        responseSeller.value?.entity?.personalInfoEntity?.mobileNumber.isNullOrEmpty()

    fun getMobileNumber(): String =
        responseSeller.value!!.entity!!.personalInfoEntity.mobileNumber

    fun triggerLike() {
        val likedOffer = LikedOfferEntity(
            MyApplication.currentUserId!!,
            responseEntity.getId()
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
                    MyApplication.currentUserId!!,
                    offerId
                ).entity!!
            )
            Log.d(TAG, "readIfOfferLiked: ${likeTriggered.get()}")
        }
    }

}

fun <T : OfferEntity> MutableLiveData<MyResponse<T>>.getId(): String {
    return this.value!!.entity!!._id!!
}

fun <T : OfferEntity> MutableLiveData<MyResponse<T>>.getSellerId(): String {
    return this.value!!.entity!!.sellerId!!
}