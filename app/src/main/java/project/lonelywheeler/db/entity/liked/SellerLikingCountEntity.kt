package project.lonelywheeler.db.entity.liked

data class SellerLikingCountEntity
constructor(
    val userId: String,
    val likes: Int,
    val dislikes: Int,
) {
}