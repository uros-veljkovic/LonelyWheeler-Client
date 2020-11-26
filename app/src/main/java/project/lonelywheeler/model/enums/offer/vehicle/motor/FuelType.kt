package project.lonelywheeler.model.enums.offer.vehicle.motor

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.util.extensions.toCamelCase

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
    Other;

    companion object {
        fun valueOfSerialized(s: String): FuelType {
            return FuelType.valueOf(s.toCamelCase())
        }
    }
}