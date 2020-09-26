package project.lonelywheeler.model.domain.user

import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.di.*
import javax.inject.Inject

@ActivityRetainedScoped
class UserPersonalInfo
@Inject
constructor(
    var firstName: String?,
    var lastName: String?,
    var city: String,
    var street: String,
    var mobileNumber: String
)