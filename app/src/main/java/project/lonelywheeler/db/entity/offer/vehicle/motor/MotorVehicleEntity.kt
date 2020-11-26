
package project.lonelywheeler.db.entity.offer.vehicle.motor

import com.google.gson.GsonBuilder
import project.lonelywheeler.db.entity.offer.OfferBasicInfoEntity
import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.model.enums.offer.Condition
import project.lonelywheeler.model.enums.offer.vehicle.motor.*
import project.lonelywheeler.model.observable.offer.vehicle.motor.MotorVehicleObservable
import project.lonelywheeler.util.extensions.convertToBitmapList
import java.util.*

class MotorVehicleEntity(
    _id: String?,
    sellerId: String?,
    basicInfo: OfferBasicInfoEntity,
    condition: String,
    pictures: MutableList<String>,
    valueFixed: Boolean,
    firstOwner: Boolean,
    sellerInForExchange: Boolean,
    otherInfo: String,
    colorExterior: String,
    colorInterior: String,
    materialInterior: String,
    val carBodyType: String,
    val fuelType: String,
    val emissionStandard: String,
    val gearboxType: String,
    val steeringWheelSide: String,
    val drivetrain: String,
    val maxSpeed: Int,
    val maxHorsePower: Int,
    val mileage: Int,
    val cubicCapacity: Int,
    val registeredUntil: Long,
    val numberOfDoors: Int,
    val numberOfSeats: Int,
    val hasMultimedia: Boolean,
) : OfferEntity(
    _id,
    sellerId,
    basicInfo,
    MotorVehicleEntity::class.simpleName.toString(),
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


    override fun toString(): String {
        val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
        return gsonBuilder.toJson(this)
    }

    override fun toObservable(): MotorVehicleObservable {
        return MotorVehicleObservable(
            _id,
            sellerId,
            basicInfo = this.basicInfo.toObservable(),
            Condition.valueOf(condition),
            pictures = this.pictures.convertToBitmapList(),
            valueFixed,
            firstOwner,
            sellerInForExchange,
            otherInfo,
            colorExterior,
            colorInterior,
            materialInterior,
            CarBodyType.valueOf(carBodyType),
            FuelType.valueOf(fuelType),
            EmissionStandard.valueOf(emissionStandard),
            GearboxType.valueOf(gearboxType),
            SteeringWheelSide.valueOf(steeringWheelSide),
            Drivetrain.valueOf(drivetrain),
            maxSpeed,
            maxHorsePower,
            mileage,
            cubicCapacity,
            registeredUntil.toDate(),
            numberOfDoors,
            numberOfSeats,
            hasMultimedia
        )
    }

    fun toOffer(): OfferEntity {
        return OfferEntity(_id,
            sellerId,
            basicInfo,
            entityClassSimpleName,
            condition,
            pictures,
            valueFixed,
            firstOwner,
            sellerInForExchange,
            otherInfo,
            colorExterior,
            colorInterior,
            materialInterior)
    }
}


fun Long.toDate(): Date {
    return Date(this);
}
