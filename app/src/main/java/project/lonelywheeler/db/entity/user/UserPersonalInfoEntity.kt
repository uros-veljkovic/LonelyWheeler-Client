package project.lonelywheeler.db.entity.user

import project.lonelywheeler.model.observable.user.UserPersonalInfoObservable

class UserPersonalInfoEntity
constructor(
    val firstName: String,
    val lastName: String,
    val city: String,
    val street: String,
    val mobileNumber: String,
) {
    fun toObservable(): UserPersonalInfoObservable {
        return UserPersonalInfoObservable().apply {
            this.firstName = this@UserPersonalInfoEntity.firstName.trim()
            lastName = this@UserPersonalInfoEntity.lastName.trim()
            city = this@UserPersonalInfoEntity.city.trim()
            street = this@UserPersonalInfoEntity.street.trim()
            mobileNumber = this@UserPersonalInfoEntity.mobileNumber.trim()
        }
    }
}

