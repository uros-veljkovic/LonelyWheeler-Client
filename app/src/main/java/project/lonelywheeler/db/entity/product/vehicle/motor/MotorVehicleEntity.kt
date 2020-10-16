package project.lonelywheeler.db.entity.product.vehicle.motor

import androidx.databinding.ObservableField
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import project.lonelywheeler.db.entity.product.ProductBasicInfoEntity
import project.lonelywheeler.db.entity.product.toPojo
import project.lonelywheeler.model.domain.product.vehicle.motor.MotorVehicle
import project.lonelywheeler.util.convertToBitmapList
import java.util.*

class MotorVehicleEntity(
    @SerializedName("_id")
    val id: String?,
    val sellerId: String?,
    val basicInfo: ProductBasicInfoEntity,
    val condition: String,
    val pictures: MutableList<String>,
    val valueFixed: Boolean,
    val firstOwner: Boolean,
    val sellerInForExchange: Boolean,
    val otherInfo: String,
    val colorExterior: String,
    val colorInterior: String,
    val materialInterior: String,
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
) {
    override fun toString(): String {
        val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
        return gsonBuilder.toJson(this)
    }
}

fun MotorVehicleEntity.toPojo(): MotorVehicle {
    return MotorVehicle(
        id,
        sellerId,
        basicInfo = this.basicInfo.toPojo(),
        ObservableField(condition),
        pictures = this.pictures.convertToBitmapList(),
        valueFixed,
        firstOwner,
        sellerInForExchange,
        otherInfo,
        colorExterior,
        colorInterior,
        materialInterior,
        ObservableField(carBodyType),
        ObservableField(fuelType),
        ObservableField(emissionStandard),
        ObservableField(gearboxType),
        ObservableField(steeringWheelSide),
        ObservableField(drivetrain),
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

fun Long.toDate(): Date {
    return Date(this);
}