package project.lonelywheeler.db.repo

import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.service.*
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

    fun signUp(userEntity: UserEntity, authListener: AuthListener) {
        userService.signUpUser(userEntity, authListener)
    }

    fun signIn(userEntity: UserEntity, authListener: AuthListener) {
        userService.signInUser(userEntity, authListener)
    }


}