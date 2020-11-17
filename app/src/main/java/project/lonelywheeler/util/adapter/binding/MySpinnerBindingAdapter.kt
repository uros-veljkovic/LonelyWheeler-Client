package project.lonelywheeler.util.adapter.binding

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
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

@BindingAdapter("app:onSelected")
fun <T : Enum<*>> Spinner.onSelectedListener(field: String) {
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {
            if (parent != null) {
                val value = parent.getItemAtPosition(position) as String
                field
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

    }
}

