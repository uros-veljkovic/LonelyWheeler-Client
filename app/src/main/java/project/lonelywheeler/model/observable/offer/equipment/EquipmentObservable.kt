package project.lonelywheeler.model.observable.offer.equipment

import android.graphics.Bitmap
import androidx.databinding.Bindable
import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.BR
import project.lonelywheeler.db.entity.offer.equipment.EquipmentEntity
import project.lonelywheeler.model.enums.offer.Condition
import project.lonelywheeler.model.enums.offer.equipment.EquipmentType
import project.lonelywheeler.model.observable.offer.OfferBasicInfoObservable
import project.lonelywheeler.model.observable.offer.OfferObservable
import project.lonelywheeler.util.convertToStringList
import javax.inject.Inject

@ActivityRetainedScoped
class EquipmentObservable
@Inject
constructor() : OfferObservable() {
    constructor(
        id: String?,
        sellerId: String?,
        basicInfo: OfferBasicInfoObservable,
        condition: Condition,
        pictures: MutableList<Bitmap>,
        valueFixed: Boolean,
        firstOwner: Boolean,
        sellerInForExchange: Boolean,
        otherInfo: String,
        colorExterior: String,
        colorInterior: String,
        materialInterior: String,
        equipmentType: EquipmentType
    ) : this() {
        this.id = id
        this.sellerId = sellerId
        this.basicInfo = basicInfo
        this.condition = condition
        this.pictures = pictures
        this.valueFixed = valueFixed
        this.firstOwner = firstOwner
        this.sellerInForExchange = sellerInForExchange
        this.otherInfo = otherInfo
        this.colorExterior = colorExterior
        this.colorInterior = colorInterior
        this.materialInterior = materialInterior
        this.equipmentType = equipmentType
    }


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

