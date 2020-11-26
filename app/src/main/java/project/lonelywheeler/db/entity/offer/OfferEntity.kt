package project.lonelywheeler.db.entity.offer

import com.google.gson.GsonBuilder
import project.lonelywheeler.model.enums.offer.Condition
import project.lonelywheeler.model.observable.offer.OfferObservable
import project.lonelywheeler.util.extensions.convertToBitmapList

open class OfferEntity
constructor(
    val _id: String?,
    val sellerId: String?,
    val basicInfo: OfferBasicInfoEntity,
    val entityClassSimpleName: String,
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

    open fun toObservable(): OfferObservable {
        return OfferObservable().apply {
            this.id = this@OfferEntity._id
            this.sellerId = this@OfferEntity.sellerId
            this.basicInfo = this@OfferEntity.basicInfo.toObservable()
            this.condition = Condition.valueOf(this@OfferEntity.condition)
            this.pictures = this@OfferEntity.pictures.convertToBitmapList()
            this.valueFixed = this@OfferEntity.valueFixed
            this.firstOwner = this@OfferEntity.firstOwner
            this.sellerInForExchange = this@OfferEntity.sellerInForExchange
            this.otherInfo = this@OfferEntity.otherInfo
            this.colorExterior = this@OfferEntity.colorExterior
            this.colorInterior = this@OfferEntity.colorInterior
            this.materialInterior = this@OfferEntity.materialInterior

        }
    }

}