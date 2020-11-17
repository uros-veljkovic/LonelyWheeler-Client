package project.lonelywheeler.db.service.api

import project.lonelywheeler.db.entity.liked.LikedOfferEntity
import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.db.response.MyResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FavoriteOfferApi {



    /**
     * Creates liked-offer MongoDB document if no documents match [likedOfferEntity] properties,
     * otherwise, deletes the document that does.
     *
     * @param likedOfferEntity
     * @return created or deleted entity
     */
    @POST("liked-offer/createOrDelete")
    suspend fun createOrDelete(@Body likedOfferEntity: LikedOfferEntity): MyResponse<LikedOfferEntity>



    /**
     * Loads favorite offers for specific user
     *
     * @param userId
     * @return List<LikedOfferEntity> list of all liked offers of user with [userId]
     */
    @GET("liked-offer/readAll")
    suspend fun readAll(
        @Query("userId") userId: String,
    ): MyResponse<MutableList<OfferEntity>>


    /**
     * Checks if a user liked specific offer
     *
     * @param userId
     * @param offerId
     * @return true if user with [userID] liked offer with [offerID]
     */
    @GET("liked-offer/read")
    suspend fun read(
        @Query("userId") userId: String,
        @Query("offerId") offerId: String
    ): MyResponse<Boolean>

}