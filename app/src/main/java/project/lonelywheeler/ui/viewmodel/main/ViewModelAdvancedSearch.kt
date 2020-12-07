package project.lonelywheeler.ui.viewmodel.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.model.observable.query.QueryObservable

class ViewModelAdvancedSearch
@ViewModelInject
constructor(
    val repository: Repository,
    val query: QueryObservable,
    val list: MutableLiveData<MyResponse<MutableList<OfferEntity>>>,
) : ViewModel() {
    fun sendQuery() {
        CoroutineScope(Dispatchers.IO).launch {
            list.postValue(repository.query(query.toEntity()))
        }
    }
}