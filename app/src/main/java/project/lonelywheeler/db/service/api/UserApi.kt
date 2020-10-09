package project.lonelywheeler.db.service.api

import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.response.MyResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

/*
*   The object in @Body will be converted using a converter specified on the Retrofit instance.
*  If no converter is added, only RequestBody can be used.
* */
interface UserApi {

    @GET("users/read")
    suspend fun getUsers(): Call<MyResponse<List<UserEntity>>>

    @GET("users/read/{id}")
    suspend fun getUserById(@Path("id") id: String): Call<MyResponse<UserEntity>>

    @PATCH("users/update")
    suspend fun updateUser(@Body userEntity: UserEntity): Call<MyResponse<UserEntity>>

    @DELETE("users/delete/{id}")
    suspend fun deleteUser(@Path("id") id: String): Call<MyResponse<UserEntity>>

    @POST("users/sign-in")
    fun signInUser(@Body userEntity: UserEntity): Call<MyResponse<UserEntity>>

    @POST("users/sign-up")
    fun signUpUser(@Body userEntity: UserEntity): Call<MyResponse<UserEntity>>

}