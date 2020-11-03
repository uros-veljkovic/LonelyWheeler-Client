package project.lonelywheeler.util.string

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import androidx.core.graphics.drawable.toDrawable
import project.lonelywheeler.app.MyApplication
import java.text.NumberFormat
import java.util.*

object MyStringUtils {

    fun convertToBitmap(s: String): BitmapDrawable {
        val imageBytes = Base64.decode(s, 0)
        val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        return bitmap.toDrawable(MyApplication.context().resources)
    }

    fun getDollarValue(value: Double): String {
        val format = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("USD")

        return format.format(value)
    }
}