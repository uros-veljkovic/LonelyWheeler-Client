package project.lonelywheeler.db.entity.user

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import project.lonelywheeler.model.observable.user.UserObservable

class UserEntity
constructor(
    @SerializedName("_id")
    val id: String?,
    @SerializedName("personalInfo")
    val personalInfoEntity: UserPersonalInfoEntity,
    @SerializedName("accountInfo")
    val accountInfoEntity: UserAccountInfoEntity,
) {
    override fun toString(): String {
        val gson: Gson =
            GsonBuilder()
                .setPrettyPrinting()
                .create()
        return gson.toJson(this)
    }

    fun toObservable(): UserObservable {
        return UserObservable().apply {
            id = this@UserEntity.id
            personalInfoObservable = this@UserEntity.personalInfoEntity.toObservable()
            accountInfoObservable = this@UserEntity.accountInfoEntity.toObservable()
        }
    }

}