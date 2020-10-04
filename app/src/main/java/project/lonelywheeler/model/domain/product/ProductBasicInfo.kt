package project.lonelywheeler.model.domain.product

import project.lonelywheeler.db.entity.product.ProductBasicInfoEntity
import java.util.*
import javax.inject.Inject

class ProductBasicInfo
@Inject
constructor(
    var title: String,
    var value: Double,
    var dateModified: Date,
    var model: String?,
    var brand: String,
    var yearOfProduction: Int?
)

fun  ProductBasicInfo.toEntity(): ProductBasicInfoEntity {
    return ProductBasicInfoEntity(
        title = this.title,
        value = this.value,
        dateModified = this.dateModified.time,
        model = this.model,
        brand = this.brand,
        yearOfProduction = this.yearOfProduction
    )
}