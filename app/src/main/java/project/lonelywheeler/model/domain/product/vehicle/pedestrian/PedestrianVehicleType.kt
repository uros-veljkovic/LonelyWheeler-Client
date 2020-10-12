package project.lonelywheeler.model.domain.product.vehicle.pedestrian

import com.google.gson.annotations.SerializedName

enum class PedestrianVehicleType {
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