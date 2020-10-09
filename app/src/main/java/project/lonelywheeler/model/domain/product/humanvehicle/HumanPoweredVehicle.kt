package project.lonelywheeler.model.domain.product.humanvehicle

import android.graphics.Bitmap
import project.lonelywheeler.db.entity.vehicle.HumanPoweredVehicleEntity
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.Product
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import project.lonelywheeler.model.domain.product.toEntity
import project.lonelywheeler.util.convertToStringList
import javax.inject.Inject

class HumanPoweredVehicle
@Inject
constructor(
    id: String?,
    sellerId: String?,
    basicInfo: ProductBasicInfo,
    condition: Condition,
    pictures: List<Bitmap>,
    valueFixed: Boolean,
    firstOwner: Boolean,
    sellerInForExchange: Boolean,
    otherInfo: String,
    colorExterior: String,
    colorInterior: String,
    materialInterior: String,
    var humanPoweredVehicleType: HumanPoweredVehicleType
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

fun HumanPoweredVehicle.toEntity(): HumanPoweredVehicleEntity {
    return HumanPoweredVehicleEntity(
        id,
        sellerId,
        basicInfo.toEntity(),
        condition,
        pictures.convertToStringList(),
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