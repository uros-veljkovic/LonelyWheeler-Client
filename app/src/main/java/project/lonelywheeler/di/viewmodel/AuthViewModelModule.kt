package project.lonelywheeler.di.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.util.validator.FieldValidator

@Module
@InstallIn(ActivityRetainedComponent::class)
class AuthViewModelModule {

    @Provides
    fun provideAuthViewModelValidator(
    ): FieldValidator {
        return FieldValidator()
    }

    @Provides
    fun provideBooleanMutableLiveData(): MutableLiveData<Boolean> {
        return MutableLiveData()
    }

/*    @Provides
    fun provideMyResponse(): MyResponse<UserEntity> {
        return MyResponse<UserEntity>("", null)
    }*/

}