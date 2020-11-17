package project.lonelywheeler.util.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

fun String.convertToBitmap(): Bitmap {
    val imageBytes = Base64.decode(this, 0)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}

fun MutableList<String>.convertToBitmapList(): MutableList<Bitmap> {
    return map {
        it.convertToBitmap()
    } as MutableList
}