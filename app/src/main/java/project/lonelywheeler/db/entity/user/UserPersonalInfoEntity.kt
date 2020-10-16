package project.lonelywheeler.db.entity.user

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.model.domain.user.UserPersonalInfo

class UserPersonalInfoEntity
constructor(
    val firstName: String,
    val lastName: String,
    val city: String,
    val street: String,
    val mobileNumber: String
)

fun UserPersonalInfoEntity.toPojo(): UserPersonalInfo {
    return UserPersonalInfo(
        firstName.trim(),
        lastName.trim(),
        city.trim(),
        street.trim(),
        mobileNumber.trim()
    )
}