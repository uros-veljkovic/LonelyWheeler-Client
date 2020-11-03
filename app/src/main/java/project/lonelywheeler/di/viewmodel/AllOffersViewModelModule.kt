package project.lonelywheeler.di.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.offfer.OfferEntity
import project.lonelywheeler.db.response.MyResponse
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProductListResponse


@Module
@InstallIn(ActivityRetainedComponent::class)
class AllOffersViewModelModule {

    @ProductListResponse
    @Provides
    fun provideMotorVehicleResponseMutableLiveData(
        @MotorVehicleResponse
        response: MyResponse<List<OfferEntity>>,
    ): MutableLiveData<MyResponse<List<OfferEntity>>> {
        return MutableLiveData(response)
    }

    @MotorVehicleResponse
    @Provides
    fun provideMotorVehicleResponse(
        list: List<OfferEntity>,
    ): MyResponse<List<OfferEntity>> {
        return MyResponse("", list)
    }

    @Provides
    fun provideProductList(): List<OfferEntity> {
        return listOf()
    }

}