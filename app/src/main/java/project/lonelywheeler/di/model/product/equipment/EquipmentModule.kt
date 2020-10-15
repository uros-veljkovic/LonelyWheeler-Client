package project.lonelywheeler.di.model.product.equipment

import android.graphics.Bitmap
import androidx.databinding.ObservableField
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.di.defaults.primitives.DefaultBoolean
import project.lonelywheeler.di.defaults.primitives.DefaultNullableString
import project.lonelywheeler.di.defaults.primitives.DefaultObservableString
import project.lonelywheeler.di.defaults.primitives.DefaultString
import project.lonelywheeler.di.defaults.product.equipment.DefaultEquipmentType
import project.lonelywheeler.di.defaults.product.vehicle.motor.DefaultListOfCarPictures
import project.lonelywheeler.di.defaults.product.vehicle.motor.DefaultProductCondition
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
        @DefaultNullableString id: String?,
        @DefaultNullableString sellerId: String?,
        basicInfo: ProductBasicInfo,
        @DefaultObservableString condition: ObservableField<String>,
        @DefaultListOfCarPictures pictures: List<Bitmap>,
        @DefaultBoolean valueFixed: Boolean,
        @DefaultBoolean firstOwner: Boolean,
        @DefaultBoolean sellerInForExchange: Boolean,
        @DefaultString otherInfo: String,
        @DefaultString colorExterior: String,
        @DefaultString colorInterior: String,
        @DefaultString materialInterior: String,
        @DefaultObservableString equipmentType: ObservableField<String>
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
