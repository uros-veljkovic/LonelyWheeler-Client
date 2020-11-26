package project.lonelywheeler.ui.viewmodel.auth

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.model.observable.user.UserObservable

class ViewModelAuth
@ViewModelInject
constructor(
    val user: UserObservable,
    val repository: Repository,
    var authTrigger: MutableLiveData<Boolean>,
    var progressBarTrigger: MutableLiveData<Boolean>,
    var authResponse: MyResponse<UserEntity>?,
) : AuthListener, ViewModel() {

    private val TAG = "AuthViewModel"

    fun signUp() {
        progressBarTrigger.value = true
        val userEntity = user.toEntity()
        repository.signUp(userEntity, this)
    }

    fun signIn() {
        progressBarTrigger.value = true
        val userEntity = user.toEntity()
        CoroutineScope(IO).launch {
            repository.signIn(userEntity, this@ViewModelAuth)
        }
    }

    override fun onSignUpSuccessful(myResponse: MyResponse<UserEntity>?) {
        MyApplication.currentUser = myResponse!!.entity
        progressBarTrigger.value = false
        authResponse = myResponse
        authTrigger.value = true
    }

    override fun onSignUpFailed(myResponse: MyResponse<UserEntity>?) {
        progressBarTrigger.value = false
        authResponse = myResponse
        authTrigger.value = false
    }

    override fun onSignInSuccessful(myResponse: MyResponse<UserEntity>?) {
        MyApplication.currentUser = myResponse!!.entity
        progressBarTrigger.value = false
        authResponse = myResponse
        authTrigger.value = true
    }

    override fun onSignInFailed(myResponse: MyResponse<UserEntity>?) {
        progressBarTrigger.value = false
        authResponse = myResponse
        authTrigger.value = false
    }


}