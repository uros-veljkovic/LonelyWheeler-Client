package project.lonelywheeler.model.observable.liked

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.BR
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.db.entity.liked.UserLikingSellerEntity
import javax.inject.Inject

@ActivityRetainedScoped
class UserLikingSellerObservable
@Inject
constructor() : BaseObservable() {

    @get: Bindable
    var userId = MyApplication.getCurrentUserID()
        set(value) {
            field = value
            notifyPropertyChanged(BR.userId)
        }

    @get: Bindable
    var sellerID = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.sellerID)
        }

    @get: Bindable
    var liked: Boolean = false
        set(value) {
            field = value
            if (disliked && value)
                disliked = false
            notifyPropertyChanged(BR.liked)
        }

    @get: Bindable
    var disliked: Boolean = false
        set(value) {
            field = value
            if (liked && value)
                liked = false
            notifyPropertyChanged(BR.disliked)
        }

    fun like() {
        liked = !liked
    }

    fun dislike() {
        disliked = !disliked
    }

    fun toEntity(): UserLikingSellerEntity {
        return UserLikingSellerEntity(
            this@UserLikingSellerObservable.userId,
            this@UserLikingSellerObservable.sellerID,
            this@UserLikingSellerObservable.liked,
            this@UserLikingSellerObservable.disliked
        )
    }

}