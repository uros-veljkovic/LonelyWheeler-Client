package project.lonelywheeler.di.model.observable.offer.vehicle.pedestrian

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.model.observable.offer.vehicle.pedestrian.PedestrianVehicleObservable


@Module
@InstallIn(ActivityRetainedComponent::class)
class DefaultHumanPoweredVehicleAttributesModule {

    @Provides
    fun providePedestrianVehicleObservable(): PedestrianVehicleObservable {
        return PedestrianVehicleObservable()
    }

}