package project.lonelywheeler.di.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.product.ProductEntity
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
        response: MyResponse<List<ProductEntity>>
    ): MutableLiveData<MyResponse<List<ProductEntity>>> {
        return MutableLiveData(response)
    }

    @Provides
    fun provideMotorVehicleResponse(
        list: List<ProductEntity>
    ): MyResponse<List<ProductEntity>> {
        return MyResponse("", list)
    }

    @Provides
    fun provideProductList(): List<ProductEntity> {
        return listOf()
    }

}