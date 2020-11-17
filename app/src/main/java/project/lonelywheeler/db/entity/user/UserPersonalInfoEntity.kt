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
            firstName = firstName.trim()
            lastName = lastName.trim()
            city = city.trim()
            street = street.trim()
            mobileNumber = mobileNumber.trim()
        }
    }
}

