package project.lonelywheeler.di.model.observable.offer.equipment

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.model.observable.offer.equipment.EquipmentObservable

@Module
@InstallIn(ActivityRetainedComponent::class)
class EquipmentObservableModule {

    @Provides
    fun provideEquipmentObservable(): EquipmentObservable {
        return EquipmentObservable()
    }
}
