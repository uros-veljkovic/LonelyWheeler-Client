package project.lonelywheeler.db.service.api

import project.lonelywheeler.db.entity.liked.SellerRateCounterEntity
import project.lonelywheeler.db.entity.liked.UserLikingSellerEntity
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.response.MyResponse
import retrofit2.Call
import retrofit2.http.*

/*
*   The object in @Body will be converted using a converter specified on the Retrofit instance.
*  If no converter is added, only RequestBody can be used.
* */
interface UserApi {

    @GET("users/read")
    suspend fun readAll(): MyResponse<List<UserEntity>>

    @GET("users/read/{id}")
    suspend fun read(@Path("id") id: String): MyResponse<UserEntity>

    @GET("users/{userID}/likedOrDisliked/{sellerID}")
    suspend fun readIsLikedOrDisliked(
        @Path("userID") userID: String,
        @Path("sellerID") sellerID: String,
    ): MyResponse<UserLikingSellerEntity>

    @GET("users/{userID}/rateCounter")
    suspend fun readRateCounter(
        @Path("userID") userID: String,
    ): MyResponse<SellerRateCounterEntity>

    @POST("users/like")
    suspend fun like(@Body likingEntity: UserLikingSellerEntity): MyResponse<UserLikingSellerEntity>

    @POST("users/dislike")
    suspend fun dislike(@Body likingEntity: UserLikingSellerEntity): MyResponse<UserLikingSellerEntity>

    @PATCH("users/update")
    suspend fun update(@Body userEntity: UserEntity): MyResponse<UserEntity>

    @DELETE("users/delete/{id}")
    suspend fun delete(@Path("id") id: String): MyResponse<UserEntity>

    @POST("users/sign-in")
    fun signInUser(@Body userEntity: UserEntity): Call<MyResponse<UserEntity>>

    @POST("users/sign-up")
    fun signUpUser(@Body userEntity: UserEntity): Call<MyResponse<UserEntity>>

}