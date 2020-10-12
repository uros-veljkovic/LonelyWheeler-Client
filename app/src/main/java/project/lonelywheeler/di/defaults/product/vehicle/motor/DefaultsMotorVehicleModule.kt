package project.lonelywheeler.di.defaults.product.vehicle.motor

import android.graphics.Bitmap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.vehicle.motor.*
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultProductCondition

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultCarBodyType

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultCarFuelType

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultCarEmissionStandard

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultCarGearboxType

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultCarSteeringWheelSide

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultCarDrivetrain

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultListOfCarPictures

@Module
@InstallIn(ActivityRetainedComponent::class)
class DefaultCarAttributesModule {

    @DefaultProductCondition
    @Provides
    fun provideDefaultProductCondition(): Condition {
        return Condition.Used
    }

    @DefaultListOfCarPictures
    @Provides
    fun provideDefaultListOfCarPictures(): List<Bitmap> {
        return arrayListOf()
    }

    @DefaultCarBodyType
    @Provides
    fun provideDefaultCarBodyType(): CarBodyType {
        return CarBodyType.Coupe
    }

    @DefaultCarFuelType
    @Provides
    fun provideDefaultCarFuelType(): FuelType {
        return FuelType.Diesel
    }

    @DefaultCarEmissionStandard
    @Provides
    fun provideDefaultCarEmissionStandard(): EmissionStandard {
        return EmissionStandard.Euro3
    }

    @DefaultCarGearboxType
    @Provides
    fun provideDefaultCarGearboxType(): GearboxType {
        return GearboxType.Manual
    }

    @DefaultCarSteeringWheelSide
    @Provides
    fun provideDefaultCarSteeringWheelSide(): SteeringWheelSide {
        return SteeringWheelSide.Left
    }

    @DefaultCarDrivetrain
    @Provides
    fun provideDefaultCarDrivetrain(): Drivetrain {
        return Drivetrain.RWD
    }
}