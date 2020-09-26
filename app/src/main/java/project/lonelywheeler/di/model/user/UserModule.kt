package project.lonelywheeler.di.model.user

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.di.defaults.primitives.DefaultNullableLong
import project.lonelywheeler.model.domain.user.User
import project.lonelywheeler.model.domain.user.UserAccountInfo
import project.lonelywheeler.model.domain.user.UserPersonalInfo

@Module
@InstallIn(ActivityRetainedComponent::class)
class UserModule {

    @Provides
    fun provideUser(
        @DefaultNullableLong id: Long?,
        personalInfo: UserPersonalInfo,
        accountInfo: UserAccountInfo
    ): User {
        return User(id, personalInfo, accountInfo)
    }
}