package project.lonelywheeler.db.service

import project.lonelywheeler.db.service.api.UserApi
import project.lonelywheeler.db.entity.user.UserEntity
import retrofit2.Call
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

    suspend fun getUsers(): Call<List<UserEntity>> {
        return userApi.getUsers()
    }

    suspend fun getUserById(userEntity: UserEntity): Call<UserEntity> {
        return userApi.getUserById(userEntity.id!!)
    }

    suspend fun updateUser(userEntity: UserEntity): Call<UserEntity> {
        return userApi.updateUser(userEntity)
    }

    suspend fun deleteUser(userEntity: UserEntity): Call<Unit> {
        return userApi.deleteUser(userEntity.id!!)
    }

    suspend fun signInUser(userEntity: UserEntity): Call<UserEntity> {
        return userApi.signInUser(userEntity)
    }

    suspend fun signUpUser(userEntity: UserEntity): Call<UserEntity> {
        return userApi.signUpUser(userEntity)
    }

}