package project.lonelywheeler.di.model.product.vehicle.pedestrian

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
import project.lonelywheeler.di.defaults.product.vehicle.motor.DefaultPictureList
import project.lonelywheeler.model.domain.product.Product
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import project.lonelywheeler.model.domain.product.vehicle.pedestrian.PedestrianVehicle

@Module
@InstallIn(ActivityRetainedComponent::class)
class PedestrianVehicleModule {

    @Provides
    fun provideHumanPoweredVehicle(
        @DefaultNullableString id: String?,
        @DefaultNullableString sellerId: String?,
        basicInfo: ProductBasicInfo,
        @DefaultObservableString condition: ObservableField<String>,
        @DefaultPictureList pictures: MutableList<Bitmap>,
        @DefaultBoolean valueFixed: Boolean,
        @DefaultBoolean firstOwner: Boolean,
        @DefaultBoolean sellerInForExchange: Boolean,
        @DefaultString otherInfo: String,
        @DefaultString colorExterior: String,
        @DefaultString colorInterior: String,
        @DefaultString materialInterior: String,
        @DefaultObservableString pedestrianVehicleType: ObservableField<String>

    ): PedestrianVehicle {
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