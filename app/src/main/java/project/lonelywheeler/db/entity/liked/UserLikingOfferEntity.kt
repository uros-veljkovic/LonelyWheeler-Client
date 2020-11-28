package project.lonelywheeler.db.entity.liked

data class UserLikingOfferEntity
constructor(
    val userId: String,
    val offerId: String,
    val entityType: String
)