package project.lonelywheeler.di.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.offer.vehicle.pedestrian.PedestrianVehicleEntity
import project.lonelywheeler.db.response.MyResponse
import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PedestrianVehicleResponse

@Module
@InstallIn(ActivityRetainedComponent::class)
class ViewModelPedestrianVehicleModule {

    @PedestrianVehicleResponse
    @Provides
    fun provideMotorVehicleResponseMutableLiveData():
            MutableLiveData<MyResponse<PedestrianVehicleEntity>> {
        return MutableLiveData()
    }

    fun provideMotorVehicleResponse(): MyResponse<PedestrianVehicleEntity> {
        return MyResponse(-1, "", null)
    }

}