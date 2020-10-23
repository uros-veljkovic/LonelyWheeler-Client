package project.lonelywheeler.model.domain.product.vehicle.motor

import com.google.gson.annotations.SerializedName

enum class FuelType {

    @SerializedName("Gasoline")
    Gasoline,

    @SerializedName("Diesel")
    Diesel,

    @SerializedName("Bio diesel")
    BioDiesel{
        override fun toString(): String {
            return "Bio diesel"
        }
    },

    @SerializedName("Gas")
    Gas,

    @SerializedName("Methane")
    Methane,

    @SerializedName("Other")
    Other
}