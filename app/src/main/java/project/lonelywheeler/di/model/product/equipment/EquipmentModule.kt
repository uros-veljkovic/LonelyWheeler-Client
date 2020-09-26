package project.lonelywheeler.di.model.product.equipment

import android.graphics.Bitmap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.di.defaults.primitives.DefaultBoolean
import project.lonelywheeler.di.defaults.primitives.DefaultNullableLong
import project.lonelywheeler.di.defaults.primitives.DefaultNullableString
import project.lonelywheeler.di.defaults.product.equipment.DefaultEquipmentType
import project.lonelywheeler.di.defaults.product.vehicle.motorpowered.DefaultListOfCarPictures
import project.lonelywheeler.di.defaults.product.vehicle.motorpowered.DefaultProductCondition
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.Product
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import project.lonelywheeler.model.domain.product.equipment.Equipment
import project.lonelywheeler.model.domain.product.equipment.EquipmentType

@Module
@InstallIn(ActivityRetainedComponent::class)
class EquipmentModule {

    @Provides
    fun provideEquipment(
        @DefaultNullableLong id: Long?,
        @DefaultNullableLong sellerId: Long?,
        basicInfo: ProductBasicInfo,
        @DefaultProductCondition condition: Condition?,
        @DefaultListOfCarPictures pictures: List<Bitmap>,
        @DefaultBoolean valueFixed: Boolean,
        @DefaultBoolean firstOwner: Boolean,
        @DefaultBoolean sellerInForExchange: Boolean,
        @DefaultNullableString otherInfo: String?,
        @DefaultNullableString colorExterior: String?,
        @DefaultNullableString colorInterior: String?,
        @DefaultNullableString materialInterior: String?,
        @DefaultEquipmentType equipmentType: EquipmentType


    ): Product {
        return Equipment(
            id,
            sellerId,
            basicInfo,
            condition,
            pictures,
            valueFixed,
            firstOwner,
            sellerInForExchange,
            otherInfo,
            colorExterior,
            colorInterior,
            materialInterior,
            equipmentType
        )
    }
}
