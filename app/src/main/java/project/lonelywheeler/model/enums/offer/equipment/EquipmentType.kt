package project.lonelywheeler.model.enums.offer.equipment

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.util.extensions.toCamelCase

enum class EquipmentType(val value: String) {


    @SerializedName("Motor vehicle part")
    MotorVehiclePart("Motor vehicle part"),

    @SerializedName("Pedestrian vehicle part")
    PedestrianVehiclePart("Pedestrian vehicle part"),

    @SerializedName("Other")
    Other("Other");

    companion object {
        fun toEnum(s: String): EquipmentType {
            return EquipmentType.valueOf(s.toCamelCase())
        }
    }

}
