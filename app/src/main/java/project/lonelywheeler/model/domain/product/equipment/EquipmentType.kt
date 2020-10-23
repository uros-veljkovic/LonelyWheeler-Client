package project.lonelywheeler.model.domain.product.equipment

import com.google.gson.annotations.SerializedName

enum class EquipmentType {

    @SerializedName("Motor vehicle part")
    MotorVehiclePart{
        override fun toString(): String {
            return "Motor vehicle part"
        }
    },

    @SerializedName("Pedestrian vehicle part")
    PedestrianVehiclePart{
        override fun toString(): String {
            return "Pedestrian vehicle part"
        }
    },

    @SerializedName("Other")
    Other{
        override fun toString(): String {
            return "Other"
        }
    },

}