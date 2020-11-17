package project.lonelywheeler.model.observable.offer.vehicle.motor

import android.graphics.Bitmap
import androidx.databinding.Bindable
import com.google.gson.Gson
import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.BR
import project.lonelywheeler.db.entity.offer.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.model.domain.offer.Condition
import project.lonelywheeler.model.observable.offer.OfferObservable
import project.lonelywheeler.model.domain.offer.vehicle.motor.*
import project.lonelywheeler.model.observable.offer.OfferBasicInfoObservable
import project.lonelywheeler.util.convertToStringList
import java.util.*
import javax.inject.Inject

@ActivityRetainedScoped
class MotorVehicleObservable
@Inject
constructor() : OfferObservable() {

    constructor(
        id: String?,
        sellerId: String?,
        basicInfo: OfferBasicInfoObservable,
        condition: String,
        pictures: MutableList<Bitmap>,
        valueFixed: Boolean,
        firstOwner: Boolean,
        sellerInForExchange: Boolean,
        otherInfo: String,
        colorExterior: String,
        colorInterior: String,
        materialInterior: String,
        carBodyType: String,
        fuelType: String,
        emissionStandard: String,
        gearboxType: String,
        steeringWheelSide: String,
        drivetrain: String,
        maxSpeed: Int,
        maxHorsePower: Int,
        mileage: Int,
        cubicCapacity: Int,
        registeredUntil: Date,
        numberOfDoors: Int,
        numberOfSeats: Int,
        hasMultimedia: Boolean,
    ) : this() {
        this.id = id
        this.sellerId = sellerId
        this.basicInfo = basicInfo
        this.condition = Condition.valueOf(condition)
        this.pictures = pictures
        this.valueFixed = valueFixed
        this.firstOwner = firstOwner
        this.sellerInForExchange = sellerInForExchange
        this.otherInfo = otherInfo
        this.colorExterior = colorExterior
        this.colorInterior = colorInterior
        this.materialInterior = materialInterior
        this.carBodyType = carBodyType
        this.fuelType = fuelType
        this.emissionStandard = emissionStandard
        this.gearboxType = gearboxType
        this.steeringWheelSide = steeringWheelSide
        this.drivetrain = drivetrain
        this.maxSpeed = maxSpeed
        this.maxHorsePower = maxHorsePower
        this.mileage = mileage
        this.cubicCapacity = cubicCapacity
        this.registeredUntil = registeredUntil
        this.numberOfDoors = numberOfDoors
        this.numberOfSeats = numberOfSeats
        this.hasMultimedia = hasMultimedia
    }

    @get:Bindable
    var carBodyType: String = CarBodyType.Coupe.name
        set(value) {
            field = value
            notifyPropertyChanged(BR.carBodyType)
        }

    @get:Bindable
    var fuelType: String = FuelType.Diesel.name
        set(value) {
            field = value
            notifyPropertyChanged(BR.fuelType)
        }

    @get:Bindable
    var emissionStandard: String = EmissionStandard.Euro3.name
        set(value) {
            field = value
            notifyPropertyChanged(BR.emissionStandard)
        }

    @get:Bindable
    var gearboxType: String = GearboxType.Manual.name
        set(value) {
            field = value
            notifyPropertyChanged(BR.gearboxType)
        }

    @get:Bindable
    var steeringWheelSide: String = SteeringWheelSide.Left.name
        set(value) {
            field = value
            notifyPropertyChanged(BR.steeringWheelSide)
        }

    @get:Bindable
    var drivetrain: String = Drivetrain.FWD.name
        set(value) {
            field = value
            notifyPropertyChanged(BR.drivetrain)
        }

    @get:Bindable
    var maxSpeed: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.maxSpeed)
        }

    @get:Bindable
    var maxHorsePower: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.maxHorsePower)
        }

    @get:Bindable
    var mileage: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.mileage)
        }

    @get:Bindable
    var cubicCapacity: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.cubicCapacity)
        }

    @get:Bindable
    var registeredUntil: Date = Date()
        set(value) {
            field = value
            notifyPropertyChanged(BR.registeredUntil)
        }

    @get:Bindable
    var numberOfDoors: Int = 3
        set(value) {
            field = value
            notifyPropertyChanged(BR.numberOfDoors)
        }

    @get:Bindable
    var numberOfSeats: Int = 4
        set(value) {
            field = value
            notifyPropertyChanged(BR.numberOfSeats)
        }

    @get:Bindable
    var hasMultimedia: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.hasMultimedia)
        }


    override fun toString(): String {
        return Gson().toJson(this)
    }

    override fun toEntity(): MotorVehicleEntity {
        return MotorVehicleEntity(
            id,
            sellerId,
            basicInfo.toEntity(),
            condition.name,
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
            hasMultimedia,
        )
    }
}

fun Date.toLong(): Long {
    return this.time;
}