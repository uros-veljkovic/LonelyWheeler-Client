package project.lonelywheeler.db.service.api

import project.lonelywheeler.db.entity.offfer.OfferEntity
import project.lonelywheeler.db.response.MyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OfferApi {

    @GET("offer/readAll")
    suspend fun readAll(@Query("sellerID") sellerId: String): MyResponse<MutableList<OfferEntity>>
}