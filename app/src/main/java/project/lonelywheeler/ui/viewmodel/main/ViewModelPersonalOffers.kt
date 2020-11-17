package project.lonelywheeler.ui.viewmodel.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.db.entity.offer.equipment.EquipmentEntity
import project.lonelywheeler.db.entity.offer.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.entity.offer.vehicle.pedestrian.PedestrianVehicleEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse

//TODO: Create MotorVehicleListViewModelModule.kt dependency injection class
class ViewModelPersonalOffers
@ViewModelInject
constructor(
    val repository: Repository,
    val offers: MutableLiveData<MyResponse<MutableList<OfferEntity>>>,
) : ViewModel() {

    fun loadPersonalOffers(sellerId: String) {
        CoroutineScope(IO).launch {
            offers.postValue(
                repository.readOffersFromSeller(sellerId)
            )
        }
    }

    fun deleteOffer(entity: OfferEntity) {

        when (entity.entityClassSimpleName) {
            MotorVehicleEntity::class.simpleName -> {
                CoroutineScope(IO).launch {
                    repository.deleteMotorVehicle(entity)
                }
            }
            PedestrianVehicleEntity::class.simpleName -> {
                CoroutineScope(IO).launch {
                    repository.deletePedestrianVehicle(entity)
                }
            }
            EquipmentEntity::class.simpleName -> {
                CoroutineScope(IO).launch {
                    repository.deleteEquipment(entity)
                }
            }
        }


    }

    fun loadFavorites(sellerId: String) {
        CoroutineScope(IO).launch {
            offers.postValue(
                repository.readFavorites(sellerId)
            )
        }
    }
}