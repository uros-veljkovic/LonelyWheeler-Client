package project.lonelywheeler.di.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.offfer.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.response.MyResponse
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MotorVehicleResponse

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SellerResponse

@Module
@InstallIn(ActivityRetainedComponent::class)
class MotorVehicleViewModelModule {

    @MotorVehicleResponse
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
    fun provideMotorVehicleResponse(): MyResponse<MotorVehicleEntity> {
        return MyResponse("", null)
    }

    @Provides
    fun provideSellerResponse(): MyResponse<UserEntity> {
        return MyResponse("", null)
    }

}