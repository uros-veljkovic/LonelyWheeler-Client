package project.lonelywheeler.util

import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt
import kotlin.math.sqrt

fun Bitmap.convertToString(): String {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 80, baos)
    val b = baos.toByteArray()
    return Base64.encodeToString(b, Base64.DEFAULT)
}

fun MutableList<Bitmap>.convertToStringList(): MutableList<String> {
    return map {
        it.convertToString()
    } as MutableList;
}

fun Bitmap.compressTo(maxSize: Int): Bitmap {
    val bitmapHeight: Int = this.height
    val bitmapWidth: Int = this.width
    val ratioSquare: Double = (bitmapHeight * bitmapWidth / maxSize).toDouble()

    if (ratioSquare <= 1) return this

    val ratio = sqrt(ratioSquare)
    Log.d("mylog", "Ratio: $ratio")

    val requiredHeight = (bitmapHeight / ratio).roundToInt()
    val requiredWidth = (bitmapWidth / ratio).roundToInt()
    return Bitmap.createScaledBitmap(this, requiredWidth, requiredHeight, true)
}