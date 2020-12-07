package project.lonelywheeler.db.entity.query

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName


data class QueryEntity(
    val brand: String?,
    val model: String?,
    val price: PriceEntity,
    val yearOfProduction: YearOfProductionEntity,
    var dateModified: Long,
) {

/*
    private var queryFieldExclusionStrategy: ExclusionStrategy = object : ExclusionStrategy {
        override fun shouldSkipField(field: FieldAttributes): Boolean {
            val declaredClass = field.declaredClass
            Log.d("TAG", "shouldSkipField: ${declaredClass.simpleName}")
            return (
//                    field.declaringClass == QueryEntity::class.java
                    field.toString().isEmpty())
        }

        override fun shouldSkipClass(clazz: Class<*>?): Boolean {
            return false
        }
    }
*/

    override fun toString(): String {
        val gson = GsonBuilder()
//            .setExclusionStrategies(queryFieldExclusionStrategy)
            .setPrettyPrinting()
            .create()
        return gson.toJson(this)
    }
}
