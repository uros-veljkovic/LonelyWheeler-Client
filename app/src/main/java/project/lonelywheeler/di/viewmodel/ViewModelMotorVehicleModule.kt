package project.lonelywheeler.di.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.offer.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.model.observable.offer.vehicle.motor.MotorVehicleObservable
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MyResponseMotorVehicle

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SellerResponse

@Module
@InstallIn(ActivityRetainedComponent::class)
class ViewModelMotorVehicleModule {

    @MyResponseMotorVehicle
    @Provides
    fun provideMotorVehicleResponseMutableLiveData(response: MyResponse<MotorVehicleEntity>):
            MutableLiveData<MyResponse<MotorVehicleEntity>> {
        return MutableLiveData(response)
    }

    @SellerResponse
    @Provides
    fun provideSellerResponseMutableLiveData(response: MyResponse<UserEntity>):
            MutableLiveData<MyResponse<UserEntity>> {
        return MutableLiveData(response)
    }

    @Provides
    fun provideMotorVehicleMLD(vehicle: MotorVehicleObservable): MutableLiveData<MotorVehicleObservable> {
        return MutableLiveData(vehicle)
    }

}