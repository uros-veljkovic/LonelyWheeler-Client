package project.lonelywheeler.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import androidx.core.graphics.drawable.toDrawable
import project.lonelywheeler.app.MyApplication

object StringUtili {

    fun convertToBitmap(s: String): BitmapDrawable {
        val imageBytes = Base64.decode(s, 0)
        val bitmap =  BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        return bitmap.toDrawable(MyApplication.context().resources)
    }
}