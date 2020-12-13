package project.lonelywheeler.ui.viewmodel.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.util.constants.ENTITY_TYPE_EQUIPMENT
import project.lonelywheeler.util.constants.ENTITY_TYPE_MOTOR_VEHICLE
import project.lonelywheeler.util.constants.ENTITY_TYPE_PEDESTRIAN_VEHICLE

class ViewModelOffers
@ViewModelInject
/*
* TODO: Initialize 3 different lists for every offer category.
* */
constructor(
    val repository: Repository,
) : ViewModel() {

    val responseMotorVehicle: MutableLiveData<MyResponse<List<OfferEntity>>> = MutableLiveData()
    val responseEquipment: MutableLiveData<MyResponse<List<OfferEntity>>> = MutableLiveData()
    val responsePedestrianVehicle: MutableLiveData<MyResponse<List<OfferEntity>>> =
        MutableLiveData()

    init {
        read(ENTITY_TYPE_MOTOR_VEHICLE, responseMotorVehicle)
        read(ENTITY_TYPE_EQUIPMENT, responseEquipment)
        read(ENTITY_TYPE_PEDESTRIAN_VEHICLE, responsePedestrianVehicle)
    }

    fun read(entityTypeId: Int, list: MutableLiveData<MyResponse<List<OfferEntity>>>) {
        CoroutineScope(IO).launch {
            list.postValue(
                repository.readOffersByType(entityTypeId)
            )
        }
    }

    fun getOffers(entityTypeId: Int): List<OfferEntity>? {
        return when (entityTypeId) {
            ENTITY_TYPE_MOTOR_VEHICLE -> {
                responseMotorVehicle.value?.entity
            }
            ENTITY_TYPE_EQUIPMENT -> {
                responseMotorVehicle.value?.entity
            }
            else -> {
                responsePedestrianVehicle.value?.entity
            }
        }
    }


}