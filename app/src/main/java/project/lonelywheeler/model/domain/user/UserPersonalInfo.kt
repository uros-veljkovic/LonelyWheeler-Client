package project.lonelywheeler.model.domain.user

import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.db.entity.user.UserPersonalInfoEntity
import javax.inject.Inject

@ActivityRetainedScoped
class UserPersonalInfo
@Inject
constructor(
    var firstName: String,
    var lastName: String,
    var city: String,
    var street: String,
    var mobileNumber: String
)

fun UserPersonalInfo.toEntity(): UserPersonalInfoEntity {
    return UserPersonalInfoEntity(
        firstName.trim(),
        lastName.trim(),
        city.trim(),
        street.trim(),
        mobileNumber.trim()
    )
}