package project.lonelywheeler.model.observable.liked

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.BR
import javax.inject.Inject

@ActivityRetainedScoped
class LikedSellerObservable
@Inject
constructor() : BaseObservable() {

    @get: Bindable
    var userLikingID = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.userLikingID)
        }

    @get: Bindable
    var userLikedID = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.userLikedID)
        }

    @get: Bindable
    var liked: Boolean = false
        set(value) {
            field = value
            disliked = !value
            notifyPropertyChanged(BR.liked)
            notifyPropertyChanged(BR.disliked)
        }

    @get: Bindable
    var disliked: Boolean = false
        set(value) {
            field = value
            liked = !value
            notifyPropertyChanged(BR.disliked)
            notifyPropertyChanged(BR.liked)
        }

}