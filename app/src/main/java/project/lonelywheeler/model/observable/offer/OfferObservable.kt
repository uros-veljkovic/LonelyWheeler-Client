package project.lonelywheeler.model.observable.offer

import android.graphics.Bitmap
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.BR
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.model.enums.offer.Condition
import project.lonelywheeler.util.convertToStringList
import javax.inject.Inject

@ActivityRetainedScoped
open class OfferObservable
@Inject
constructor() : BaseObservable() {

    @get: Bindable
    var id: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.id)
        }

    @get: Bindable
    var sellerId: String? = MyApplication.getCurrentUserID()
        set(value) {
            field = value
            notifyPropertyChanged(BR.sellerId)
        }

    @get: Bindable
    var basicInfo: OfferBasicInfoObservable = OfferBasicInfoObservable()
        set(value) {
            field = value
            notifyPropertyChanged(BR.basicInfo)
        }

    @get: Bindable
    var condition: Condition = Condition.New
        set(value) {
            field = value
            notifyPropertyChanged(BR.condition)
        }

    @get: Bindable
    var pictures: MutableList<Bitmap> = mutableListOf()
        set(value) {
            field = value
            notifyPropertyChanged(BR.pictures)
        }

    @get: Bindable
    var valueFixed: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.valueFixed)
        }

    @get: Bindable
    var firstOwner: Boolean = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.firstOwner)
        }

    @get: Bindable
    var sellerInForExchange: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.sellerInForExchange)
        }

    @get: Bindable
    var otherInfo: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.otherInfo)
        }

    @get: Bindable
    var colorExterior: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.colorExterior)
        }

    @get: Bindable
    var colorInterior: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.colorInterior)
        }

    @get: Bindable
    var materialInterior: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.materialInterior)
        }


    fun addPicture(bitmap: Bitmap) {
        pictures.add(bitmap)
        notifyPropertyChanged(BR.pictures)
    }

    open fun toEntity(): OfferEntity {
        return OfferEntity(id,
            sellerId,
            basicInfo.toEntity(),
            OfferObservable::class.simpleName!!,
            condition.name,
            pictures.convertToStringList(),
            valueFixed,
            firstOwner,
            sellerInForExchange,
            otherInfo,
            colorExterior,
            colorInterior,
            materialInterior)
    }

}

