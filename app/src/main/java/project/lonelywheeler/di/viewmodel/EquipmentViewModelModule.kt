package project.lonelywheeler.di.viewmodel

import project.lonelywheeler.model.domain.product.equipment.Equipment

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.product.equipment.EquipmentEntity
import project.lonelywheeler.db.entity.product.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.response.MyResponse
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Equipment_Response

@Module
@InstallIn(ActivityRetainedComponent::class)
class EquipmentViewModelModule {

    @Equipment_Response
    @Provides
    fun provideEquipmentResponseMutableLiveData():
            MutableLiveData<MyResponse<EquipmentEntity>> {
        return MutableLiveData()
    }

    fun provideEquipmentResponse() : MyResponse<EquipmentEntity>{
        return MyResponse("", null)
    }

}