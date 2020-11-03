package project.lonelywheeler.di.model.offer.vehicle.motor

import android.graphics.Bitmap
import androidx.databinding.ObservableField
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.di.defaults.primitives.*
import project.lonelywheeler.di.defaults.offer.vehicle.motor.*
import project.lonelywheeler.model.domain.offer.OfferBasicInfo
import project.lonelywheeler.model.domain.offer.vehicle.motor.*
import java.util.*

@Module
@InstallIn(ActivityRetainedComponent::class)
class MotorVehicleModule {

    @Provides
    fun provideMotorVehicle(
        @DefaultNullableString id: String?,
        @DefaultNullableString sellerId: String?,
        basicInfo: OfferBasicInfo,
        @DefaultObservableString condition: ObservableField<String>,
        @DefaultPictureList pictures: MutableList<Bitmap>,
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