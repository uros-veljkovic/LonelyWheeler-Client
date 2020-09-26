package project.lonelywheeler.model.domain.product

import android.graphics.Bitmap

abstract class Product(
    var id: Long?,
    var sellerId: Long?,
    var basicInfo: ProductBasicInfo,
    var condition: Condition?,
    var pictures: List<Bitmap>,
    var valueFixed: Boolean?,
    var firstOwner: Boolean?,
    var sellerInForExchange: Boolean?,
    var otherInfo: String?,
    var colorExterior: String?,
    var colorInterior: String?,
    var materialInterior: String?
) {
    init {
        this.toString()
    }
}