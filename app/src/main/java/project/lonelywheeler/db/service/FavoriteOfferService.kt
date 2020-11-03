package project.lonelywheeler.db.service

import project.lonelywheeler.db.entity.liked.LikedOfferEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.db.service.api.FavoriteOfferApi
import javax.inject.Inject

class FavoriteOfferService
@Inject
constructor(
    private val favoriteOfferApi: FavoriteOfferApi
) {

    suspend fun createOrDelete(entity: LikedOfferEntity): MyResponse<LikedOfferEntity> {
        return favoriteOfferApi.createOrDelete(entity)
    }

    suspend fun read(userId: String, offerId: String): MyResponse<Boolean> {
        return favoriteOfferApi.read(userId, offerId)
    }

    suspend fun readAll(userId: String): MyResponse<List<LikedOfferEntity>> {
        return favoriteOfferApi.readAll(userId)
    }
}