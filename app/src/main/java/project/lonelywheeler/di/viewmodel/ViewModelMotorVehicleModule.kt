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
annotation class MyResponseListMotorVehicle

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SellerResponse

@Module
@InstallIn(ActivityRetainedComponent::class)
class ViewModelMotorVehicleModule {

    @MyResponseListMotorVehicle
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

    /*Bound multiple times MyResponse<MotorVehicleEntity
    @Provides
    fun provideMotorVehicleResponse(): MyResponse<MotorVehicleEntity> {
        return MyResponse(-1, "", null)
    }*/

    /*Bound multiple times MyResponse<UserEntity>
    @Provides
    fun provideSellerResponse(): MyResponse<UserEntity> {
        return MyResponse(-1, "", null)
    }*/

    @Provides
    fun provideMotorVehicleMLD(vehicle: MotorVehicleObservable): MutableLiveData<MotorVehicleObservable> {
        return MutableLiveData(vehicle)
    }

}