package project.lonelywheeler.db.service

import project.lonelywheeler.db.entity.liked.UserLikingOfferEntity
import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.db.service.api.FavoriteOfferApi
import javax.inject.Inject

class FavoriteOfferService
@Inject
constructor(
    private val favoriteOfferApi: FavoriteOfferApi
) {

    suspend fun createOrDelete(entity: UserLikingOfferEntity): MyResponse<UserLikingOfferEntity> {
        return favoriteOfferApi.createOrDelete(entity)
    }

    suspend fun read(userId: String, offerId: String): MyResponse<Boolean> {
        return favoriteOfferApi.read(userId, offerId)
    }

    suspend fun readAll(userId: String): MyResponse<MutableList<OfferEntity>> {
        return favoriteOfferApi.readAll(userId)
    }
}