package project.lonelywheeler.db.entity.user

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import project.lonelywheeler.model.domain.user.User

class UserEntity
constructor(

    @SerializedName("_id")
    val id: String?,

    @SerializedName("personalInfo")
    val personalInfoEntity: UserPersonalInfoEntity,

    @SerializedName("accountInfo")
    val accountInfoEntity: UserAccountInfoEntity
) {
    override fun toString(): String {
        val gson: Gson =
            GsonBuilder()
                .setPrettyPrinting()
                .create()
        return gson.toJson(this)
    }
}

fun UserEntity.toPojo(): User {
    return User(
        id = this.id,
        personalInfo = personalInfoEntity.toPojo(),
        accountInfo = accountInfoEntity.toPojo()
    )
}