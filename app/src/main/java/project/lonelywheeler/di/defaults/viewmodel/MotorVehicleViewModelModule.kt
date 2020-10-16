package project.lonelywheeler.di.defaults.viewmodel

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.product.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.response.MyResponse

@Module
@InstallIn(ActivityRetainedComponent::class)
class MotorVehicleViewModelModule {

    @Provides
    fun provideMotorVehicleResponse():
            MutableLiveData<MyResponse<MotorVehicleEntity>> {
        return MutableLiveData()
    }

    @Provides
    fun provideMyResponseMotorVehicleEntity(): MyResponse<MotorVehicleEntity> {
        return MyResponse("", null)
    }

    @Provides
    fun provideCurrentPictureIndex(): ObservableInt {
        return ObservableInt()
    }

}