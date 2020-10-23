package project.lonelywheeler.model.domain.product.vehicle.pedestrian

import com.google.gson.annotations.SerializedName

enum class PedestrianVehicleType {
    @SerializedName("Bicycle")
    Bicycle,

    @SerializedName("Scooter")
    Scooter,

    @SerializedName("Roller skates")
    RollerSkates,

    @SerializedName("Skateboard")
    Skateboard,

    @SerializedName("Skis")
    Skis,

    @SerializedName("Other")
    Other
}