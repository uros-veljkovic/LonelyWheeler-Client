package project.lonelywheeler.db.service

import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.db.entity.offer.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.db.service.api.MotorVehicleApi
import javax.inject.Inject

class MotorVehicleService
@Inject
constructor(
    private val motorVehicleApi: MotorVehicleApi
) {

    suspend fun createOrUpdate(entity: MotorVehicleEntity): MyResponse<MotorVehicleEntity> {
        return motorVehicleApi.createOrUpdate(entity)
    }

    suspend fun readAll(): MyResponse<List<OfferEntity>> {
        return motorVehicleApi.readAll()
    }

    suspend fun read(offerId: String): MyResponse<MotorVehicleEntity> {
        return motorVehicleApi.read(offerId)
    }

    suspend fun delete(id: String): MyResponse<MotorVehicleEntity> {
        return motorVehicleApi.delete(id)
    }

}