package project.lonelywheeler.db.entity.offer.equipment

import project.lonelywheeler.db.entity.offer.OfferBasicInfoEntity
import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.model.enums.offer.equipment.EquipmentType
import project.lonelywheeler.model.observable.offer.equipment.EquipmentObservable
import project.lonelywheeler.util.extensions.convertToBitmapList

class EquipmentEntity
constructor(
    _id: String?,
    sellerId: String?,
    basicInfo: OfferBasicInfoEntity,
    condition: String,
    pictures: MutableList<String>,
    valueFixed: Boolean,
    firstOwner: Boolean,
    sellerInForExchange: Boolean,
    otherInfo: String,
    colorExterior: String,
    colorInterior: String,
    materialInterior: String,
    val equipmentType: String,
) : OfferEntity(
    _id,
    sellerId,
    basicInfo,
    EquipmentEntity::class.simpleName.toString(),
    condition,
    pictures,
    valueFixed,
    firstOwner,
    sellerInForExchange,
    otherInfo,
    colorExterior,
    colorInterior,
    materialInterior
) {
    override fun toObservable(): EquipmentObservable {
        val basicInfoObservable = basicInfo.toObservable()
        val equipmentTypeEnum = equipmentType
        val pictureList = pictures.convertToBitmapList()

        return EquipmentObservable().apply {
            id = _id
            sellerId = sellerId
            basicInfo = basicInfoObservable
            equipmentType = EquipmentType.valueOf(equipmentTypeEnum)
            colorExterior = colorExterior
            colorInterior = colorInterior
            materialInterior = materialInterior
            pictures = pictureList
            firstOwner = firstOwner
            sellerInForExchange = sellerInForExchange
            otherInfo = otherInfo
            condition = condition
        }
    }
}
