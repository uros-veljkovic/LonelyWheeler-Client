package project.lonelywheeler.di.model.product.vehicle.motor

import android.graphics.Bitmap
import androidx.databinding.ObservableField
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.di.defaults.primitives.*
import project.lonelywheeler.di.defaults.product.vehicle.motor.*
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import project.lonelywheeler.model.domain.product.vehicle.motor.*
import java.util.*

@Module
@InstallIn(ActivityRetainedComponent::class)
class MotorVehicleModule {

    @Provides
    fun provideMotorVehicle(
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
        @DefaultObservableString carBodyType: ObservableField<String>,
        @DefaultObservableString fuelType: ObservableField<String>,
        @DefaultObservableString emissionStandard: ObservableField<String>,
        @DefaultObservableString gearboxType: ObservableField<String>,
        @DefaultObservableString steeringWheelSide: ObservableField<String>,
        @DefaultObservableString drivetrain: ObservableField<String>,
        @DefaultInt maxSpeed: Int,
        @DefaultInt maxHorsePower: Int,
        @DefaultInt mileage: Int,
        @DefaultInt cubicCapacity: Int,
        @DefaultDate registeredUntil: Date,
        @DefaultInt numberOfDoors: Int,
        @DefaultInt numberOfSeats: Int,
        @DefaultBoolean hasMultimedia: Boolean,
    ): MotorVehicle {
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