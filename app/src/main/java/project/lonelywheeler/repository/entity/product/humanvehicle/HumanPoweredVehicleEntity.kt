package project.lonelywheeler.repository.entity.product.humanvehicle

import android.graphics.Bitmap
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import project.lonelywheeler.model.domain.product.humanvehicle.HumanPoweredVehicle
import project.lonelywheeler.model.domain.product.humanvehicle.HumanPoweredVehicleType
import project.lonelywheeler.repository.entity.product.ProductBasicInfoEntity
import project.lonelywheeler.repository.entity.product.toPojo

class HumanPoweredVehicleEntity
constructor(
    val id: Long?,
    val sellerId: Long?,
    val basicInfo: ProductBasicInfoEntity,
    val condition: Condition?,
    val pictures: List<Bitmap>,
    val valueFixed: Boolean?,
    val firstOwner: Boolean?,
    val sellerInForExchange: Boolean?,
    val otherInfo: String?,
    val colorExterior: String?,
    val colorInterior: String?,
    val materialInterior: String?,
    var humanPoweredVehicleType: HumanPoweredVehicleType
)

fun HumanPoweredVehicleEntity.toPojo(): HumanPoweredVehicle {
    return HumanPoweredVehicle(
        id,
        sellerId,
        basicInfo = this.basicInfo.toPojo(),
        condition,
        pictures,
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