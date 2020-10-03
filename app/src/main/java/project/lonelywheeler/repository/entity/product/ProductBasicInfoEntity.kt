package project.lonelywheeler.repository.entity.product

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import java.util.*

class ProductBasicInfoEntity
constructor(
    @SerializedName("title")
    val title: String,

    @SerializedName("value")
    val value: Double,

    @SerializedName("date_modified")
    val dateModified: Long,

    @SerializedName("model")
    val model: String?,

    @SerializedName("brand")
    val brand: String,

    @SerializedName("year_of_production")
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

