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

    suspend fun getUsers(): Call<MyResponse<List<UserEntity>>> {
        return userApi.getUsers()
    }

    suspend fun getUserById(userEntity: UserEntity): Call<MyResponse<UserEntity>> {
        return userApi.getUserById(userEntity.id!!)
    }

    suspend fun updateUser(userEntity: UserEntity): Call<MyResponse<UserEntity>> {
        return userApi.updateUser(userEntity)
    }

    suspend fun deleteUser(userEntity: UserEntity): Call<MyResponse<UserEntity>> {
        return userApi.deleteUser(userEntity.id!!)
    }

    fun signInUser(userEntity: UserEntity, authListener: AuthListener) {
        userApi.signInUser(userEntity).enqueue(object : Callback<MyResponse<UserEntity>> {
            override fun onResponse(
                call: Call<MyResponse<UserEntity>>,
                response: Response<MyResponse<UserEntity>>
            ) {
                when (response.code()) {
                    RESPONSE_CODE_REQUEST_SUCCESS -> {
                        authListener.onSignInSuccessful(response.body())
                    }
                    RESPONSE_CODE_REQUEST_FAIL,
                    RESPONSE_CODE_SERVER_FAIL -> {
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
                when (response.code()) {
                    RESPONSE_CODE_REQUEST_SUCCESS -> {
                        authListener.onSignUpSuccessful(response.body())
                    }
                    RESPONSE_CODE_REQUEST_FAIL,
                    RESPONSE_CODE_SERVER_FAIL -> {
                        authListener.onSignUpFailed(response.body())
                    }
                }
            }

            override fun onFailure(call: Call<MyResponse<UserEntity>>, t: Throwable) {
            }

        })

    }

}