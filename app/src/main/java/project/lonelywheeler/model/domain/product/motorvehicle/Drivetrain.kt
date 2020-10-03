package project.lonelywheeler.model.domain.product.motorvehicle

import com.google.gson.annotations.SerializedName

enum class Drivetrain {

    @SerializedName("awd")
    AWD,

    @SerializedName("rwd")
    RWD,

    @SerializedName("fwd")
    FWD
}