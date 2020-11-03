package project.lonelywheeler.di.model.offer.equipment

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
import project.lonelywheeler.di.defaults.offer.vehicle.motor.DefaultPictureList
import project.lonelywheeler.model.domain.offer.OfferBasicInfo
import project.lonelywheeler.model.domain.offer.equipment.Equipment

@Module
@InstallIn(ActivityRetainedComponent::class)
class EquipmentModule {

    @Provides
    fun provideEquipment(
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
        @DefaultObservableString equipmentType: ObservableField<String>
    ): Equipment{
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
