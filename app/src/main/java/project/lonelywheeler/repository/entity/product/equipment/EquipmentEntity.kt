package project.lonelywheeler.repository.entity.product.equipment

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.equipment.Equipment
import project.lonelywheeler.model.domain.product.equipment.EquipmentType
import project.lonelywheeler.repository.entity.product.ProductBasicInfoEntity
import project.lonelywheeler.repository.entity.product.toPojo
import project.lonelywheeler.util.toBitmapList

class EquipmentEntity
constructor(
    @SerializedName("id")
    val id: Long?,

    @SerializedName("seller_id")
    val sellerId: Long?,

    @SerializedName("basic_info")
    val basicInfo: ProductBasicInfoEntity,

    @SerializedName("condition")
    val condition: Condition?,

    @SerializedName("pictures_byte_array")
    val picturesListByteArray: List<ByteArray>,

    @SerializedName("value_fixed")
    val valueFixed: Boolean?,

    @SerializedName("first_owner")
    val firstOwner: Boolean?,

    @SerializedName("seller_in_for_exchange")
    val sellerInForExchange: Boolean?,

    @SerializedName("other_info")
    val otherInfo: String?,

    @SerializedName("color_exterior")
    val colorExterior: String?,

    @SerializedName("color_interior")
    val colorInterior: String?,

    @SerializedName("material_interior")
    val materialInterior: String?,

    @SerializedName("equipment_type")
    val equipmentType: EquipmentType
)

fun EquipmentEntity.toPojo() : Equipment{
    return Equipment(
        id = this.id,
        sellerId = this.sellerId,
        basicInfo = this.basicInfo.toPojo(),
        condition = this.condition,
        pictures = picturesListByteArray.toBitmapList(),
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