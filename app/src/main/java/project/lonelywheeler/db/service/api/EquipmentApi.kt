package project.lonelywheeler.db.service.api

import project.lonelywheeler.db.entity.product.equipment.EquipmentEntity
import project.lonelywheeler.db.response.MyResponse
import retrofit2.Call
import retrofit2.http.*

interface EquipmentApi {


    @GET("equipments/read")
    suspend fun readAll(): Call<MyResponse<List<EquipmentEntity>>>

    @GET("equipments/read/{id}")
    suspend fun read(@Path("id") id: String): Call<MyResponse<EquipmentEntity>>

    @DELETE("equipments/delete/{id}")
    suspend fun delete(@Path("id") id: String): Call<MyResponse<EquipmentEntity>>

    @POST("equipments/create")
    suspend fun create(@Body entity: EquipmentEntity): Call<MyResponse<EquipmentEntity>>

    @PATCH("equipments/update")
    suspend fun update(@Body entity: EquipmentEntity): Call<MyResponse<EquipmentEntity>>
}