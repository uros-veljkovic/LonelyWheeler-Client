package project.lonelywheeler.model.domain.user

import android.graphics.Bitmap
import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.db.entity.user.UserAccountInfoEntity
import project.lonelywheeler.util.convertToString
import javax.inject.Inject

@ActivityRetainedScoped
data class UserAccountInfo
@Inject
constructor(
    var username: String,
    var email: String,
    var password: String,
    var confirmPassword: String,
    var picture: Bitmap?,
    var timesSupported: Int,
    var timesReported: Int,
    var offersLiked: MutableList<Long>,
    var myOffers: MutableList<Long>
)

fun UserAccountInfo.toEntity() : UserAccountInfoEntity {
    return UserAccountInfoEntity(
        username.trim(),
        email.trim(),
        password.trim(),
        picture?.convertToString(),
        timesSupported,
        timesReported,
        offersLiked,
        myOffers
    )
}