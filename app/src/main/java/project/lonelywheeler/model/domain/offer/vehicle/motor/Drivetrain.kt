package project.lonelywheeler.model.domain.offer.vehicle.motor

import com.google.gson.annotations.SerializedName

enum class Drivetrain {

    @SerializedName("AWD")
    AWD,

    @SerializedName("RWD")
    RWD,

    @SerializedName("FWD")
    FWD
}
