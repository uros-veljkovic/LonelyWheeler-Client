package project.lonelywheeler.model.observable.user

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.BR
import project.lonelywheeler.db.entity.user.UserPersonalInfoEntity
import javax.inject.Inject

@ActivityRetainedScoped
class UserPersonalInfoObservable
@Inject
constructor() : BaseObservable() {

    @get:Bindable
    var numberOfSeats: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.numberOfSeats)
        }

    @get:Bindable
    var firstName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.firstName)
        }

    @get:Bindable
    var lastName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.lastName)
        }

    @get:Bindable
    var city: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.city)
        }

    @get:Bindable
    var street: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.street)
        }

    @get:Bindable
    var mobileNumber: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.mobileNumber)
        }

    fun toEntity(): UserPersonalInfoEntity {
        return UserPersonalInfoEntity(
            firstName.trim(),
            lastName.trim(),
            city.trim(),
            street.trim(),
            mobileNumber.trim()
        )
    }
}