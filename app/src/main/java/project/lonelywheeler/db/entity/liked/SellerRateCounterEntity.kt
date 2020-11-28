package project.lonelywheeler.db.entity.liked

import project.lonelywheeler.model.observable.liked.SellerRateCounterObservable

data class SellerRateCounterEntity
constructor(
    val userId: String,
    val likes: Int,
    val dislikes: Int,
) {
    fun toObservable(): SellerRateCounterObservable {
        return SellerRateCounterObservable().also {
            it.userId = userId
            it.likes = likes
            it.dislikes = dislikes
        }
    }
}