package project.lonelywheeler.db.entity.user

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.model.domain.user.UserPersonalInfo

class UserPersonalInfoEntity
constructor(
    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("street")
    val street: String,

    @SerializedName("mobileNumber")
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