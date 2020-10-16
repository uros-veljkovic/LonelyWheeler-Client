package project.lonelywheeler.db.entity.product

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import java.util.*

class ProductBasicInfoEntity
constructor(
    val title: String,
    val value: Double,
    val dateModified: Long,
    val model: String?,
    val brand: String,
    val yearOfProduction: Int?
)

fun ProductBasicInfoEntity.toPojo(): ProductBasicInfo {
    return ProductBasicInfo(
        title = this.title,
        value = this.value,
        dateModified = Date(this.dateModified),
        model = this.model,
        brand = this.brand,
        yearOfProduction = this.yearOfProduction
    )
}

