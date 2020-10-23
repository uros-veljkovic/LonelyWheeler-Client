package project.lonelywheeler.ui.viewmodel.main

import android.graphics.Bitmap
import androidx.databinding.ObservableInt
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.db.entity.product.equipment.EquipmentEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.di.viewmodel.Equipment_Response
import project.lonelywheeler.model.domain.product.equipment.Equipment
import project.lonelywheeler.model.domain.product.equipment.toEntity
import project.lonelywheeler.ui.view.activity.main.fragment.compressTo
import project.lonelywheeler.ui.view.activity.main.fragment.decrease
import project.lonelywheeler.ui.view.activity.main.fragment.increase
import project.lonelywheeler.util.constants.RESOLUTION_1080X768


//TODO: Kreiraj EquipmentViewModelModule.kt kako bi injectovao sve zavisnosti
class EquipmentViewModel
@ViewModelInject
constructor(
    var equipment: Equipment,
    val repository: Repository,
    @Equipment_Response
    val response: MutableLiveData<MyResponse<EquipmentEntity>>,
) : ViewModel() {

    var currentPictureIndex: ObservableInt = ObservableInt(-1)
    var lastPictureIndex: ObservableInt = ObservableInt(-1)
    var displayedPicture: MutableLiveData<Bitmap?> = MutableLiveData(null)

    fun persist() {
        equipment.sellerId = MyApplication.currentUser?.id
        CoroutineScope(Dispatchers.IO).launch {
            response.postValue(
                repository.create(equipment.toEntity())
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
                lastPictureIndex.decrease()
                pictures.removeAt(getIndexOfCurrentPicture())

            } else {
                displayedPicture.value = pictures[getIndexOfCurrentPicture() + 1]
                lastPictureIndex.decrease()
                pictures.removeAt(getIndexOfCurrentPicture())
            }
        }
    }

    fun getCurrentPicture(): Bitmap? =
        if (getIndexOfCurrentPicture() == -1)
            null
        else
            equipment.pictures[getIndexOfCurrentPicture()]

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


}