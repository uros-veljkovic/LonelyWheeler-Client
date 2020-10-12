package project.lonelywheeler.db.entity.product.vehicle.motor

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.db.entity.product.ProductBasicInfoEntity
import project.lonelywheeler.db.entity.product.toPojo
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.vehicle.motor.*
import project.lonelywheeler.util.convertToBitmapList
import java.util.*

class MotorVehicleEntity(
    @SerializedName("id")
    val id: String?,

    @SerializedName("seller_id")
    val sellerId: String?,

    @SerializedName("basic_info")
    val basicInfo: ProductBasicInfoEntity,

    @SerializedName("condition")
    val condition: Condition,

    @SerializedName("pictures")
    val pictures: List<String>,

    @SerializedName("value_fixed")
    val valueFixed: Boolean,

    @SerializedName("first_owner")
    val firstOwner: Boolean,

    @SerializedName("seller_in_for_exchange")
    val sellerInForExchange: Boolean,

    @SerializedName("other_info")
    val otherInfo: String,

    @SerializedName("color_exterior")
    val colorExterior: String,

    @SerializedName("color_interior")
    val colorInterior: String,

    @SerializedName("material_interior")
    val materialInterior: String,

    @SerializedName("car_body_type")
    val carBodyType: CarBodyType,

    @SerializedName("fuel_type")
    val fuelType: FuelType,

    @SerializedName("emission_standard")
    val emissionStandard: EmissionStandard,

    @SerializedName("gearbox_type")
    val gearboxType: GearboxType,

    @SerializedName("steering_wheel_side")
    val steeringWheelSide: SteeringWheelSide,

    @SerializedName("drivetrain")
    val drivetrain: Drivetrain,

    @SerializedName("max_speed")
    val maxSpeed: Int,

    @SerializedName("max_horse_power")
    val maxHorsePower: Int,

    @SerializedName("mileage")
    val mileage: Int,

    @SerializedName("cubic_capacity")
    val cubicCapacity: Int,

    @SerializedName("registered_until")
    val registeredUntil: Long,

    @SerializedName("number_of_doors")
    val numberOfDoors: Int,

    @SerializedName("number_of_seats")
    val numberOfSeats: Int,

    @SerializedName("has_multimedia")
    val hasMultimedia: Boolean,
)

fun MotorVehicleEntity.toPojo(): MotorVehicle {
    return MotorVehicle(
        id,
        sellerId,
        basicInfo = this.basicInfo.toPojo(),
        condition,
        pictures = this.pictures.convertToBitmapList(),
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
        registeredUntil.toDate(),
        numberOfDoors,
        numberOfSeats,
        hasMultimedia
    )
}

fun Long.toDate(): Date {
    return Date(this);
}