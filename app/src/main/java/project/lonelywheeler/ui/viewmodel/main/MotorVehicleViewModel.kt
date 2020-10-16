package project.lonelywheeler.ui.viewmodel.main

import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import project.lonelywheeler.db.entity.product.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.model.domain.product.vehicle.motor.MotorVehicle
import project.lonelywheeler.model.domain.product.vehicle.motor.toEntity

class MotorVehicleViewModel
@ViewModelInject
constructor(
    val motorVehicle: MotorVehicle,
    val repository: Repository,
    val response: MutableLiveData<MyResponse<MotorVehicleEntity>>,
) : ViewModel() {

    var currentPictureIndex: ObservableInt = ObservableInt(0)
    var lastPictureIndex: ObservableInt = ObservableInt(-1)

    fun printObject() {
        println(motorVehicle.toString())
        val entity = motorVehicle.toEntity()
        println(entity)
    }

    fun persist() {
        CoroutineScope(IO).launch {
            response.postValue(
                repository.createMotorVehicle(motorVehicle.toEntity())
            )
        }
    }


}
