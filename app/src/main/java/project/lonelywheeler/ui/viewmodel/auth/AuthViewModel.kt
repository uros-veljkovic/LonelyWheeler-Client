package project.lonelywheeler.ui.viewmodel.auth

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.model.domain.user.User
import project.lonelywheeler.model.domain.user.toEntity
import project.lonelywheeler.util.validator.FieldValidator

class AuthViewModel
@ViewModelInject
constructor(
    private val repository: Repository,
    val validator: FieldValidator,
    val user: User,
    var authTrigger: MutableLiveData<Boolean>,
    var authResponse: MyResponse<UserEntity>?,
    @Assisted private val savedStateHandle: SavedStateHandle
) : AuthListener, ViewModel() {

    private val TAG = "AuthViewModel"

    fun signUp() {
        val userEntity = user.toEntity()
        repository.signUp(userEntity, this)
    }

    fun signIn() {
        val userEntity = user.toEntity()
        repository.signIn(userEntity, this)
    }

    //TODO: Uncomment line below
    override fun onSignUpSuccessful(myResponse: MyResponse<UserEntity>?) {
        authResponse = myResponse
        authTrigger.value = true;
    }

    override fun onSignUpFailed(myResponse: MyResponse<UserEntity>?) {
        authResponse = myResponse
        authTrigger.value = false
    }

    override fun onSignInSuccessful(myResponse: MyResponse<UserEntity>?) {
        authResponse = myResponse
        authTrigger.value = true
    }

    override fun onSignInFailed(myResponse: MyResponse<UserEntity>?) {
        authResponse = myResponse
        authTrigger.value = false
    }


}