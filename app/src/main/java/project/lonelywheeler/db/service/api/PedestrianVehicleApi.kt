package project.lonelywheeler.db.service.api

import project.lonelywheeler.db.entity.product.vehicle.pedestrian.PedestrianVehicleEntity
import project.lonelywheeler.db.response.MyResponse
import retrofit2.Call
import retrofit2.http.*

interface PedestrianVehicleApi {

    @GET("pedestrian-vehicles/read")
    suspend fun readAll(): Call<MyResponse<List<PedestrianVehicleEntity>>>

    @GET("pedestrian-vehicles/read/{id}")
    suspend fun read(@Path("id") id: String): Call<MyResponse<PedestrianVehicleEntity>>

    @DELETE("pedestrian-vehicles/delete/{id}")
    suspend fun delete(@Path("id") id: String): Call<MyResponse<PedestrianVehicleEntity>>

    @POST("pedestrian-vehicles/create")
    suspend fun create(@Body entity: PedestrianVehicleEntity): Call<MyResponse<PedestrianVehicleEntity>>

    @PATCH("pedestrian-vehicles/update")
    suspend fun update(@Body entity: PedestrianVehicleEntity): Call<MyResponse<PedestrianVehicleEntity>>
}