package project.lonelywheeler.di.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.offfer.OfferEntity
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.response.MyResponse

@Module
@InstallIn(ActivityRetainedComponent::class)
class SellerOfferViewModelModule {

    @Provides
    fun provideOfferList(
        response: MyResponse<MutableList<OfferEntity>>,
    ): MutableLiveData<MyResponse<MutableList<OfferEntity>>> {
        return MutableLiveData(response)
    }

    @Provides
    fun provideOfferEntityResponseList(): MyResponse<MutableList<OfferEntity>> {
        return MyResponse("", mutableListOf())
    }

}