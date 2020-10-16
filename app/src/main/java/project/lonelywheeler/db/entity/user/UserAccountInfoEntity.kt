package project.lonelywheeler.db.entity.user

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.model.domain.user.UserAccountInfo
import project.lonelywheeler.util.convertToBitmap

class UserAccountInfoEntity(

    val username: String,
    val email: String,
    val password: String,
    val picture: String?,
    val timesSupported: Int,
    val timesReported: Int,
    val offersLiked: MutableList<Long>,
    val myOffers: MutableList<Long>
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
