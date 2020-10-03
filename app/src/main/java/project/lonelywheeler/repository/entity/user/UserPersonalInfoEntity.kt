package project.lonelywheeler.repository.entity.user

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.model.domain.user.UserPersonalInfo

class UserPersonalInfoEntity
constructor(
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("city")
    val city: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("mobile_number")
    val mobileNumber: String
)

fun UserPersonalInfoEntity.toPojo(): UserPersonalInfo {
    return UserPersonalInfo(
        firstName, lastName, city, street, mobileNumber
    )
}