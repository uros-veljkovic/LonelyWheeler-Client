package project.lonelywheeler.model.domain.product.vehicle.motor

import android.graphics.Bitmap
import project.lonelywheeler.db.entity.product.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.Product
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import project.lonelywheeler.model.domain.product.toEntity
import project.lonelywheeler.util.convertToStringList
import java.util.*
import javax.inject.Inject

class MotorVehicle
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
)

fun MotorVehicle.toEntity(): MotorVehicleEntity {
    return MotorVehicleEntity(
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
        registeredUntil.toLong(),
        numberOfDoors,
        numberOfSeats,
        hasMultimedia
    )
}

fun Date.toLong(): Long {
    return this.time;
}