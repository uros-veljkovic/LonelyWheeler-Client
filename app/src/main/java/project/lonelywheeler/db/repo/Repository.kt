package project.lonelywheeler.db.repo

import project.lonelywheeler.db.entity.liked.LikedOfferEntity
import project.lonelywheeler.db.entity.offfer.OfferEntity
import project.lonelywheeler.db.entity.offfer.equipment.EquipmentEntity
import project.lonelywheeler.db.entity.offfer.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.entity.offfer.vehicle.pedestrian.PedestrianVehicleEntity
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.db.service.*
import project.lonelywheeler.db.service.api.OfferService
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
    private val offerService: OfferService,
    private val favoriteOfferService: FavoriteOfferService,
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

    suspend fun createOrDelete(entity: LikedOfferEntity): MyResponse<LikedOfferEntity> {
        return favoriteOfferService.createOrDelete(entity)
    }

    suspend fun readSpecificTypeOffers(entityTypeId: Int): MyResponse<List<OfferEntity>> {
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

    suspend fun readOffers(sellerId: String): MyResponse<MutableList<OfferEntity>> {
        return offerService.readAll(sellerId)
    }

    suspend fun readSeller(sellerId: String): MyResponse<UserEntity> {
        return userService.read(sellerId)
    }

    suspend fun readMotorVehicle(offerId: String): MyResponse<MotorVehicleEntity> {
        return motorVehicleService.read(offerId)
    }

    suspend fun readEquipment(offerId: String): MyResponse<EquipmentEntity> {
        return equipmentService.read(offerId)
    }

    suspend fun readPedestrianVehicle(offerId: String): MyResponse<PedestrianVehicleEntity> {
        return pedestrianVehicleService.read(offerId)
    }

    suspend fun readLikedOffers(userId: String): MyResponse<List<LikedOfferEntity>> {
        return favoriteOfferService.readAll(userId)
    }

    suspend fun hasUserLikedOffer(userId: String, offerId: String): MyResponse<Boolean> {
        return favoriteOfferService.read(userId, offerId)
    }



}