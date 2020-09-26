package project.lonelywheeler.model.domain.product.humanvehicle

import android.graphics.Bitmap
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.Product
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import javax.inject.Inject

class HumanPoweredVehicle
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
) {

}