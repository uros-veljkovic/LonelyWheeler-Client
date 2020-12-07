package project.lonelywheeler.db.service

import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.db.service.api.OfferApi
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