package project.lonelywheeler.di.model.observable.user

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.model.observable.user.UserPersonalInfoObservable

@Module
@InstallIn(ActivityRetainedComponent::class)
class UserPersonalInfoObservableModule {

    @Provides
    fun provideUserPersonalInfoObservable(): UserPersonalInfoObservable{
        return UserPersonalInfoObservable()
    }
}