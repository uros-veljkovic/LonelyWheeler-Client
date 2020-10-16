package project.lonelywheeler.di.model.user

import android.graphics.Bitmap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.di.defaults.primitives.*
import project.lonelywheeler.model.domain.user.User
import project.lonelywheeler.model.domain.user.UserAccountInfo
import project.lonelywheeler.model.domain.user.UserPersonalInfo

@Module
@InstallIn(ActivityRetainedComponent::class)
class UserModule {

    @Provides
    fun provideUser(
        @DefaultNullableString id: String?,
        personalInfo: UserPersonalInfo,
        accountInfo: UserAccountInfo
    ): User {
        return User(id, personalInfo, accountInfo)
    }

    @Provides
    fun provideUserPersonalInfo(
        @DefaultString firstName: String,
        @DefaultString lastName: String,
        @DefaultString city: String,
        @DefaultString street: String,
        @DefaultString mobileNumber: String
    ): UserPersonalInfo {
        return UserPersonalInfo(
            firstName,
            lastName,
            city,
            street,
            mobileNumber
        )
    }

    @Provides
    fun provideUserAccountInfo(
        @DefaultString username: String,
        @DefaultString email: String,
        @DefaultString password: String,
        @DefaultString confirmPassword: String,
        @DefaultNullableBitmap picture: Bitmap?,
        @DefaultInt timesSupported: Int,
        @DefaultInt timesReported: Int,
        @DefaultListOfLong offersLiked: MutableList<Long>,
        @DefaultListOfLong myOffers: MutableList<Long>
    ): UserAccountInfo {
        return UserAccountInfo(
            username,
            email,
            password,
            confirmPassword,
            picture,
            timesSupported,
            timesReported,
            offersLiked,
            myOffers
        )
    }
}