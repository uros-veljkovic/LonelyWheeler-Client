package project.lonelywheeler.ui.viewmodel.main

import android.graphics.Bitmap
import androidx.databinding.ObservableInt
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.db.entity.product.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.di.viewmodel.MotorVehicleResponse
import project.lonelywheeler.di.viewmodel.SellerResponse
import project.lonelywheeler.model.domain.product.vehicle.motor.MotorVehicle
import project.lonelywheeler.model.domain.product.vehicle.motor.toEntity

class MotorVehicleViewModel
@ViewModelInject
constructor(
    var motorVehicle: MotorVehicle,
    val repository: Repository,
    @MotorVehicleResponse
    val responseOffer: MutableLiveData<MyResponse<MotorVehicleEntity>>,
    @SellerResponse
    val responseSeller: MutableLiveData<MyResponse<UserEntity>>
) : ViewModel() {

    var currentPictureIndex: ObservableInt = ObservableInt(-1)
    var lastPictureIndex: ObservableInt = ObservableInt(-1)

    fun persist() {
        motorVehicle.sellerId = MyApplication.currentUser?.id
        CoroutineScope(IO).launch {
            responseOffer.postValue(
                repository.create(motorVehicle.toEntity())
            )
        }
    }

    fun readOffer(offerId: String) {
        CoroutineScope(IO).launch {
            responseOffer.postValue(
                repository.readMotorVehicle(offerId)
            )
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


}
