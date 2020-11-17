package project.lonelywheeler.db.service.api

import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.db.entity.offer.vehicle.pedestrian.PedestrianVehicleEntity
import project.lonelywheeler.db.response.MyResponse
import retrofit2.http.*

interface PedestrianVehicleApi {

    @GET("pedestrian-vehicles/read")
    suspend fun readAll(): MyResponse<List<OfferEntity>>

    @GET("pedestrian-vehicles/read/{id}")
    suspend fun read(@Path("id") id: String): MyResponse<PedestrianVehicleEntity>

    @DELETE("pedestrian-vehicles/delete/{id}")
    suspend fun delete(@Path("id") id: String): MyResponse<PedestrianVehicleEntity>

    @POST("pedestrian-vehicles/create")
    suspend fun create(@Body entity: PedestrianVehicleEntity): MyResponse<PedestrianVehicleEntity>

    @PATCH("pedestrian-vehicles/update")
    suspend fun update(@Body entity: PedestrianVehicleEntity): MyResponse<PedestrianVehicleEntity>
}