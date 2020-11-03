package project.lonelywheeler.util.bitmap

import android.graphics.drawable.BitmapDrawable
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.flaviofaria.kenburnsview.KenBurnsView
import project.lonelywheeler.R
import project.lonelywheeler.util.string.MyStringUtils

@BindingAdapter("app:offerPicture")
fun KenBurnsView.setBitmapFromString(s: String?) {
    if (s.isNullOrEmpty()) {
        this.setImageDrawable(
            ContextCompat.getDrawable(
                this.context,
                R.drawable.ic_no_photos)
        )
    } else {
        this.setImageDrawable(
            MyStringUtils.convertToBitmap(s)
        )
    }
}