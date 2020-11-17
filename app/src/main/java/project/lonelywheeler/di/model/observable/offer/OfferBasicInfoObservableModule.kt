package project.lonelywheeler.di.model.observable.offer

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.model.observable.offer.OfferBasicInfoObservable

@Module
@InstallIn(ActivityRetainedComponent::class)
class OfferBasicInfoObservableModule {

    fun provideOfferBasicInfoObservable(): OfferBasicInfoObservable {
        return OfferBasicInfoObservable()
    }
}