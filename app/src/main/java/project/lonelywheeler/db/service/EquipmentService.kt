package project.lonelywheeler.db.service

import project.lonelywheeler.db.entity.product.ProductEntity
import project.lonelywheeler.db.entity.product.equipment.EquipmentEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.db.service.api.EquipmentApi
import javax.inject.Inject

class EquipmentService
@Inject
constructor(
    private val equipmentApi: EquipmentApi
) {

    suspend fun create(entity: EquipmentEntity): MyResponse<EquipmentEntity> {
        return equipmentApi.create(entity)
    }

    suspend fun readAll(): MyResponse<List<ProductEntity>> {
        return equipmentApi.readAll()
    }

    suspend fun read(entity: EquipmentEntity): MyResponse<EquipmentEntity> {
        return equipmentApi.read(entity._id!!)
    }

    suspend fun update(entity: EquipmentEntity): MyResponse<EquipmentEntity> {
        return equipmentApi.update(entity)
    }

    suspend fun delete(entity: EquipmentEntity): MyResponse<EquipmentEntity> {
        return equipmentApi.delete(entity._id!!)
    }


}