package project.lonelywheeler.db.entity.offfer

import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose

open class OfferEntity
constructor(
    val _id: String?,
    val sellerId: String?,
    val basicInfo: OfferBasicInfoEntity,
    val condition: String,
    val pictures: MutableList<String>,
    val valueFixed: Boolean,
    val firstOwner: Boolean,
    val sellerInForExchange: Boolean,
    val otherInfo: String,
    val colorExterior: String,
    val colorInterior: String,
    val materialInterior: String,
) {
    override fun toString(): String {
        val gsonBuilder = GsonBuilder()
            .setPrettyPrinting()
            .create()
        return gsonBuilder.toJson(this)
    }
}