package project.lonelywheeler.db.response

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.db.entity.offer.OfferEntity

class MyResponse<T>
constructor(
    @SerializedName("code")
    val responseCode: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("entity")
    var entity: T?
)

fun <T: OfferEntity> MyResponse<T>.hasMessage(): Boolean {
    return this.message.isNotEmpty()
}

fun <T: OfferEntity> MyResponse<T>.entityExistInDatabase(): Boolean {
    return entity!!._id!!.isNotEmpty()
}

fun <T: Any> MyResponse<T>.isEmpty(): Boolean {
    return entity == null
}