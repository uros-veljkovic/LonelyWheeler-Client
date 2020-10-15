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
    @SerializedName("id")
    val id: String?,

    @SerializedName("seller_id")
    val sellerId: String?,

    @SerializedName("basic_info")
    val basicInfo: ProductBasicInfoEntity,

    @SerializedName("condition")
    val condition: String,

    @SerializedName("pictures")
    val pictures: List<String>,

    @SerializedName("value_fixed")
    val valueFixed: Boolean,

    @SerializedName("first_owner")
    val firstOwner: Boolean,

    @SerializedName("seller_in_for_exchange")
    val sellerInForExchange: Boolean,

    @SerializedName("other_info")
    val otherInfo: String,

    @SerializedName("color_exterior")
    val colorExterior: String,

    @SerializedName("color_interior")
    val colorInterior: String,

    @SerializedName("material_interior")
    val materialInterior: String,

    @SerializedName("equipment_type")
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