package project.lonelywheeler.model.domain.product.vehicle.motor

import com.google.gson.annotations.SerializedName

enum class GearboxType {

    @SerializedName("manual")
    Manual,

    @SerializedName("automatic")
    Automatic
}