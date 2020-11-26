package project.lonelywheeler.ui.viewmodel.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse

class ViewModelSellers
@ViewModelInject
constructor(
    val repository: Repository,
) : ViewModel() {

    val response: MutableLiveData<MyResponse<List<UserEntity>>> = MutableLiveData()

    suspend fun read() {
        response.postValue(
            repository.readUsers()
        )
    }

    fun getItem(position: Int): UserEntity {
        return response.value?.entity?.get(position)!!
    }

}