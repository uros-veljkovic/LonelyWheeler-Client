package project.lonelywheeler.di.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.db.response.MyResponse
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MyResponseListOffer

@Module
@InstallIn(ActivityRetainedComponent::class)
class ViewModelAllOffersModule {

    @MyResponseListOffer
    @Provides
    fun provideMotorVehicleResponseMutableLiveData(
        @MyResponseListMotorVehicle
        response: MyResponse<List<OfferEntity>>,
    ): MutableLiveData<MyResponse<List<OfferEntity>>> {
        return MutableLiveData(response)
    }

    @MyResponseListMotorVehicle
    @Provides
    fun provideMotorVehicleResponse(
        list: List<OfferEntity>,
    ): MyResponse<List<OfferEntity>> {
        return MyResponse(-1, "", list)
    }

    @Provides
    fun provideProductList(): List<OfferEntity> {
        return listOf()
    }

}