package project.lonelywheeler.model.domain.product.vehicle.motor

import com.google.gson.annotations.SerializedName

enum class FuelType {

    @SerializedName("gasoline")
    Gasoline,

    @SerializedName("diesel")
    Diesel,

    @SerializedName("bioDiesel")
    BioDiesel,

    @SerializedName("gas")
    Gas,

    @SerializedName("methane")
    Methane,

    @SerializedName("other")
    Other
}