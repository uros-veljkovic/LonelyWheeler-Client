package project.lonelywheeler.util.binding.adapter

import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlin.reflect.KClass

fun <E : Enum<*>> Spinner.populateFrom(myenum: KClass<E>) {
    val list = arrayListOf<String>()
    myenum.java.enumConstants.forEach {
        list.add(it.toString())
    }
    val enumAdapter =
        ArrayAdapter(
            this.context,
            android.R.layout.simple_list_item_1,
            list
        )

    this.adapter = enumAdapter
}
