package project.lonelywheeler.db.entity.product.vehicle.pedestrian

import com.google.gson.annotations.SerializedName
import project.lonelywheeler.db.entity.product.ProductBasicInfoEntity
import project.lonelywheeler.db.entity.product.toPojo
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.vehicle.pedestrian.PedestrianVehicle
import project.lonelywheeler.model.domain.product.vehicle.pedestrian.PedestrianVehicleType
import project.lonelywheeler.util.convertToBitmapList

class PedestrianVehicleEntity
constructor(
    @SerializedName("id")
    val id: String?,

    @SerializedName("seller_id")
    val sellerId: String?,

    @SerializedName("basic_info")
    val basicInfo: ProductBasicInfoEntity,

    @SerializedName("condition")
    val condition: Condition,

    @SerializedName("pictures")
    val pictures: List<String>,

    @SerializedName("value_fixed")
    val valueFixed: Boolean,

    @SerializedName("first_owner")
    val firstOwner: Boolean,

    @SerializedName("seller_in_for_exchange")
    val sellerInForExchange: Boolean,

    @SerializedName("other_info")
    val otherInfo: String,

    @SerializedName("color_exterior")
    val colorExterior: String,

    @SerializedName("color_interior")
    val colorInterior: String,

    @SerializedName("material_interior")
    val materialInterior: String,

    @SerializedName("human_powered_vehicle_type")
    val pedestrianVehicleType: PedestrianVehicleType
)

fun PedestrianVehicleEntity.toPojo(): PedestrianVehicle {
    return PedestrianVehicle(
        id,
        sellerId,
        basicInfo = this.basicInfo.toPojo(),
        condition,
        pictures = this.pictures.convertToBitmapList(),
        valueFixed,
        firstOwner,
        sellerInForExchange,
        otherInfo,
        colorExterior,
        colorInterior,
        materialInterior,
        pedestrianVehicleType
    )
}