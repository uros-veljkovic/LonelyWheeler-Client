package project.lonelywheeler.model.domain.offer.vehicle.motor

import android.graphics.Bitmap
import androidx.databinding.ObservableField
import com.google.gson.Gson
import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.db.entity.offfer.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.model.domain.offer.Offer
import project.lonelywheeler.model.domain.offer.OfferBasicInfo
import project.lonelywheeler.model.domain.offer.toEntity
import project.lonelywheeler.util.convertToStringList
import java.util.*
import javax.inject.Inject

@ActivityRetainedScoped
class MotorVehicle
@Inject
constructor(
    id: String?,
    sellerId: String?,
    basicInfo: OfferBasicInfo,
    condition: ObservableField<String>,
    pictures: MutableList<Bitmap>,
    valueFixed: Boolean,
    firstOwner: Boolean,
    sellerInForExchange: Boolean,
    otherInfo: String,
    colorExterior: String,
    colorInterior: String,
    materialInterior: String,
    var carBodyType: ObservableField<String>,
    var fuelType: ObservableField<String>,
    var emissionStandard: ObservableField<String>,
    var gearboxType: ObservableField<String>,
    var steeringWheelSide: ObservableField<String>,
    var drivetrain: ObservableField<String>,
    var maxSpeed: Int,
    var maxHorsePower: Int,
    var mileage: Int,
    var cubicCapacity: Int,
    var registeredUntil: Date,
    var numberOfDoors: Int,
    var numberOfSeats: Int,
    var hasMultimedia: Boolean,
) : Offer(
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
){
    override fun toString(): String {
        return Gson().toJson(this)
    }
}

fun MotorVehicle.toEntity(): MotorVehicleEntity {
    return MotorVehicleEntity(
        id,
        sellerId,
        basicInfo.toEntity(),
        condition.get()!!,
        pictures.convertToStringList(),
        valueFixed,
        firstOwner,
        sellerInForExchange,
        otherInfo,
        colorExterior,
        colorInterior,
        materialInterior,
        carBodyType.get()!!,
        fuelType.get()!!,
        emissionStandard.get()!!,
        gearboxType.get()!!,
        steeringWheelSide.get()!!,
        drivetrain.get()!!,
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