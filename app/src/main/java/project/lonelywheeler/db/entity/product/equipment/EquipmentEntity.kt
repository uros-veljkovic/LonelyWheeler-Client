package project.lonelywheeler.db.entity.product.equipment

import androidx.databinding.ObservableField
import com.google.gson.annotations.SerializedName
import project.lonelywheeler.db.entity.product.ProductBasicInfoEntity
import project.lonelywheeler.db.entity.product.toPojo
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.equipment.Equipment
import project.lonelywheeler.model.domain.product.equipment.EquipmentType
import project.lonelywheeler.util.convertToBitmapList

class EquipmentEntity
constructor(
    @SerializedName("_id")
    val id: String?,
    val sellerId: String?,
    val basicInfo: ProductBasicInfoEntity,
    val condition: String,
    val pictures: MutableList<String>,
    val valueFixed: Boolean,
    val firstOwner: Boolean,
    val sellerInForExchange: Boolean,
    val otherInfo: String,
    val colorExterior: String,
    val colorInterior: String,
    val materialInterior: String,
    val equipmentType: String
)

fun EquipmentEntity.toPojo(): Equipment {
    return Equipment(
        id,
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