package project.lonelywheeler.util.adapter.binding

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableInt
import com.bumptech.glide.Glide
import project.lonelywheeler.R
import project.lonelywheeler.util.extensions.convertToBitmap


@BindingAdapter("app:pictures", "app:index")
fun ImageView.setPictures(pictures: MutableList<Bitmap>, index: ObservableInt) {
    var bitmap: Bitmap? = null

    if (index.get() >= 0 && pictures.size > 0) {
        bitmap = pictures[index.get()]
    }

    Glide.with(this.context)
        .load(bitmap)
        .centerCrop()
        .placeholder(null)
        .into(this)
    this.refreshDrawableState()
}

@BindingAdapter("app:mySrc")
fun ImageView.setMySrc(list: MutableList<String>) {

    var picture: Drawable? = null

    if (list.isNotEmpty()) {
        picture = list[0].convertToBitmap().toDrawable(resources)
    }

    Glide.with(this.context)
        .load(picture)
        .centerCrop()
        .placeholder(R.drawable.ic_no_photos)
        .into(this)
}

@BindingAdapter("app:mySingleSrc")
fun ImageView.setMySingleSrc(picture: String?) {

    val drawable = picture?.convertToBitmap()?.toDrawable(resources)

    Glide.with(this.context)
        .load(drawable)
        .centerCrop()
        .placeholder(R.drawable.ic_no_photos)
        .into(this)

}
