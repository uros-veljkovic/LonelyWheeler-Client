package project.lonelywheeler.util.adapter.binding

import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import project.lonelywheeler.util.adapter.recyclerview.AllOfferRecViewAdapter
import project.lonelywheeler.util.convertToBitmap

@BindingAdapter("app:myAdapter")
fun RecyclerView.setAdapter(rvAdapter: AllOfferRecViewAdapter) {
    apply {
        layoutManager = GridLayoutManager(context, 2)
        setHasFixedSize(true)
        adapter = rvAdapter
    }
}

@BindingAdapter("app:mySrc")
fun ImageView.setMySrc(list: MutableList<String>) {
/*    if (list.size == 0) {
        drawable = ContextCompat.getDrawable(MyApplication.context(), R.drawable.ic_no_photos)!!
    } else {
        drawable = list[0].convertToBitmap().toDrawable(resources)
    }*/
    if (list.size != 0) {
        val drawable = list[0].convertToBitmap().toDrawable(resources)
        this.setImageDrawable(drawable)
    }
}