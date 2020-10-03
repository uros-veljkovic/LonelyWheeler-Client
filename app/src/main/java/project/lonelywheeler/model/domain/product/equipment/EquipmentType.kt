package project.lonelywheeler.model.domain.product.equipment

import com.google.gson.annotations.SerializedName

enum class EquipmentType {

    @SerializedName("motor vehicle part")
    MotorVehiclePart,

    @SerializedName("human-powered vehicle part")
    HumanPoweredVehiclePart,

    @SerializedName("other")
    Other

}