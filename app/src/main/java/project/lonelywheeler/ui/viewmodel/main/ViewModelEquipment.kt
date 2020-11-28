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
import project.lonelywheeler.db.entity.offer.equipment.EquipmentEntity
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.di.viewmodel.MyResponseEquipment
import project.lonelywheeler.di.viewmodel.SellerResponse
import project.lonelywheeler.model.observable.offer.equipment.EquipmentObservable
import project.lonelywheeler.util.compressTo
import project.lonelywheeler.util.constants.RESOLUTION_1080X768
import project.lonelywheeler.util.extensions.decrease
import project.lonelywheeler.util.extensions.increase
import project.lonelywheeler.util.string.MyStringUtils


class ViewModelEquipment
@ViewModelInject
constructor(
    var equipment: EquipmentObservable,
    val repository: Repository,
    @MyResponseEquipment
    var responseEntity: MutableLiveData<MyResponse<EquipmentEntity>>,
    @SellerResponse
    var responseSeller: MutableLiveData<MyResponse<UserEntity>>,
) : ViewModel() {

    var likeTriggered: ObservableBoolean = ObservableBoolean(false)

    var currentPictureIndex: ObservableInt = ObservableInt(-1)
    var lastPictureIndex: ObservableInt = ObservableInt(-1)

    fun persist() {
        if (equipment.sellerId.isNullOrEmpty()) {
            equipment.sellerId = MyApplication.currentUser?.id
        }
        CoroutineScope(Dispatchers.IO).launch {
            responseEntity.postValue(
                repository.createOrUpdate(equipment.toEntity())
            )
        }
    }

    suspend fun readOffer(offerId: String) {
//        CoroutineScope(Dispatchers.IO).async {
        responseEntity.postValue(
            repository.readEquipment(offerId)
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
        equipment.pictures.apply {
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
        equipment.addPicture(picture.compressTo(RESOLUTION_1080X768))

        val lastIndex = equipment.pictures.lastIndex

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
        equipment = EquipmentObservable()
        resetIndexes()
        resetResponses()
    }

    fun update() {
        equipment = responseEntity.value?.entity?.toObservable() ?: EquipmentObservable()
        if (equipment.pictures.size >= 0) {
            currentPictureIndex.set(0)
            lastPictureIndex.set(equipment.pictures.lastIndex)
        } else {
            resetIndexes()
        }
        equipment.notifyChange()
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
