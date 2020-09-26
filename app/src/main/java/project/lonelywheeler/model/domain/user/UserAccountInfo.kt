package project.lonelywheeler.model.domain.user

import android.graphics.Bitmap
import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.di.*
import javax.inject.Inject

@ActivityRetainedScoped
class UserAccountInfo
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