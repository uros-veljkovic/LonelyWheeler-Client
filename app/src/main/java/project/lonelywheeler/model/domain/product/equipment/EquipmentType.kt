package project.lonelywheeler.model.domain.product.equipment

import com.google.gson.annotations.SerializedName

enum class EquipmentType {

    @SerializedName("MotorVehiclePart")
    MotorVehiclePart,

    @SerializedName("HumanPoweredVehiclePart")
    HumanPoweredVehiclePart,

    @SerializedName("Other")
    Other

}