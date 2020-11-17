package project.lonelywheeler.db.entity.user

import project.lonelywheeler.model.observable.user.UserAccountInfoObservable
import project.lonelywheeler.util.extensions.convertToBitmap

class UserAccountInfoEntity(

    val username: String,
    val email: String,
    val password: String,
    val picture: String?,
    val timesSupported: Int,
    val timesReported: Int,
    val offersLiked: MutableList<Long>,
    val myOffers: MutableList<Long>,
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


    fun toObservable(): UserAccountInfoObservable {
        return UserAccountInfoObservable().apply {
            username = this@UserAccountInfoEntity.username
            email = this@UserAccountInfoEntity.email
            password = this@UserAccountInfoEntity.password
            picture = this@UserAccountInfoEntity.picture?.convertToBitmap()
            timesSupported = this@UserAccountInfoEntity.timesSupported
            timesReported = this@UserAccountInfoEntity.timesReported
            offersLiked = this@UserAccountInfoEntity.offersLiked
            myOffers = this@UserAccountInfoEntity.myOffers
        }
    }
}

