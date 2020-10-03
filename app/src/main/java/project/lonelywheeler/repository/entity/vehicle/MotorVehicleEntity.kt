package project.lonelywheeler.repository.entity.vehicle

import android.graphics.Bitmap
import project.lonelywheeler.model.domain.product.motorvehicle.*
import project.lonelywheeler.repository.entity.product.ProductBasicInfoEntity
import java.util.*
import java.util.concurrent.locks.Condition

class MotorVehicleEntity
constructor(
    val id : Long?,
    val sellerID : Long?,
    val basicInfo : ProductBasicInfoEntity,
    val condition: Condition?,
    val pictures: List<Bitmap>,
    val valueFixed: Boolean?,
    val firstOwner: Boolean?,
    val sellerInForExchange: Boolean?,
    val otherInfo: String?,
    val colorExterior: String?,
    val colorInterior: String?,
    val materialInterior: String?,
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

    ){
}