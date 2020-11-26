package project.lonelywheeler.db.entity.offer.vehicle.pedestrian

import com.google.gson.GsonBuilder
import project.lonelywheeler.db.entity.offer.OfferBasicInfoEntity
import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.model.enums.offer.Condition
import project.lonelywheeler.model.enums.offer.vehicle.pedestrian.PedestrianVehicleType
import project.lonelywheeler.model.observable.offer.vehicle.pedestrian.PedestrianVehicleObservable
import project.lonelywheeler.util.extensions.convertToBitmapList

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
    val pedestrianVehicleType: String,
) : OfferEntity(
    _id,
    sellerId,
    basicInfo,
    PedestrianVehicleEntity::class.simpleName.toString(),
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

    override fun toObservable(): PedestrianVehicleObservable {
        return PedestrianVehicleObservable().apply {
            id = this@PedestrianVehicleEntity._id
            sellerId = this@PedestrianVehicleEntity.sellerId
            basicInfo = this@PedestrianVehicleEntity.basicInfo.toObservable()
            condition = Condition.valueOf(this@PedestrianVehicleEntity.condition)
            pictures = this@PedestrianVehicleEntity.pictures.convertToBitmapList()
            valueFixed = this@PedestrianVehicleEntity.valueFixed
            firstOwner = this@PedestrianVehicleEntity.firstOwner
            sellerInForExchange = this@PedestrianVehicleEntity.sellerInForExchange
            otherInfo = this@PedestrianVehicleEntity.otherInfo
            colorExterior = this@PedestrianVehicleEntity.colorExterior
            colorInterior = this@PedestrianVehicleEntity.colorInterior
            materialInterior = this@PedestrianVehicleEntity.materialInterior
            pedestrianVehicleType =
                PedestrianVehicleType.valueOf(this@PedestrianVehicleEntity.pedestrianVehicleType)
        }
    }
}

