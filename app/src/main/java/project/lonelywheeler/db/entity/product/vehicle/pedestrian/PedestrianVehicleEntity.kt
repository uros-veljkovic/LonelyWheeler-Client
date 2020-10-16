package project.lonelywheeler.db.entity.product.vehicle.pedestrian

import androidx.databinding.ObservableField
import com.google.gson.annotations.SerializedName
import project.lonelywheeler.db.entity.product.ProductBasicInfoEntity
import project.lonelywheeler.db.entity.product.toPojo
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.vehicle.pedestrian.PedestrianVehicle
import project.lonelywheeler.model.domain.product.vehicle.pedestrian.PedestrianVehicleType
import project.lonelywheeler.util.convertToBitmapList

class PedestrianVehicleEntity
constructor(
    @SerializedName("_id")
    val id: String?,
    val sellerId: String?,
    val basicInfo: ProductBasicInfoEntity,
    val condition: String,
    val pictures: MutableList<String>,
    val valueFixed: Boolean,
    val firstOwner: Boolean,
    val sellerInForExchange: Boolean,
    val otherInfo: String,
    val colorExterior: String,
    val colorInterior: String,
    val materialInterior: String,
    val pedestrianVehicleType: String
)

fun PedestrianVehicleEntity.toPojo(): PedestrianVehicle {
    return PedestrianVehicle(
        id,
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