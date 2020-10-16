package project.lonelywheeler.db.service.api

import project.lonelywheeler.db.entity.product.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.response.MyResponse
import retrofit2.Call
import retrofit2.http.*

interface MotorVehicleApi {

    @POST("motor-vehicles/create")
    suspend fun create(@Body motorVehicleEntity: MotorVehicleEntity): MyResponse<MotorVehicleEntity>

    @GET("motor-vehicles/read")
    suspend fun readAll(): Call<MyResponse<List<MotorVehicleEntity>>>

    @GET("motor-vehicles/read/{id}")
    suspend fun read(@Path("id") id: String): Call<MyResponse<MotorVehicleEntity>>

    @DELETE("motor-vehicles/delete/{id}")
    suspend fun delete(@Path("id") id: String): Call<MyResponse<MotorVehicleEntity>>

    @PATCH("motor-vehicles/update")
    suspend fun update(@Body motorVehicleEntity: MotorVehicleEntity): Call<MyResponse<MotorVehicleEntity>>


}