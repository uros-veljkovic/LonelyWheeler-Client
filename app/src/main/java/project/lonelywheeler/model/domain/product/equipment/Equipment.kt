package project.lonelywheeler.model.domain.product.equipment

import android.graphics.Bitmap
import androidx.databinding.ObservableField
import project.lonelywheeler.db.entity.product.equipment.EquipmentEntity
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.Product
import project.lonelywheeler.model.domain.product.ProductBasicInfo
import project.lonelywheeler.model.domain.product.toEntity
import project.lonelywheeler.util.convertToStringList
import javax.inject.Inject

class Equipment
@Inject
constructor(
    id: String?,
    sellerId: String?,
    basicInfo: ProductBasicInfo,
    condition: ObservableField<String>,
    pictures: MutableList<Bitmap>,
    valueFixed: Boolean,
    firstOwner: Boolean,
    sellerInForExchange: Boolean,
    otherInfo: String,
    colorExterior: String,
    colorInterior: String,
    materialInterior: String,
    var equipmentType: ObservableField<String>
) : Product(
    id,
    sellerId,
    basicInfo,
    condition,
    pictures,
    valueFixed,
    firstOwner,
    sellerInForExchange,
    otherInfo,
    colorExterior,
    colorInterior,
    materialInterior
)

fun Equipment.toEntity(): EquipmentEntity {
    return EquipmentEntity(
        id, sellerId,
        basicInfo.toEntity(),
        condition.get()!!,
        pictures.convertToStringList(),
        valueFixed,
        firstOwner,
        sellerInForExchange,
        otherInfo,
        colorExterior,
        colorInterior,
        materialInterior,
        equipmentType.get()!!
    )
}