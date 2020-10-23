package project.lonelywheeler.model.domain.product.vehicle.motor

import com.google.gson.annotations.SerializedName
import kotlin.reflect.KClass

enum class Drivetrain {

    @SerializedName("AWD")
    AWD,

    @SerializedName("RWD")
    RWD,

    @SerializedName("FWD")
    FWD
}
