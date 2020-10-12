package project.lonelywheeler.di.model.product.vehicle.humanpowered

import android.graphics.Bitmap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.di.defaults.primitives.DefaultBoolean
import project.lonelywheeler.di.defaults.primitives.DefaultString
import project.lonelywheeler.di.defaults.product.vehicle.pedestrian.DefaultHumanPoweredVehicleType
import project.lonelywheeler.di.defaults.product.vehicle.motor.DefaultListOfCarPictures
import project.lonelywheeler.di.defaults.product.vehicle.motor.DefaultProductCondition
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.Product
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import project.lonelywheeler.model.domain.product.vehicle.pedestrian.PedestrianVehicle
import project.lonelywheeler.model.domain.product.vehicle.pedestrian.PedestrianVehicleType

@Module
@InstallIn(ActivityRetainedComponent::class)
class HumanPoweredVehicleModule {

    @Provides
    fun provideHumanPoweredVehicle(
        @DefaultString id: String?,
        @DefaultString sellerId: String?,
        basicInfo: ProductBasicInfo,
        @DefaultProductCondition condition: Condition,
        @DefaultListOfCarPictures pictures: List<Bitmap>,
        @DefaultBoolean valueFixed: Boolean,
        @DefaultBoolean firstOwner: Boolean,
        @DefaultBoolean sellerInForExchange: Boolean,
        @DefaultString otherInfo: String,
        @DefaultString colorExterior: String,
        @DefaultString colorInterior: String,
        @DefaultString materialInterior: String,
        @DefaultHumanPoweredVehicleType pedestrianVehicleType: PedestrianVehicleType

    ): Product {
        return PedestrianVehicle(
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
            pedestrianVehicleType
        )
    }


}