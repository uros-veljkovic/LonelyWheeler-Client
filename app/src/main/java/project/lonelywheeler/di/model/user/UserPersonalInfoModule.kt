package project.lonelywheeler.di.model.user

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.di.defaults.primitives.DefaultString
import project.lonelywheeler.model.domain.user.UserPersonalInfo
import javax.inject.Qualifier

@Module
@InstallIn(ActivityRetainedComponent::class)
class UserPersonalInfoModule {

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
}