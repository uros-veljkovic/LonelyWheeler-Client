package project.lonelywheeler.db.repo

import project.lonelywheeler.db.entity.product.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.db.service.EquipmentService
import project.lonelywheeler.db.service.MotorVehicleService
import project.lonelywheeler.db.service.PedestrianVehicleService
import project.lonelywheeler.db.service.UserService
import project.lonelywheeler.ui.viewmodel.auth.AuthListener
import javax.inject.Inject

class Repository
@Inject
constructor(
    val userService: UserService,
    val motorVehicleService: MotorVehicleService,
    val pedestrianVehicleService: PedestrianVehicleService,
    val equipmentService: EquipmentService
) {

    suspend fun createMotorVehicle(entity: MotorVehicleEntity): MyResponse<MotorVehicleEntity> {
        return motorVehicleService.create(entity)
    }

    fun signUp(userEntity: UserEntity, authListener: AuthListener) {
        userService.signUpUser(userEntity, authListener)
    }

    fun signIn(userEntity: UserEntity, authListener: AuthListener) {
        userService.signInUser(userEntity, authListener)
    }


}