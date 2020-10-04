package project.lonelywheeler.db.service.api

import project.lonelywheeler.db.entity.user.UserEntity
import retrofit2.Call
import retrofit2.http.*

/*
*   The object in @Body will be converted using a converter specified on the Retrofit instance.
*  If no converter is added, only RequestBody can be used.
* */
interface UserApi {

    @GET("users/read")
    suspend fun getUsers(): Call<List<UserEntity>>

    @GET("users/read/{id}")
    suspend fun getUserById(@Path("id") id: Long): Call<UserEntity>

    @PATCH("users/update")
    suspend fun updateUser(@Body userEntity: UserEntity): Call<UserEntity>

    @DELETE("users/delete/{id}")
    suspend fun deleteUser(@Path("id") id: Long): Call<Unit>

    @POST("users/sign-in")
    suspend fun signInUser(@Body userEntity: UserEntity): Call<UserEntity>

    @POST("users/sign-up")
    suspend fun signUpUser(@Body userEntity: UserEntity): Call<UserEntity>

}