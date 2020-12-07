package project.lonelywheeler.ui.viewmodel.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.db.entity.liked.SellerRateCounterEntity
import project.lonelywheeler.db.entity.liked.UserLikingSellerEntity
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.model.observable.liked.SellerRateCounterObservable
import project.lonelywheeler.model.observable.liked.UserLikingSellerObservable
import project.lonelywheeler.model.observable.user.UserObservable

class ViewModelProfile
@ViewModelInject
constructor(
    var userObservable: UserObservable,
    var liking: UserLikingSellerObservable,
    var rateCounter: SellerRateCounterObservable,
    val repository: Repository,
) : ViewModel() {

    var responseUserEntity: MutableLiveData<MyResponse<UserEntity>> = MutableLiveData()
    var responseLikingEntity:
            MutableLiveData<MyResponse<UserLikingSellerEntity>> = MutableLiveData()
    var responseRateCounterEntity:
            MutableLiveData<MyResponse<SellerRateCounterEntity>> = MutableLiveData()

    suspend fun loadSeller(sellerId: String) {
        if (sellerId == MyApplication.getCurrentUserID())
            userObservable = MyApplication.currentUser?.toObservable() ?: UserObservable()
        else {
            responseUserEntity.postValue(repository.readSeller(sellerId))
        }
    }

    suspend fun loadRateCounter(userId: String) {
        responseRateCounterEntity.postValue(repository.readRateCounter(userId))
    }

    suspend fun loadLiking(userId: String, sellerId: String) {
        responseLikingEntity.postValue(repository.readIsLikedOrDisliked(userId, sellerId))
    }

    fun like(sellerId: String) {
        liking.sellerID = sellerId

        liking.like()

        CoroutineScope(IO).launch {
            repository.like(liking.toEntity())
            loadRateCounter(sellerId)
        }
    }

    fun dislike(sellerId: String) {
        liking.sellerID = sellerId

        liking.dislike()

        CoroutineScope(IO).launch {
            repository.dislike(liking.toEntity())
            loadRateCounter(sellerId)
        }
    }

    fun deleteProfile() {
        CoroutineScope(IO).launch {
            repository.deleteUserProfile(MyApplication.getCurrentUserID())
        }
    }

}