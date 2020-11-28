package project.lonelywheeler.db.repo

import project.lonelywheeler.db.entity.liked.SellerRateCounterEntity
import project.lonelywheeler.db.entity.liked.UserLikingOfferEntity
import project.lonelywheeler.db.entity.liked.UserLikingSellerEntity
import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.db.entity.offer.equipment.EquipmentEntity
import project.lonelywheeler.db.entity.offer.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.entity.offer.vehicle.pedestrian.PedestrianVehicleEntity
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

    suspend fun createOrUpdate(entity: MotorVehicleEntity): MyResponse<MotorVehicleEntity> {
        return motorVehicleService.createOrUpdate(entity)
    }

    suspend fun createOrUpdate(entity: EquipmentEntity): MyResponse<EquipmentEntity> {
        return equipmentService.create(entity)
    }

    suspend fun createOrUpdate(entity: PedestrianVehicleEntity): MyResponse<PedestrianVehicleEntity> {
        return pedestrianVehicleService.create(entity)
    }

    suspend fun createOrDelete(entity: UserLikingOfferEntity): MyResponse<UserLikingOfferEntity> {
        return favoriteOfferService.createOrDelete(entity)
    }

    suspend fun readOffersByType(entityTypeId: Int): MyResponse<List<OfferEntity>> {
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

    suspend fun readOffersFromSeller(sellerId: String): MyResponse<MutableList<OfferEntity>> {
        return offerService.readAll(sellerId)
    }

    suspend fun readSeller(sellerId: String): MyResponse<UserEntity> {
        return userService.read(sellerId)
    }

    suspend fun readUsers(): MyResponse<List<UserEntity>> {
        return userService.readAll()
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

    suspend fun readFavorites(userId: String): MyResponse<MutableList<OfferEntity>> {
        return favoriteOfferService.readAll(userId)
    }

    suspend fun hasUserLikedOffer(userId: String, offerId: String): MyResponse<Boolean> {
        return favoriteOfferService.read(userId, offerId)
    }

    suspend fun readIsLikedOrDisliked(
        userLikingId: String,
        userLikedId: String,
    ): MyResponse<UserLikingSellerEntity> {
        return userService.readIsLikedOrDisliked(userLikingId, userLikedId)
    }

    suspend fun readRateCounter(userID: String): MyResponse<SellerRateCounterEntity> {
        return userService.readRateCounter(userID)
    }

    suspend fun like(entity: UserLikingSellerEntity) {
        userService.like(entity)
    }

    suspend fun dislike(entity: UserLikingSellerEntity) {
        userService.dislike(entity)
    }

    suspend fun deleteMotorVehicle(entity: OfferEntity) {
        motorVehicleService.delete(entity._id!!)
    }

    suspend fun deletePedestrianVehicle(entity: OfferEntity) {
        pedestrianVehicleService.delete(entity._id!!)
    }

    suspend fun deleteEquipment(entity: OfferEntity) {
        equipmentService.delete(entity._id!!)
    }

}