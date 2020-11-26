package project.lonelywheeler.model.observable.offer.vehicle.pedestrian

import androidx.databinding.Bindable
import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.BR
import project.lonelywheeler.db.entity.offer.vehicle.pedestrian.PedestrianVehicleEntity
import project.lonelywheeler.model.enums.offer.vehicle.pedestrian.PedestrianVehicleType
import project.lonelywheeler.model.observable.offer.OfferObservable
import project.lonelywheeler.util.convertToStringList
import javax.inject.Inject

@ActivityRetainedScoped
class PedestrianVehicleObservable
@Inject
constructor() : OfferObservable() {

    @get: Bindable
    var pedestrianVehicleType: PedestrianVehicleType = PedestrianVehicleType.Bicycle
        set(value) {
            field = value
            notifyPropertyChanged(BR.pedestrianVehicleType)
        }

    override fun toEntity(): PedestrianVehicleEntity {
        return PedestrianVehicleEntity(
            id,
            sellerId,
            basicInfo.toEntity(),
            condition.name,
            pictures.convertToStringList(),
            valueFixed,
            firstOwner,
            sellerInForExchange,
            otherInfo,
            colorExterior,
            colorInterior,
            materialInterior,
            pedestrianVehicleType.name
        )
    }

}