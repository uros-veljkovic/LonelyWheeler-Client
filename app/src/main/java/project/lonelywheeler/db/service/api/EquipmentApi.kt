package project.lonelywheeler.db.service.api

import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.db.entity.offer.equipment.EquipmentEntity
import project.lonelywheeler.db.response.MyResponse
import retrofit2.http.*

interface EquipmentApi {


    @GET("equipment/read")
    suspend fun readAll(): MyResponse<List<OfferEntity>>

    @GET("equipment/read/{id}")
    suspend fun read(@Path("id") id: String): MyResponse<EquipmentEntity>

    @DELETE("equipment/delete/{id}")
    suspend fun delete(@Path("id") id: String): MyResponse<EquipmentEntity>

    @POST("equipment/create")
    suspend fun create(@Body entity: EquipmentEntity): MyResponse<EquipmentEntity>

    @PATCH("equipment/update")
    suspend fun update(@Body entity: EquipmentEntity): MyResponse<EquipmentEntity>
}