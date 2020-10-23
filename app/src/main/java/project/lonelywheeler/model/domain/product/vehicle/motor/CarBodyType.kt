package project.lonelywheeler.model.domain.product.vehicle.motor

import com.google.gson.annotations.SerializedName

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
    PickUp{
        override fun toString(): String {
            return "Pick up"
        }
    },

    @SerializedName("Sedan")
    Sedan,

    @SerializedName("Station wagon")
    StationWagon
    {
        override fun toString(): String {
            return "Station wagon"
        }
    },

    @SerializedName("Sports car")
    SportsCar{
        override fun toString(): String {
            return "Sports car"
        }
    },

    @SerializedName("SUV")
    SUV,

    @SerializedName("Van")
    Van
}