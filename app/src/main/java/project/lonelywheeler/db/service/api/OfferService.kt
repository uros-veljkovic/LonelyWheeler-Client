package project.lonelywheeler.db.service.api

import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.db.response.MyResponse
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