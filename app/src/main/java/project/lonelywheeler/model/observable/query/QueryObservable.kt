package project.lonelywheeler.model.observable.query

import android.util.Log
import androidx.databinding.ObservableInt
import dagger.hilt.android.scopes.ActivityRetainedScoped
import project.lonelywheeler.db.entity.query.QueryEntity
import java.util.*
import javax.inject.Inject

@ActivityRetainedScoped
class QueryObservable
@Inject
constructor() {

    var brand = "Opel"
    var model = "Astra"
    var price = PriceObservable()
    val yearOfProduction = YearOfProductionObservable()
    var maxOfferAge = ObservableInt(7)

    fun toEntity(): QueryEntity {
        val calendar1 = Calendar.getInstance()
        calendar1.add(Calendar.DAY_OF_YEAR, -maxOfferAge.get())
        val dateModifiedLong = calendar1.timeInMillis

            /*return QueryEntity(
                if (brand.isEmpty()) null else brand,
                if (model.isEmpty()) null else model,
                price.toEntity(),
                yearOfProduction.toEntity(),
                dateModifiedLong
            )*/
        return QueryEntity(
            brand,
            model,
            price.toEntity(),
            yearOfProduction.toEntity(),
            dateModifiedLong
        )
    }
}

