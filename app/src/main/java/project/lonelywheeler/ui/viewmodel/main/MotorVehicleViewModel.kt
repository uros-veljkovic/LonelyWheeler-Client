package project.lonelywheeler.ui.viewmodel.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
    val response: MutableLiveData<MyResponse<MotorVehicleEntity>>
) : ViewModel() {

    fun printObject(){
        println(motorVehicle.toString())
        val entity = motorVehicle.toEntity()
        println(entity)
    }

}
