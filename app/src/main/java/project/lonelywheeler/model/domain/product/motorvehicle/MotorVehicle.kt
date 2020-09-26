package project.lonelywheeler.model.domain.product.motorvehicle

import android.graphics.Bitmap
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.Product
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import java.util.*
import javax.inject.Inject

class MotorVehicle
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
    var carBodyType: CarBodyType,
    var fuelType: FuelType,
    var emissionStandard: EmissionStandard,
    var gearboxType: GearboxType,
    var steeringWheelSide: SteeringWheelSide,
    var drivetrain: Drivetrain,
    var maxSpeed: Int,
    var maxHorsePower: Int,
    var mileage: Int,
    var cubicCapacity: Int,
    var registeredUntil: Date,
    var numberOfDoors: Int,
    var numberOfSeats: Int,
    var hasMultimedia: Boolean,
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