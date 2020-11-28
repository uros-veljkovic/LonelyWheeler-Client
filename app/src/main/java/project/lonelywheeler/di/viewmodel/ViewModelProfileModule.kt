package project.lonelywheeler.di.viewmodel

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.model.observable.liked.UserLikingSellerObservable
import project.lonelywheeler.model.observable.liked.SellerRateCounterObservable


@Module
@InstallIn(ActivityRetainedComponent::class)
class ViewModelProfileModule {

    @Provides
    fun provideSellerLikingCountObservable(): SellerRateCounterObservable {
        return SellerRateCounterObservable()
    }

    @Provides
    fun provideLikedSellerObservable(): UserLikingSellerObservable {
        return UserLikingSellerObservable()
    }

}