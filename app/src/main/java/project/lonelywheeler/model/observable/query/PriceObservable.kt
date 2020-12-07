package project.lonelywheeler.model.observable.query

import androidx.databinding.ObservableInt
import project.lonelywheeler.db.entity.query.PriceEntity

class PriceObservable {
    var min: ObservableInt = ObservableInt(0)
    var max: ObservableInt = ObservableInt(15000)

    fun toEntity(): PriceEntity {
        return PriceEntity(min.get(), max.get())
    }
}