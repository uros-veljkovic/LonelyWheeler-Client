package project.lonelywheeler.model.domain.product

import com.google.gson.annotations.SerializedName

enum class Condition {

    @SerializedName("New")
    New,

    @SerializedName("Used")
    Used,

    @SerializedName("Repaired")
    Repaired,

    @SerializedName("Needs repair")
    NeedsRepair{
        override fun toString(): String {
            return "Needs repair"
        }
    }

}