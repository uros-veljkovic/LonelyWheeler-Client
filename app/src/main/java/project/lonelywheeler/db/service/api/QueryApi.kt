package project.lonelywheeler.db.service.api

import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.db.entity.query.QueryEntity
import project.lonelywheeler.db.response.MyResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface QueryApi {

    @POST("query/")
    suspend fun makeQuery(@Body queryEntity: QueryEntity): MyResponse<MutableList<OfferEntity>>

}