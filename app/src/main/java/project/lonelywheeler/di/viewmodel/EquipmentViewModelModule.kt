package project.lonelywheeler.di.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.offfer.equipment.EquipmentEntity
import project.lonelywheeler.db.response.MyResponse
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EquipmentResponse

@Module
@InstallIn(ActivityRetainedComponent::class)
class EquipmentViewModelModule {

    @EquipmentResponse
    @Provides
    fun provideEquipmentResponseMutableLiveData():
            MutableLiveData<MyResponse<EquipmentEntity>> {
        return MutableLiveData()
    }

    fun provideEquipmentResponse() : MyResponse<EquipmentEntity>{
        return MyResponse("", null)
    }

}