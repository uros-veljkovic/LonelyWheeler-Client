package project.lonelywheeler.model.observable.query

import androidx.databinding.ObservableInt
import project.lonelywheeler.db.entity.query.YearOfProductionEntity

class YearOfProductionObservable {

    var min: ObservableInt = ObservableInt(2000)
    var max: ObservableInt = ObservableInt(2020)

    fun toEntity(): YearOfProductionEntity {
        return YearOfProductionEntity(min.get(), max.get())
    }
}
