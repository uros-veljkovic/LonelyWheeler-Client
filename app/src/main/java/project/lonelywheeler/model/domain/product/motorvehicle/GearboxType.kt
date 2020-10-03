package project.lonelywheeler.model.domain.product.motorvehicle

import com.google.gson.annotations.SerializedName

enum class GearboxType {

    @SerializedName("manual")
    Manual,

    @SerializedName("automatic")
    Automatic
}