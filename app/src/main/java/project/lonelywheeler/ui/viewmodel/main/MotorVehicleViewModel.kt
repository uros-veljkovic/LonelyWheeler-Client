package project.lonelywheeler.ui.viewmodel.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import project.lonelywheeler.db.entity.product.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.model.domain.product.vehicle.motor.MotorVehicle

class MotorVehicleViewModel
@ViewModelInject
constructor(
    val motorVehicle: MotorVehicle,
    val repository: Repository,
    val response: MutableLiveData<MyResponse<MotorVehicleEntity>>
) : ViewModel() {
}
