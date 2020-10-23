package project.lonelywheeler.db.repo

import project.lonelywheeler.db.entity.product.ProductEntity
import project.lonelywheeler.db.entity.product.equipment.EquipmentEntity
import project.lonelywheeler.db.entity.product.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.entity.product.vehicle.pedestrian.PedestrianVehicleEntity
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.db.service.EquipmentService
import project.lonelywheeler.db.service.MotorVehicleService
import project.lonelywheeler.db.service.PedestrianVehicleService
import project.lonelywheeler.db.service.UserService
import project.lonelywheeler.ui.viewmodel.auth.AuthListener
import project.lonelywheeler.util.constants.ENTITY_TYPE_MOTOR_VEHICLE
import project.lonelywheeler.util.constants.ENTITY_TYPE_PEDESTRIAN_VEHICLE
import javax.inject.Inject

class Repository
@Inject
constructor(
    private val userService: UserService,
    private val motorVehicleService: MotorVehicleService,
    private val pedestrianVehicleService: PedestrianVehicleService,
    private val equipmentService: EquipmentService,
) {

    fun signUp(userEntity: UserEntity, authListener: AuthListener) {
        userService.signUpUser(userEntity, authListener)
    }

    fun signIn(userEntity: UserEntity, authListener: AuthListener) {
        userService.signInUser(userEntity, authListener)
    }

    suspend fun create(entity: MotorVehicleEntity): MyResponse<MotorVehicleEntity> {
        return motorVehicleService.create(entity)
    }

    suspend fun create(entity: EquipmentEntity): MyResponse<EquipmentEntity> {
        return equipmentService.create(entity)
    }

    suspend fun create(entity: PedestrianVehicleEntity): MyResponse<PedestrianVehicleEntity> {
        return pedestrianVehicleService.create(entity)
    }

    suspend fun readAll(entityTypeId: Int): MyResponse<List<ProductEntity>> {
        return when (entityTypeId) {
            ENTITY_TYPE_MOTOR_VEHICLE -> {
                motorVehicleService.readAll()
            }
            ENTITY_TYPE_PEDESTRIAN_VEHICLE -> {
                pedestrianVehicleService.readAll()
            }
            else -> {
                equipmentService.readAll()
            }
        }
    }

    suspend fun readSeller(sellerId: String): MyResponse<UserEntity> {
        return userService.read(sellerId)
    }

    suspend fun readMotorVehicle(offerId: String): MyResponse<MotorVehicleEntity> {
        return motorVehicleService.read(offerId)
    }


}