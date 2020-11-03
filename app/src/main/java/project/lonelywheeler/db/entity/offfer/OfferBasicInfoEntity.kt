package project.lonelywheeler.db.entity.offfer

import project.lonelywheeler.model.domain.offer.OfferBasicInfo
import java.util.*

class OfferBasicInfoEntity
constructor(
    val title: String,
    val value: Double,
    val dateModified: Long,
    val model: String?,
    val brand: String,
    val yearOfProduction: Int?
)

fun OfferBasicInfoEntity.toPojo(): OfferBasicInfo {
    return OfferBasicInfo(
        title = this.title,
        value = this.value,
        dateModified = Date(this.dateModified),
        model = this.model,
        brand = this.brand,
        yearOfProduction = this.yearOfProduction
    )
}

