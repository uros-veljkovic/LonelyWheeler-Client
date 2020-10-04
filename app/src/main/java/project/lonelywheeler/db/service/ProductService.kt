package project.lonelywheeler.db.service

import project.lonelywheeler.db.service.api.ProductApi
import project.lonelywheeler.db.entity.product.equipment.EquipmentEntity
import project.lonelywheeler.db.entity.vehicle.HumanPoweredVehicleEntity
import project.lonelywheeler.db.entity.vehicle.MotorVehicleEntity
import retrofit2.Call
import javax.inject.Inject

class ProductService
@Inject
constructor(
    private val productApi: ProductApi
) {

    suspend fun getMotorVehicleById(entity: MotorVehicleEntity): Call<MotorVehicleEntity> {
        return productApi.getMotorVehicleById(entity.id!!)
    }

    suspend fun getMotorVehicles(): Call<List<MotorVehicleEntity>> {
        return productApi.getMotorVehicles()
    }

    suspend fun getHumanPoweredVehicles(): Call<List<HumanPoweredVehicleEntity>> {
        return productApi.getHumanPoweredVehicles()
    }

    suspend fun getEquipment(): Call<List<EquipmentEntity>> {
        return productApi.getEquipment()
    }
}