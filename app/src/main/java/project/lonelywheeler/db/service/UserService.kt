package project.lonelywheeler.db.service

import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.db.service.api.UserApi
import project.lonelywheeler.ui.viewmodel.auth.AuthListener
import project.lonelywheeler.util.constants.RESPONSE_CODE_REQUEST_FAIL
import project.lonelywheeler.util.constants.RESPONSE_CODE_REQUEST_SUCCESS
import project.lonelywheeler.util.constants.RESPONSE_CODE_SERVER_FAIL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/*
    The defined Retrofit converter (like GsonConverter) will map the defined
   Object to JSON and it will finally sent
   as the request’s body to your defined server.
*/
class UserService
@Inject
constructor(
    private val userApi: UserApi
) {

    private val TAG = "UserService"

    suspend fun readAll(): MyResponse<List<UserEntity>> {
        return userApi.readAll()
    }

    suspend fun read(sellerId: String): MyResponse<UserEntity> {
        return userApi.read(sellerId)
    }

    suspend fun update(entity: UserEntity): MyResponse<UserEntity> {
        return userApi.update(entity)
    }

    suspend fun delete(entity: UserEntity): MyResponse<UserEntity> {
        return userApi.delete(entity.id!!)
    }

    fun signInUser(userEntity: UserEntity, authListener: AuthListener) {

        userApi.signInUser(userEntity).enqueue(object : Callback<MyResponse<UserEntity>> {
            override fun onResponse(
                call: Call<MyResponse<UserEntity>>,
                response: Response<MyResponse<UserEntity>>
            ) {
                when (response.body()!!.responseCode) {
                    RESPONSE_CODE_REQUEST_SUCCESS -> {
                        authListener.onSignInSuccessful(response.body())
                    }
                    else -> {
                        authListener.onSignInFailed(response.body())
                    }
                }
            }

            override fun onFailure(call: Call<MyResponse<UserEntity>>, t: Throwable) {

            }
        })
    }

    fun signUpUser(userEntity: UserEntity, authListener: AuthListener) {

        userApi.signUpUser(userEntity).enqueue(object : Callback<MyResponse<UserEntity>> {

            override fun onResponse(
                call: Call<MyResponse<UserEntity>>,
                response: Response<MyResponse<UserEntity>>
            ) {
                when (response.body()!!.responseCode) {
                    RESPONSE_CODE_REQUEST_SUCCESS -> {
                        authListener.onSignUpSuccessful(response.body())
                    }
                    else -> {
                        authListener.onSignUpFailed(response.body())
                    }
                }
            }

            override fun onFailure(call: Call<MyResponse<UserEntity>>, t: Throwable) {
            }

        })

    }

}