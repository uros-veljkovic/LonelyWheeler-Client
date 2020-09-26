package project.lonelywheeler.di.defaults.product.equipment

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.model.domain.product.equipment.EquipmentType
import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultEquipmentType

@Module
@InstallIn(ActivityRetainedComponent::class)
class DefaultEquipmentAttributesModule {

    @DefaultEquipmentType
    @Provides
    fun provideDefaultEquipmentType(): EquipmentType {
        return EquipmentType.Other
    }
}