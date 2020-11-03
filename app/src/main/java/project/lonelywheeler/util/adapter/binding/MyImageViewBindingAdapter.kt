package project.lonelywheeler.util.adapter.binding

import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableInt
import project.lonelywheeler.R
import project.lonelywheeler.util.convertToBitmap
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

    if (list.size != 0) {
        val drawable = list[0].convertToBitmap().toDrawable(resources)
        this.setImageDrawable(drawable)
    } else {
        this.setImageDrawable(
            ContextCompat.getDrawable(this.context, R.drawable.ic_no_photos)
        )
        this.setPadding(64, 64, 64, 64)
    }
}
