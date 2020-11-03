package project.lonelywheeler.model.domain.offer

import project.lonelywheeler.db.entity.offfer.OfferBasicInfoEntity
import java.util.*
import javax.inject.Inject

class OfferBasicInfo
@Inject
constructor(
    var title: String,
    var value: Double,
    var dateModified: Date,
    var model: String?,
    var brand: String,
    var yearOfProduction: Int?
)

fun  OfferBasicInfo.toEntity(): OfferBasicInfoEntity {
    return OfferBasicInfoEntity(
        title = this.title,
        value = this.value,
        dateModified = this.dateModified.time,
        model = this.model,
        brand = this.brand,
        yearOfProduction = this.yearOfProduction
    )
}