package project.lonelywheeler.model.domain.product.vehicle.motor

import com.google.gson.annotations.SerializedName
import kotlin.reflect.KClass

enum class Drivetrain {

    @SerializedName("awd")
    AWD,

    @SerializedName("rwd")
    RWD,

    @SerializedName("fwd")
    FWD
}
