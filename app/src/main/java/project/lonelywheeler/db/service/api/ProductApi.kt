package project.lonelywheeler.db.service.api

import project.lonelywheeler.db.entity.product.equipment.EquipmentEntity
import project.lonelywheeler.db.entity.vehicle.HumanPoweredVehicleEntity
import project.lonelywheeler.db.entity.vehicle.MotorVehicleEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("motor-vehicles")
    suspend fun getMotorVehicles(): Call<List<MotorVehicleEntity>>

    @GET("motor-vehicles/{id}")
    suspend fun getMotorVehicleById(@Path("id") id: String): Call<MotorVehicleEntity>

    @GET("human-powered-vehicles")
    suspend fun getHumanPoweredVehicles(): Call<List<HumanPoweredVehicleEntity>>

    @GET("human-powered-vehicles/{id}")
    suspend fun getHumanPoweredVehicleById(@Path("id") id: String): Call<HumanPoweredVehicleEntity>

    @GET("equipment")
    suspend fun getEquipment(): Call<List<EquipmentEntity>>

    @GET("equipment/{vehicleId}")
    suspend fun getEquipmentById(@Path("id") id: Long): Call<EquipmentEntity>

}