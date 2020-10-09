package project.lonelywheeler.db.entity.user

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.model.domain.user.UserAccountInfo
import project.lonelywheeler.util.convertToBitmap

class UserAccountInfoEntity(

    @SerializedName("username")
    val username: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("picture")
    val picture: String?,

    @SerializedName("timesSupported")
    val timesSupported: Int,

    @SerializedName("timesReported")
    val timesReported: Int,

    @SerializedName("offersLiked")
    val offersLiked: List<Long>,

    @SerializedName("myOffers")
    val myOffers: List<Long>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserAccountInfoEntity

        if (username != other.username) return false

        return true
    }

    override fun hashCode(): Int {
        return username.hashCode()
    }
}

fun UserAccountInfoEntity.toPojo(): UserAccountInfo {
    return UserAccountInfo(
        username,
        email,
        password,
        password,
        this.picture?.convertToBitmap(),
        timesSupported,
        timesReported,
        offersLiked,
        myOffers
    )
}
