package project.lonelywheeler.model.domain.product.motorvehicle

import android.graphics.Bitmap
import project.lonelywheeler.db.entity.product.motorvehicle.MotorVehicleEntity
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
)

fun MotorVehicle.toEntity(): MotorVehicleEntity {
    return MotorVehicleEntity(
        id,
        sellerId,
        basicInfo = this.basicInfo.toEntity(),
        condition,
        pictures = this.pictures.convertToStringList(),
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