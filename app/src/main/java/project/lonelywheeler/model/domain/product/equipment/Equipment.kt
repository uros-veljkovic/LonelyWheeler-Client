package project.lonelywheeler.model.domain.product.equipment

import android.graphics.Bitmap
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.Product
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import project.lonelywheeler.model.domain.product.toEntity
import project.lonelywheeler.repository.entity.product.equipment.EquipmentEntity
import project.lonelywheeler.util.toListOfByteArrays
import javax.inject.Inject

class Equipment
@Inject
constructor(
    id: Long?,
    sellerId: Long?,
    basicInfo: ProductBasicInfo,
    condition: Condition?,
    pictures: List<Bitmap>,
    valueFixed: Boolean?,
    firstOwner: Boolean?,
    sellerInForExchange: Boolean?,
    otherInfo: String?,
    colorExterior: String?,
    colorInterior: String?,
    materialInterior: String?,
    var equipmentType: EquipmentType
) : Product(
    id,
    sellerId,
    basicInfo,
    condition,
    pictures,
    valueFixed,
    firstOwner,
    sellerInForExchange,
    otherInfo,
    colorExterior,
    colorInterior,
    materialInterior
)

fun Equipment.toEntity() : EquipmentEntity{
    return EquipmentEntity(
        id = this.id,
        sellerId = this.sellerId,
        basicInfo = this.basicInfo.toEntity(),
        condition = this.condition,
        picturesListByteArray = pictures.toListOfByteArrays(),
        valueFixed = this.valueFixed,
        firstOwner = this.firstOwner,
        sellerInForExchange = this.sellerInForExchange,
        otherInfo = this.otherInfo,
        colorExterior = this.colorExterior,
        colorInterior = this.colorInterior,
        materialInterior = this.materialInterior,
        equipmentType = this.equipmentType
    )
}