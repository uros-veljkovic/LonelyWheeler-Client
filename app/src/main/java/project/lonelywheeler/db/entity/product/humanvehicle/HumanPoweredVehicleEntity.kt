package project.lonelywheeler.db.entity.product.humanvehicle

import project.lonelywheeler.db.entity.product.ProductBasicInfoEntity
import project.lonelywheeler.db.entity.product.toPojo
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.humanvehicle.HumanPoweredVehicle
import project.lonelywheeler.model.domain.product.humanvehicle.HumanPoweredVehicleType
import project.lonelywheeler.util.convertToBitmapList

class HumanPoweredVehicleEntity
constructor(
    val id: String?,
    val sellerId: String?,
    val basicInfo: ProductBasicInfoEntity,
    val condition: Condition,
    val pictures: List<String>,
    val valueFixed: Boolean,
    val firstOwner: Boolean,
    val sellerInForExchange: Boolean,
    val otherInfo: String,
    val colorExterior: String,
    val colorInterior: String,
    val materialInterior: String,
    var humanPoweredVehicleType: HumanPoweredVehicleType
)

fun HumanPoweredVehicleEntity.toPojo(): HumanPoweredVehicle {
    return HumanPoweredVehicle(
        id,
        sellerId,
        basicInfo = this.basicInfo.toPojo(),
        condition,
        pictures = this.pictures.convertToBitmapList(),
        valueFixed,
        firstOwner,
        sellerInForExchange,
        otherInfo,
        colorExterior,
        colorInterior,
        materialInterior,
        humanPoweredVehicleType
    )
}