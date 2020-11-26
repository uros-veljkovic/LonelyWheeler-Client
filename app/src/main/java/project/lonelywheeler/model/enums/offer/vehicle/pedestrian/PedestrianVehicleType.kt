package project.lonelywheeler.model.enums.offer.vehicle.pedestrian

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.util.extensions.toCamelCase

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
    Other;

    companion object {
        fun toEnum(s: String): PedestrianVehicleType {
            return PedestrianVehicleType.valueOf(s.toCamelCase())
        }
    }
}