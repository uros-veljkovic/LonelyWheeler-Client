package project.lonelywheeler.model.domain.product.humanvehicle

import com.google.gson.annotations.SerializedName

enum class HumanPoweredVehicleType {
    @SerializedName("bicycle")
    Bicycle,

    @SerializedName("scooter")
    Scooter,

    @SerializedName("roller skates")
    RollerSkates,

    @SerializedName("skateboard")
    Skateboard,

    @SerializedName("skis")
    Skis,

    @SerializedName("other")
    Other
}