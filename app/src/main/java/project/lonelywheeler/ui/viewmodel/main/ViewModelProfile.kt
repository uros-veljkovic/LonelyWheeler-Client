package project.lonelywheeler.ui.viewmodel.main

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

class ViewModelProfile
@ViewModelInject
constructor(
    val repository: Repository,
    var responseUser: MutableLiveData<MyResponse<UserEntity>>,
) : ViewModel() {

    fun loadSeller(sellerId: String) {
        if (sellerId == MyApplication.getCurrentUserID()) {
            responseUser.postValue(MyResponse(-1, "", MyApplication.currentUser))
        } else {
            CoroutineScope(IO).launch {
                responseUser.postValue(
                    repository.readSeller(sellerId)
                )
            }
        }
    }

}