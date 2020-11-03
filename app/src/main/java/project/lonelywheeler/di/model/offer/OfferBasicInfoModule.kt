package project.lonelywheeler.di.model.offer

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.di.defaults.primitives.DefaultDate
import project.lonelywheeler.di.defaults.primitives.DefaultDouble
import project.lonelywheeler.di.defaults.primitives.DefaultNullableInt
import project.lonelywheeler.di.defaults.primitives.DefaultString
import project.lonelywheeler.model.domain.offer.OfferBasicInfo
import java.util.*

@InstallIn(ActivityRetainedComponent::class)
@Module
class OfferBasicInfoModule {

    @Provides
    fun provideBasicInfo(
        @DefaultString title: String,
        @DefaultDouble value: Double,
        @DefaultDate dateModified: Date,
        @DefaultString model: String,
        @DefaultString brand: String,
        @DefaultNullableInt yearOfProduction: Int?
    ): OfferBasicInfo {
        return OfferBasicInfo(
            title,
            value,
            dateModified,
            model,
            brand,
            yearOfProduction
        )
    }

}