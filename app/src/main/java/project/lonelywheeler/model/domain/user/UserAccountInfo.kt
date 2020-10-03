package project.lonelywheeler.model.domain.user

import android.graphics.Bitmap
import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.repository.entity.user.UserAccountInfoEntity
import project.lonelywheeler.util.toByteArray
import javax.inject.Inject

@ActivityRetainedScoped
data class UserAccountInfo
@Inject
constructor(
    var username: String,
    var email: String,
    var password: String,
    var picture: Bitmap?,
    var timesSupported: Int,
    var timesReported: Int,
    var offersLiked: List<Long>,
    var myOffers: List<Long>
)

fun UserAccountInfo.toEntity() : UserAccountInfoEntity{
    return UserAccountInfoEntity(
        username = this.username,
        email = this.email,
        password = this.password,
        pictureByteArray = this.picture?.toByteArray(),
        timesSupported = this.timesSupported,
        timesReported = this.timesReported,
        offersLiked = this.offersLiked,
        myOffers = this.myOffers
    )
}