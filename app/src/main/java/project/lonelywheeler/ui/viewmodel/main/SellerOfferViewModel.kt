package project.lonelywheeler.ui.viewmodel.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import project.lonelywheeler.db.entity.offfer.OfferEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse

//TODO: Create MotorVehicleListViewModelModule.kt dependency injection class
class SellerOfferViewModel
@ViewModelInject
constructor(
    val repository: Repository,
    val offers: MutableLiveData<MyResponse<MutableList<OfferEntity>>>,
) : ViewModel() {

    fun loadOffers(sellerId: String) {
        CoroutineScope(IO).launch {
            offers.postValue(
                repository.readOffers(sellerId)
            )
        }
    }
}