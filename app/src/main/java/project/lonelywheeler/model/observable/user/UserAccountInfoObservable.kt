package project.lonelywheeler.model.observable.user

import android.graphics.Bitmap
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.BR
import project.lonelywheeler.db.entity.user.UserAccountInfoEntity
import project.lonelywheeler.util.convertToString
import javax.inject.Inject

@ActivityRetainedScoped
class UserAccountInfoObservable
@Inject
constructor() : BaseObservable() {

    @get:Bindable
    var username: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.username)
        }

    @get:Bindable
    var email: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @get:Bindable
    var password: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }

    @get:Bindable
    var confirmPassword: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.confirmPassword)
        }

    @get:Bindable
    var picture: Bitmap? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.picture)
        }

    @get:Bindable
    var timesSupported: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.timesSupported)
        }

    @get:Bindable
    var timesReported: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.timesReported)
        }

    @get:Bindable
    var offersLiked: MutableList<Long> = mutableListOf()
        set(value) {
            field = value
            notifyPropertyChanged(BR.offersLiked)
        }

    @get:Bindable
    var myOffers: MutableList<Long> = mutableListOf()
        set(value) {
            field = value
            notifyPropertyChanged(BR.myOffers)
        }

    fun toEntity(): UserAccountInfoEntity {
        return UserAccountInfoEntity(username,
            email,
            password,
            picture?.convertToString(),
            timesSupported,
            timesReported,
            offersLiked,
            myOffers)
    }
}