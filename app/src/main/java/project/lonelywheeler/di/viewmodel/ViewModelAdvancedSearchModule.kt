package project.lonelywheeler.di.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.model.observable.query.QueryObservable

@Module
@InstallIn(ActivityRetainedComponent::class)
class ViewModelAdvancedSearchModule {

    @Provides
    fun provideQueryObservable(): QueryObservable {
        return QueryObservable()
    }
/*
    @Provides
    fun provideMLiveDataOfferList(): MutableLiveData<MyResponse<MutableList<OfferEntity>>> {
        return MutableLiveData()
    }*/

}