package project.lonelywheeler.model.domain.user

import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.di.*
import project.lonelywheeler.repository.entity.user.UserPersonalInfoEntity
import project.lonelywheeler.repository.entity.user.toPojo
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

fun UserPersonalInfo.toEntity() : UserPersonalInfoEntity{
    return UserPersonalInfoEntity(
        firstName = this.firstName,
        lastName = this.lastName,
        city = this.city,
        street = this.street,
        mobileNumber = this.mobileNumber
    )
}