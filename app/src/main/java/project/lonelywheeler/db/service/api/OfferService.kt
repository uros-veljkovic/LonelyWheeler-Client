package project.lonelywheeler.db.service.api

import project.lonelywheeler.db.entity.liked.LikedOfferEntity
import project.lonelywheeler.db.entity.offfer.OfferEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.db.service.api.FavoriteOfferApi
import javax.inject.Inject

class OfferService
@Inject
constructor(
    private val offerApi: OfferApi
) {
    suspend fun readAll(sellerId: String): MyResponse<MutableList<OfferEntity>> {
        return offerApi.readAll(sellerId)
    }
}