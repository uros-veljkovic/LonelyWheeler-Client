package project.lonelywheeler.model.enums.offer.vehicle.motor

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.util.extensions.toCamelCase

enum class CarBodyType {

    @SerializedName("Coupe")
    Coupe,

    @SerializedName("Convertible")
    Convertible,

    @SerializedName("Hatchback")
    Hatchback,

    @SerializedName("Minivan")
    Minivan,

    @SerializedName("Truck")
    Truck,

    @SerializedName("Pick up")
    PickUp,

    @SerializedName("Sedan")
    Sedan,

    @SerializedName("Station wagon")
    StationWagon,

    @SerializedName("Sports car")
    SportsCar,

    @SerializedName("SUV")
    SUV,

    @SerializedName("Van")
    Van;

    companion object {
        fun valueOfSerialized(s: String): CarBodyType {
            return CarBodyType.valueOf(s.toCamelCase())
        }
    }
}