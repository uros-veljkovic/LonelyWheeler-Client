package project.lonelywheeler.model.domain.offer.vehicle.pedestrian

import android.graphics.Bitmap
import androidx.databinding.ObservableField
import project.lonelywheeler.db.entity.offfer.vehicle.pedestrian.PedestrianVehicleEntity
import project.lonelywheeler.model.domain.offer.Offer
import project.lonelywheeler.model.domain.offer.OfferBasicInfo
import project.lonelywheeler.model.domain.offer.toEntity
import project.lonelywheeler.util.convertToStringList
import javax.inject.Inject

class PedestrianVehicle
@Inject
constructor(
    id: String?,
    sellerId: String?,
    basicInfo: OfferBasicInfo,
    condition: ObservableField<String>,
    pictures: MutableList<Bitmap>,
    valueFixed: Boolean,
    firstOwner: Boolean,
    sellerInForExchange: Boolean,
    otherInfo: String,
    colorExterior: String,
    colorInterior: String,
    materialInterior: String,
    var pedestrianVehicleType: ObservableField<String>
) : Offer(
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

fun PedestrianVehicle.toEntity(): PedestrianVehicleEntity {
    return PedestrianVehicleEntity(
        id,
        sellerId,
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
        pedestrianVehicleType.get()!!
    )
}