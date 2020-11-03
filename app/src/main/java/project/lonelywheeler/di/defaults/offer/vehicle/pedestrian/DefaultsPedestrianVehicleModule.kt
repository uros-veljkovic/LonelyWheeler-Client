package project.lonelywheeler.di.defaults.offer.vehicle.pedestrian

import androidx.databinding.ObservableField
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.model.domain.offer.vehicle.pedestrian.PedestrianVehicleType
import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultHumanPoweredVehicleType

@Module
@InstallIn(ActivityRetainedComponent::class)
class DefaultHumanPoweredVehicleAttributesModule {

    @DefaultHumanPoweredVehicleType
    @Provides
    fun provideHumanPoweredVehicleType(): ObservableField<PedestrianVehicleType> {
        return ObservableField(PedestrianVehicleType.Other)
    }

}