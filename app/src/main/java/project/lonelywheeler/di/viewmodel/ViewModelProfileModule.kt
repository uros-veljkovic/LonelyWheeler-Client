package project.lonelywheeler.di.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.response.MyResponse
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SellerProfileEntity


@Module
@InstallIn(ActivityRetainedComponent::class)
class ViewModelProfileModule {

    @Provides
    fun provideMLiveDataResponseUserEntity(): MutableLiveData<MyResponse<UserEntity>> {
        return MutableLiveData()
    }

    @SellerProfileEntity
    @Provides
    fun provideMyResponseUserEntity(): MyResponse<UserEntity> {
        return MyResponse(-1, "", null)
    }

}