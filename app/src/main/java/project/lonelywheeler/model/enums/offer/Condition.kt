package project.lonelywheeler.model.enums.offer

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.util.extensions.toCamelCase

enum class Condition {

    @SerializedName("New")
    New,

    @SerializedName("Used")
    Used,

    @SerializedName("Repaired")
    Repaired,

    @SerializedName("Needs repair")
    NeedsRepair;

    companion object {
        fun valueOfSerialized(s: String): Condition {
            return Condition.valueOf(s.toCamelCase())
        }
    }

}