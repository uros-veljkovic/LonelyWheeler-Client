package project.lonelywheeler.db.entity.liked

data class LikedSellerEntity
constructor(
    val userLikingID: String,
    val userLikedID: String,
    val liked: Boolean,
    val disliked: Boolean,
) {
}