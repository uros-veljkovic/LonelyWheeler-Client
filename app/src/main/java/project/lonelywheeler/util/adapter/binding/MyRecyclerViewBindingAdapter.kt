package project.lonelywheeler.util.adapter.binding

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import project.lonelywheeler.R
import project.lonelywheeler.util.adapter.recyclerview.AllOfferRecViewAdapter

@BindingAdapter("app:myAdapter")
fun RecyclerView.setAdapter(rvAdapter: AllOfferRecViewAdapter) {
    apply {
        layoutManager = GridLayoutManager(context, 2)
        setHasFixedSize(true)
        adapter = rvAdapter
    }
}

@BindingAdapter("app:linearLayoutAdapter")
fun RecyclerView.setAdapter(adapter: RecyclerView.Adapter<*>) {

    this.layoutManager = LinearLayoutManager(this.context)
    this.setHasFixedSize(true)

    this.adapter = adapter
}


