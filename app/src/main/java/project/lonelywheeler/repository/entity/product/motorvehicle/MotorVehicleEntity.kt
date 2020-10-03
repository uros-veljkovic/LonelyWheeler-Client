package project.lonelywheeler.repository.entity.product.motorvehicle

import android.graphics.Bitmap
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import project.lonelywheeler.model.domain.product.motorvehicle.*
import project.lonelywheeler.repository.entity.product.ProductBasicInfoEntity
import project.lonelywheeler.repository.entity.product.toPojo
import java.util.*

class MotorVehicleEntity
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
    val carBodyType: CarBodyType,
    val fuelType: FuelType,
    val emissionStandard: EmissionStandard,
    val gearboxType: GearboxType,
    val steeringWheelSide: SteeringWheelSide,
    val drivetrain: Drivetrain,
    val maxSpeed: Int,
    val maxHorsePower: Int,
    val mileage: Int,
    val cubicCapacity: Int,
    val registeredUntil: Date,
    val numberOfDoors: Int,
    val numberOfSeats: Int,
    val hasMultimedia: Boolean,
)

fun MotorVehicleEntity.toPojo(): MotorVehicle {
    return MotorVehicle(
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
        carBodyType,
        fuelType,
        emissionStandard,
        gearboxType,
        steeringWheelSide,
        drivetrain,
        maxSpeed,
        maxHorsePower,
        mileage,
        cubicCapacity,
        registeredUntil,
        numberOfDoors,
        numberOfSeats,
        hasMultimedia
    )
}