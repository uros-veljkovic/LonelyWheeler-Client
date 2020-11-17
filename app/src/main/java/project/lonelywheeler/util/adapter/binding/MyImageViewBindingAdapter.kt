package project.lonelywheeler.util.adapter.binding

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableInt
import com.bumptech.glide.Glide
import project.lonelywheeler.R
import project.lonelywheeler.util.extensions.convertToBitmap
import project.lonelywheeler.util.string.MyStringUtils


object ImageViewUtil {

    fun showIfExist(
        pictures: MutableList<String>,
        currentPictureIndex: ObservableInt,
    ): BitmapDrawable? {
        if (pictures.isNullOrEmpty() || currentPictureIndex.get() < 0)
            return null
        else {
            return MyStringUtils.convertToBitmap(pictures[currentPictureIndex.get()])
        }
    }

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

// MyWay
/*    if (list.size != 0) {
        val drawable = list[0].convertToBitmap().toDrawable(resources)
        this.setImageDrawable(drawable)
        this.refreshDrawableState()
    } else {
        this.setImageDrawable(
            ContextCompat.getDrawable(this.context, R.drawable.ic_no_photos)
        )
        this.setPadding(64, 64, 64, 64)
    }*/
}

@BindingAdapter("app:mySingleSrc")
fun ImageView.setMySingleSrc(picture: String?) {

    val drawable = picture?.convertToBitmap()?.toDrawable(resources)

    Glide.with(this.context)
        .load(drawable)
        .centerCrop()
        .placeholder(R.drawable.ic_no_photos)
        .into(this)

    /*
    var drawable: Drawable? = null

    if (!picture.isNullOrEmpty()) {
        drawable = picture.convertToBitmap().toDrawable(resources)
    } else {
        drawable = ContextCompat.getDrawable(this.context, R.drawable.ic_no_photos)
        this.setPadding(64, 64, 64, 64)
    }
    this.setImageDrawable(drawable)
    this.refreshDrawableState()
*/

}
