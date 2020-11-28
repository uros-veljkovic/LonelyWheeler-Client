package project.lonelywheeler.model.observable.liked

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import project.lonelywheeler.BR

class SellerRateCounterObservable : BaseObservable() {

    @get: Bindable
    var userId: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.userId)
        }

    @get: Bindable
    var likes: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.likes)
        }

    @get: Bindable
    var dislikes: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.dislikes)
        }

}