package project.lonelywheeler.model.domain.offer.vehicle.motor

import com.google.gson.annotations.SerializedName

enum class EmissionStandard {

    @SerializedName("Euro 1")
    Euro1{
        override fun toString(): String {
            return "Euro 1"
        }
    },

    @SerializedName("Euro 2")
    Euro2
    {
        override fun toString(): String {
            return "Euro 2"
        }
    },

    @SerializedName("Euro 3")
    Euro3{
        override fun toString(): String {
            return "Euro 3"
        }
    },

    @SerializedName("Euro 4")
    Euro4{
        override fun toString(): String {
            return "Euro 4"
        }
    },

    @SerializedName("Euro 5")
    Euro5{
        override fun toString(): String {
            return "Euro 5"
        }
    },

    @SerializedName("Euro 6")
    Euro6{
        override fun toString(): String {
            return "Euro 6"
        }
    }
}