package project.lonelywheeler.di.model.product.vehicle.pedestrian

import android.graphics.Bitmap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.di.defaults.product.vehicle.motor.DefaultListOfCarPictures
import project.lonelywheeler.di.defaults.product.vehicle.motor.DefaultProductCondition
import project.lonelywheeler.di.defaults.product.vehicle.pedestrian.DefaultHumanPoweredVehicleType
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.Product
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import project.lonelywheeler.model.domain.product.vehicle.pedestrian.PedestrianVehicle
import project.lonelywheeler.model.domain.product.vehicle.pedestrian.PedestrianVehicleType

@Module
@InstallIn(ActivityRetainedComponent::class)
class PedestrianVehicleModule {

    @Provides
    fun provideHumanPoweredVehicle(
        id: String?,
        sellerId: String?,
        basicInfo: ProductBasicInfo,
        @DefaultProductCondition condition: Condition,
        @DefaultListOfCarPictures pictures: List<Bitmap>,
        valueFixed: Boolean,
        firstOwner: Boolean,
        sellerInForExchange: Boolean,
        otherInfo: String,
        colorExterior: String,
        colorInterior: String,
        materialInterior: String,
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