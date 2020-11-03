package project.lonelywheeler.db.entity.offfer.equipment

import androidx.databinding.ObservableField
import project.lonelywheeler.db.entity.offfer.OfferBasicInfoEntity
import project.lonelywheeler.db.entity.offfer.OfferEntity
import project.lonelywheeler.db.entity.offfer.toPojo
import project.lonelywheeler.model.domain.offer.equipment.Equipment
import project.lonelywheeler.util.convertToBitmapList

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
    val equipmentType: String
) : OfferEntity(
    _id,
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

fun EquipmentEntity.toPojo(): Equipment {
    return Equipment(
        _id,
        sellerId,
        basicInfo.toPojo(),
        ObservableField(condition),
        pictures.convertToBitmapList(),
        valueFixed,
        firstOwner,
        sellerInForExchange,
        otherInfo,
        colorExterior,
        colorInterior,
        materialInterior,
        ObservableField(equipmentType)
    )
}