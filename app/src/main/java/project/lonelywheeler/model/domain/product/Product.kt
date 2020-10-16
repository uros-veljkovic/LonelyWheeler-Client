package project.lonelywheeler.model.domain.product

import android.graphics.Bitmap
import androidx.databinding.ObservableField

abstract class Product(
    var id: String?,
    var sellerId: String?,
    var basicInfo: ProductBasicInfo,
    var condition: ObservableField<String>,
    var pictures: MutableList<Bitmap>,
    var valueFixed: Boolean,
    var firstOwner: Boolean,
    var sellerInForExchange: Boolean,
    var otherInfo: String,
    var colorExterior: String,
    var colorInterior: String,
    var materialInterior: String
)