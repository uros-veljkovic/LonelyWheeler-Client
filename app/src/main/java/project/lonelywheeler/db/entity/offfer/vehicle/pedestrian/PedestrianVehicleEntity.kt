package project.lonelywheeler.db.entity.offfer.vehicle.pedestrian

import androidx.databinding.ObservableField
import com.google.gson.GsonBuilder
import project.lonelywheeler.db.entity.offfer.OfferBasicInfoEntity
import project.lonelywheeler.db.entity.offfer.OfferEntity
import project.lonelywheeler.db.entity.offfer.toPojo
import project.lonelywheeler.model.domain.offer.vehicle.pedestrian.PedestrianVehicle
import project.lonelywheeler.util.convertToBitmapList

class PedestrianVehicleEntity
constructor(
    _id: String?,
    sellerId: String?,
    basicInfo: OfferBasicInfoEntity,
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
) : OfferEntity(
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
) {
    override fun toString(): String {
        val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
        return gsonBuilder.toJson(this)
    }
}

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