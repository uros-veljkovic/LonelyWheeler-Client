package project.lonelywheeler.db.service

import project.lonelywheeler.db.entity.product.ProductEntity
import project.lonelywheeler.db.entity.product.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.db.service.api.MotorVehicleApi
import retrofit2.Call
import javax.inject.Inject

class MotorVehicleService
@Inject
constructor(
    private val motorVehicleApi: MotorVehicleApi
) {

    suspend fun create(entity: MotorVehicleEntity): MyResponse<MotorVehicleEntity> {
        return motorVehicleApi.create(entity)
    }

    suspend fun readAll(): MyResponse<List<ProductEntity>> {
        return motorVehicleApi.readAll()
    }

    suspend fun read(offerId: String): MyResponse<MotorVehicleEntity> {
        return motorVehicleApi.read(offerId)
    }

    suspend fun update(entity: MotorVehicleEntity): Call<MyResponse<MotorVehicleEntity>> {
        return motorVehicleApi.update(entity)
    }

    suspend fun delete(entity: UserEntity): Call<MyResponse<MotorVehicleEntity>> {
        return motorVehicleApi.delete(entity.id!!)
    }

}