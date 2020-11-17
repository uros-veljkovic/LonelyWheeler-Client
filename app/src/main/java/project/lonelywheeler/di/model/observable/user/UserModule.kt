package project.lonelywheeler.di.model.observable.user

import android.graphics.Bitmap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.model.observable.user.UserObservable

@Module
@InstallIn(ActivityRetainedComponent::class)
class UserModule {

    @Provides
    fun provideUserObservable(): UserObservable{
        return UserObservable()
    }

}