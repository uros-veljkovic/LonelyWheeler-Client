package project.lonelywheeler.di.db.response.entity.user

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.util.constants.DEFAULT_RESPONSE_CODE
import project.lonelywheeler.util.constants.DEFAULT_RESPONSE_MESSAGE

@Module
@InstallIn(ActivityRetainedComponent::class)
class MyResponseUserEntityModule {

    @Provides
    fun provideMyResponseUserEntity(): MyResponse<UserEntity> {
        return MyResponse(
            DEFAULT_RESPONSE_CODE,
            DEFAULT_RESPONSE_MESSAGE,
            null
        )
    }

}