package project.lonelywheeler.di.model.observable.offer.vehicle.motor

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.model.observable.offer.vehicle.motor.MotorVehicleObservable
import javax.inject.Qualifier

@Module
@InstallIn(ActivityRetainedComponent::class)
class DefaultCarAttributesModule {

    @Provides
    fun provideMotorVehicleObservable() : MotorVehicleObservable {
        return MotorVehicleObservable()
    }
}