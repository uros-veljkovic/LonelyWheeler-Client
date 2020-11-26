package project.lonelywheeler.di.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.offer.equipment.EquipmentEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.model.observable.offer.equipment.EquipmentObservable
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MyResponseEquipment

@Module
@InstallIn(ActivityRetainedComponent::class)
class ViewModelEquipmentModule {

    @MyResponseEquipment
    @Provides
    fun provideEquipmentResponseMutableLiveData(
        response: MyResponse<EquipmentEntity>,
    ): MutableLiveData<MyResponse<EquipmentEntity>> {
        return MutableLiveData(response)
    }

    @Provides
    fun provideEquipmentMLD(equipment: EquipmentObservable): MutableLiveData<EquipmentObservable> {
        return MutableLiveData(equipment)
    }

}