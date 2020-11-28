package project.lonelywheeler.db.response

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.model.observable.offer.OfferObservable

class MyResponse<T>
constructor(
    @SerializedName("code")
    val responseCode: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("entity")
    var entity: T?,
) {
    fun hasMessage(): Boolean {
        return this.message.isNotEmpty()
    }


    fun isEmpty(): Boolean {
        return entity == null
    }
}

fun <T : OfferEntity> MyResponse<T>.isInDatabase(): Boolean {
    return entity?._id?.isNotEmpty() ?: false
}


