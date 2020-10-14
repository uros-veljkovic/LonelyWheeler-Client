package project.lonelywheeler.di.model.product

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.di.defaults.primitives.DefaultDate
import project.lonelywheeler.di.defaults.primitives.DefaultDouble
import project.lonelywheeler.di.defaults.primitives.DefaultNullableInt
import project.lonelywheeler.di.defaults.primitives.DefaultString
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import java.util.*

@InstallIn(ActivityRetainedComponent::class)
@Module
class ProductBasicInfoModule {

//    @Provides
//    fun provideBasicInfo(
//        @DefaultString title: String,
//        @DefaultDouble value: Double,
//        @DefaultDate dateModified: Date,
//        @DefaultString model: String,
//        @DefaultString brand: String,
//        @DefaultNullableInt yearOfProduction: Int?
//    ): ProductBasicInfo {
//        return ProductBasicInfo(
//            title,
//            value,
//            dateModified,
//            model,
//            brand,
//            yearOfProduction
//        )
//    }


    @Provides
    fun provideBasicInfo(
        @DefaultString title: String,
        @DefaultDouble value: Double,
        @DefaultDate dateModified: Date,
        @DefaultString model: String,
        @DefaultString brand: String,
        @DefaultNullableInt yearOfProduction: Int?
    ): ProductBasicInfo {
        return ProductBasicInfo(
            title,
            value,
            dateModified,
            model,
            brand,
            yearOfProduction
        )
    }

}