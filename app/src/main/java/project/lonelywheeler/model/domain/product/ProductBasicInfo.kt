package project.lonelywheeler.model.domain.product

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