package project.lonelywheeler.db.service

import project.lonelywheeler.db.entity.product.ProductEntity
import project.lonelywheeler.db.entity.product.vehicle.pedestrian.PedestrianVehicleEntity
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

    suspend fun readAll(): MyResponse<List<ProductEntity>> {
        return pedestrianVehicleApi.readAll()
    }

    suspend fun read(entity: PedestrianVehicleEntity): MyResponse<PedestrianVehicleEntity> {
        return pedestrianVehicleApi.read(entity._id!!)
    }

    suspend fun update(entity: PedestrianVehicleEntity): MyResponse<PedestrianVehicleEntity> {
        return pedestrianVehicleApi.update(entity)
    }

    suspend fun delete(entity: PedestrianVehicleEntity): MyResponse<PedestrianVehicleEntity> {
        return pedestrianVehicleApi.delete(entity._id!!)
    }

}