package project.lonelywheeler.db.entity.offer

import project.lonelywheeler.model.observable.offer.OfferBasicInfoObservable
import java.util.*

class OfferBasicInfoEntity
constructor(
    val title: String,
    val value: Double,
    val dateModified: Long,
    val model: String?,
    val brand: String,
    val yearOfProduction: Int?,
) {
    fun toObservable(): OfferBasicInfoObservable {
        return OfferBasicInfoObservable(
            title = this.title,
            value = this.value,
            dateModified = Date(this.dateModified),
            model = this.model!!,
            brand = this.brand,
            yearOfProduction = this.yearOfProduction!!
        )
    }

}


