package project.lonelywheeler.util.string

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import androidx.core.graphics.drawable.toDrawable
import project.lonelywheeler.app.MyApplication

object MyStringUtils {

    fun convertToBitmap(s: String): BitmapDrawable {
        val imageBytes = Base64.decode(s, 0)
        val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        return bitmap.toDrawable(MyApplication.context().resources)
    }

    fun getDollarValue(value: Double): String {
        var price = value.toInt()
        val priceArray: MutableList<String> = mutableListOf()
//        if (price / 1000 >= 1) {
            while (true) {
                if (price / 1000 > 0) {
                    price /= 1000
                    priceArray.add(0, ".000")
                    continue
                } else {
                    priceArray.add(0, "$${price}")
                    break
                }
            }
//        } else {
//            return "$$price"
//        }

        val bulider = StringBuilder()
        priceArray.forEach { item ->
            bulider.append(item)
        }


        return bulider.toString()
    }
}