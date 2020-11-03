package project.lonelywheeler.db.service

import project.lonelywheeler.db.entity.offfer.OfferEntity
import project.lonelywheeler.db.entity.offfer.vehicle.pedestrian.PedestrianVehicleEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.db.service.api.PedestrianVehicleApi
import javax.inject.Inject

class PedestrianVehicleService
@Inject
constructor(
    private val pedestrianVehicleApi: PedestrianVehicleApi
) {

    suspend fun create(entity: PedestrianVehicleEntity): MyResponse<PedestrianVehicleEntity> {
        return pedestrianVehicleApi.create(entity)
    }

    suspend fun readAll(): MyResponse<List<OfferEntity>> {
        return pedestrianVehicleApi.readAll()
    }

    suspend fun read(_id: String): MyResponse<PedestrianVehicleEntity> {
        return pedestrianVehicleApi.read(_id)
    }

    suspend fun update(entity: PedestrianVehicleEntity): MyResponse<PedestrianVehicleEntity> {
        return pedestrianVehicleApi.update(entity)
    }

    suspend fun delete(entity: PedestrianVehicleEntity): MyResponse<PedestrianVehicleEntity> {
        return pedestrianVehicleApi.delete(entity._id!!)
    }

}