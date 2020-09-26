package project.lonelywheeler.di.model.user

import android.graphics.Bitmap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.di.defaults.primitives.DefaultInt
import project.lonelywheeler.di.defaults.primitives.DefaultListOfLong
import project.lonelywheeler.di.defaults.primitives.DefaultNullableBitmap
import project.lonelywheeler.di.defaults.primitives.DefaultString
import project.lonelywheeler.model.domain.user.UserAccountInfo

@Module
@InstallIn(ActivityRetainedComponent::class)
class UserAccountInfoModuleModule {

    @Provides
    fun provideUserAccountInfo(
        @DefaultString username: String,
        @DefaultString email: String,
        @DefaultString password: String,
        @DefaultNullableBitmap picture: Bitmap?,
        @DefaultInt timesSupported: Int,
        @DefaultInt timesReported: Int,
        @DefaultListOfLong offersLiked: List<Long>,
        @DefaultListOfLong myOffers: List<Long>
    ): UserAccountInfo {
        return UserAccountInfo(
            username,
            email,
            password,
            picture,
            timesSupported,
            timesReported,
            offersLiked,
            myOffers
        )
    }

}