package project.lonelywheeler.model.observable.offer.equipment

import androidx.databinding.Bindable
import project.lonelywheeler.BR
import project.lonelywheeler.db.entity.offer.equipment.EquipmentEntity
import project.lonelywheeler.model.domain.offer.equipment.EquipmentType
import project.lonelywheeler.model.observable.offer.OfferObservable
import project.lonelywheeler.util.convertToStringList
import javax.inject.Inject

class EquipmentObservable
@Inject
constructor() : OfferObservable() {

    @get: Bindable
    var equipmentType: EquipmentType = EquipmentType.MotorVehiclePart
        set(value) {
            field = value
            notifyPropertyChanged(BR.equipmentType)
        }

    override fun toEntity(): EquipmentEntity {
        return EquipmentEntity(
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
            equipmentType.name
        )
    }
}

