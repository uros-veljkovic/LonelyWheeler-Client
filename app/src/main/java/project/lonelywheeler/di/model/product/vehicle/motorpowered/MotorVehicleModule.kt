package project.lonelywheeler.di.model.product.vehicle.motorpowered

import android.graphics.Bitmap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.di.defaults.primitives.*
import project.lonelywheeler.di.defaults.product.vehicle.motorpowered.*
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.Product
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import project.lonelywheeler.model.domain.product.motorvehicle.*
import java.util.*

@Module
@InstallIn(ActivityRetainedComponent::class)
class MotorVehicleModule {

    @Provides
    fun provideMotorVehicle(
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
        @DefaultCarBodyType carBodyType: CarBodyType,
        @DefaultCarFuelType fuelType: FuelType,
        @DefaultCarEmissionStandard emissionStandard: EmissionStandard,
        @DefaultCarGearboxType gearboxType: GearboxType,
        @DefaultCarSteeringWheelSide steeringWheelSide: SteeringWheelSide,
        @DefaultCarDrivetrain drivetrain: Drivetrain,
        @DefaultInt maxSpeed: Int,
        @DefaultInt maxHorsePower: Int,
        @DefaultInt mileage: Int,
        @DefaultInt cubicCapacity: Int,
        @DefaultDate registeredUntil: Date,
        @DefaultInt numberOfDoors: Int,
        @DefaultInt numberOfSeats: Int,
        @DefaultBoolean hasMultimedia: Boolean,
    ): Product {
        return MotorVehicle(
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
            carBodyType,
            fuelType,
            emissionStandard,
            gearboxType,
            steeringWheelSide,
            drivetrain,
            maxSpeed,
            maxHorsePower,
            mileage,
            cubicCapacity,
            registeredUntil,
            numberOfDoors,
            numberOfSeats,
            hasMultimedia
        )
    }

}