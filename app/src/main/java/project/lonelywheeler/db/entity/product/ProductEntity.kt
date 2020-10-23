package project.lonelywheeler.db.entity.product

import com.google.gson.GsonBuilder

open class ProductEntity
constructor(
    val _id: String?,
    val sellerId: String?,
    val basicInfo: ProductBasicInfoEntity,
    val condition: String,
    val pictures: MutableList<String>,
    val valueFixed: Boolean,
    val firstOwner: Boolean,
    val sellerInForExchange: Boolean,
    val otherInfo: String,
    val colorExterior: String,
    val colorInterior: String,
    val materialInterior: String
) {
    override fun toString(): String {
        val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
        return gsonBuilder.toJson(this)
    }
}