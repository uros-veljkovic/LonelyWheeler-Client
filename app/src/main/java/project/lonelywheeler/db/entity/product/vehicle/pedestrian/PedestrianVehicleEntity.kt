package project.lonelywheeler.db.entity.product.vehicle.pedestrian

import androidx.databinding.ObservableField
import project.lonelywheeler.db.entity.product.ProductBasicInfoEntity
import project.lonelywheeler.db.entity.product.ProductEntity
import project.lonelywheeler.db.entity.product.toPojo
import project.lonelywheeler.model.domain.product.vehicle.pedestrian.PedestrianVehicle
import project.lonelywheeler.util.convertToBitmapList

class PedestrianVehicleEntity
constructor(
    _id: String?,
    sellerId: String?,
    basicInfo: ProductBasicInfoEntity,
    condition: String,
    pictures: MutableList<String>,
    valueFixed: Boolean,
    firstOwner: Boolean,
    sellerInForExchange: Boolean,
    otherInfo: String,
    colorExterior: String,
    colorInterior: String,
    materialInterior: String,
    val pedestrianVehicleType: String
) : ProductEntity(
    _id,
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

fun PedestrianVehicleEntity.toPojo(): PedestrianVehicle {
    return PedestrianVehicle(
        _id,
        sellerId,
        this.basicInfo.toPojo(),
        ObservableField(condition),
        this.pictures.convertToBitmapList(),
        valueFixed,
        firstOwner,
        sellerInForExchange,
        otherInfo,
        colorExterior,
        colorInterior,
        materialInterior,
        ObservableField(pedestrianVehicleType)
    )
}