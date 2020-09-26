package project.lonelywheeler.di.model.product.vehicle.humanpowered

import android.graphics.Bitmap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.di.defaults.primitives.*
import project.lonelywheeler.di.defaults.product.vehicle.humanpowered.DefaultHumanPoweredVehicleType
import project.lonelywheeler.di.defaults.product.vehicle.motorpowered.DefaultListOfCarPictures
import project.lonelywheeler.di.defaults.product.vehicle.motorpowered.DefaultProductCondition
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.Product
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import project.lonelywheeler.model.domain.product.humanvehicle.HumanPoweredVehicle
import project.lonelywheeler.model.domain.product.humanvehicle.HumanPoweredVehicleType

@Module
@InstallIn(ActivityRetainedComponent::class)
class HumanPoweredVehicleModule {

    @Provides
    fun provideHumanPoweredVehicle(
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
        @DefaultHumanPoweredVehicleType humanPoweredVehicleType: HumanPoweredVehicleType

    ): Product {
        return HumanPoweredVehicle(
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
            humanPoweredVehicleType
        )
    }


}