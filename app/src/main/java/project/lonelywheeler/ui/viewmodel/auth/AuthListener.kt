package project.lonelywheeler.ui.viewmodel.auth

import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.response.MyResponse

interface AuthListener {

    fun onSignUpSuccessful(myResponse: MyResponse<UserEntity>?);
    fun onSignInSuccessful(myResponse: MyResponse<UserEntity>?);
    fun onSignUpFailed(myResponse: MyResponse<UserEntity>?);
    fun onSignInFailed(myResponse: MyResponse<UserEntity>?);
}