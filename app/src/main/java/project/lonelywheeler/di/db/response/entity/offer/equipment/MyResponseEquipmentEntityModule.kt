package project.lonelywheeler.di.db.response.entity.offer.equipment

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import project.lonelywheeler.db.entity.offer.equipment.EquipmentEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.util.constants.DEFAULT_RESPONSE_CODE
import project.lonelywheeler.util.constants.DEFAULT_RESPONSE_MESSAGE

@Module
@InstallIn(ActivityRetainedComponent::class)
class MyResponseEquipmentEntityModule {

    @Provides
    fun provideMyResponseEquipmentEntity(): MyResponse<EquipmentEntity> {
        return MyResponse(
            DEFAULT_RESPONSE_CODE,
            DEFAULT_RESPONSE_MESSAGE,
            null
        )
    }
}