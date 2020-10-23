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
import project.lonelywheeler.db.entity.product.vehicle.pedestrian.PedestrianVehicleEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.di.viewmodel.Equipment_Response
import project.lonelywheeler.di.viewmodel.PedestrianVehicleResponse
import project.lonelywheeler.model.domain.product.vehicle.pedestrian.PedestrianVehicle
import project.lonelywheeler.model.domain.product.vehicle.pedestrian.toEntity


//TODO: Kreiraj EquipmentViewModelModule.kt kako bi injectovao sve zavisnosti
class PedestrianVehicleViewModel
@ViewModelInject
constructor(
    var vehicle: PedestrianVehicle,
    val repository: Repository,
    @PedestrianVehicleResponse
    val response: MutableLiveData<MyResponse<PedestrianVehicleEntity>>,
) : ViewModel() {

    var currentPictureIndex: ObservableInt = ObservableInt(-1)
    var lastPictureIndex: ObservableInt = ObservableInt(-1)

    fun persist() {
        vehicle.id = MyApplication.currentUser?.id
        CoroutineScope(Dispatchers.IO).launch {
            response.postValue(
                repository.create(vehicle.toEntity())
            )
        }
    }

    fun isNewVehicle(): Boolean {
        return vehicle.id.isNullOrEmpty()
    }

    fun getIndexOfCurrentPicture(): Int {
        return currentPictureIndex.get()
    }

    fun getCurrentPicture(): Bitmap? =
        if (getIndexOfCurrentPicture() == -1)
            null
        else
            vehicle.pictures[getIndexOfCurrentPicture()]


}