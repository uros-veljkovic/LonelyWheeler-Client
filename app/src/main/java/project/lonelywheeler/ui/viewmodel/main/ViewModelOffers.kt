package project.lonelywheeler.ui.viewmodel.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.util.constants.NO_ENTITY_TYPE

class ViewModelOffers
@ViewModelInject
/*
* TODO: Initialize 3 different lists for every offer category.
* */
constructor(
    val repository: Repository,
) : ViewModel() {

    val response: MutableLiveData<MyResponse<List<OfferEntity>>> = MutableLiveData()
    var entityTypeId = NO_ENTITY_TYPE

    suspend fun read(entityTypeId: Int) {
        response.postValue(
            repository.readOffersByType(entityTypeId)
        )
        this.entityTypeId = entityTypeId
    }

    fun getOffers(): List<OfferEntity> {
        return response.value?.entity ?: mutableListOf()
    }

}