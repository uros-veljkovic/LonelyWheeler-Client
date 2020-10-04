package project.lonelywheeler.db.entity.user

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.model.domain.user.User

class UserEntity
constructor(
    @SerializedName("user_id")
    val id: Long?,
    @SerializedName("personal_info")
    val personalInfoEntity: UserPersonalInfoEntity,
    @SerializedName("account_info")
    val accountInfoEntity: UserAccountInfoEntity
)

fun UserEntity.toPojo(): User {
    return User(
        id = this.id,
        personalInfo = personalInfoEntity.toPojo(),
        accountInfo = accountInfoEntity.toPojo()
    )
}