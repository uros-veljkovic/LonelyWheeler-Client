package project.lonelywheeler.model.observable.offer

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.BR
import project.lonelywheeler.db.entity.offer.OfferBasicInfoEntity
import project.lonelywheeler.model.observable.offer.vehicle.motor.toLong
import java.util.*
import javax.inject.Inject

@ActivityRetainedScoped
class OfferBasicInfoObservable
@Inject
constructor() : BaseObservable() {

    constructor(
        title: String,
        value: Int,
        dateModified: Date,
        model: String,
        brand: String,
        yearOfProduction: Int,
    ) : this() {
        this.title = title
        this.value = value
        this.dateModified = dateModified
        this.model = model
        this.brand = brand
        this.yearOfProduction = yearOfProduction
    }

    @get: Bindable
    var title: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @get: Bindable
    var value: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.value)
        }

    @get: Bindable
    var dateModified: Date = Date()
        set(value) {
            field = value
            notifyPropertyChanged(BR.dateModified)
        }

    @get: Bindable
    var model: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.model)
        }

    @get: Bindable
    var brand: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.brand)
        }

    @get: Bindable
    var yearOfProduction: Int = 2020
        set(value) {
            field = value
            notifyPropertyChanged(BR.yearOfProduction)
        }

    fun toEntity(): OfferBasicInfoEntity {
        return OfferBasicInfoEntity(
            title,
            value,
            dateModified.toLong(),
            model,
            brand,
            yearOfProduction,
        )
    }

}