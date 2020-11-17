package project.lonelywheeler.di.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.offer.equipment.EquipmentEntity
import project.lonelywheeler.db.response.MyResponse
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EquipmentResponse

@Module
@InstallIn(ActivityRetainedComponent::class)
class ViewModelEquipmentModule {

    @EquipmentResponse
    @Provides
    fun provideEquipmentResponseMutableLiveData(
        response: MyResponse<EquipmentEntity>,
    ): MutableLiveData<MyResponse<EquipmentEntity>> {
        return MutableLiveData(response)
    }

}