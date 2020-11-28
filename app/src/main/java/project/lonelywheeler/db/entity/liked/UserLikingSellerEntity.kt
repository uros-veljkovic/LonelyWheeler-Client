package project.lonelywheeler.db.entity.liked

import project.lonelywheeler.model.observable.liked.UserLikingSellerObservable

data class UserLikingSellerEntity
constructor(
    val userID: String,
    val sellerID: String,
    val liked: Boolean,
    val disliked: Boolean,
) {

    fun toObservable(): UserLikingSellerObservable {
        return UserLikingSellerObservable().also { it ->
            it.userId = userID
            it.sellerID = sellerID
            it.liked = liked
            it.disliked = disliked
        }
    }
}