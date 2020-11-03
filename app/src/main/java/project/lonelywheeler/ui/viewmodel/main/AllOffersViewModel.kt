package project.lonelywheeler.ui.viewmodel.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import project.lonelywheeler.db.entity.offfer.OfferEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse

class AllOffersViewModel
@ViewModelInject
constructor(
    val repository: Repository,
) : ViewModel() {

    val response: MutableLiveData<MyResponse<List<OfferEntity>>> = MutableLiveData()

    fun read(entityTypeId : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            response.postValue(
                repository.readSpecificTypeOffers(entityTypeId)
            )
        }
    }

}