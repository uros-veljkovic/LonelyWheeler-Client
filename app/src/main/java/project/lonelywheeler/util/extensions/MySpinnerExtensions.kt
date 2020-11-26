package project.lonelywheeler.util.extensions

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlin.reflect.KClass

/*fun <E : Enum<*>> Spinner.populateFrom(myenum: KClass<E>) {
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
}*/

fun Spinner.observeChange(callback: (String) -> Unit) {
    onItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                val serializedEnum = parent?.getItemAtPosition(position).toString()
                callback(serializedEnum)
            }
        }
}

fun <E : Enum<*>> Spinner.initOrRefresh(enumerationClass: KClass<E>, selectedEnum: E?) {
    if (this.adapter == null) {
        val newAdapter =
            ArrayAdapter(
                this.context,
                android.R.layout.simple_list_item_1,
                enumerationClass.java.enumConstants
            )
        this.adapter = newAdapter
    }
    selectedEnum?.ordinal?.let { this.setSelection(it) }
}