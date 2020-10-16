package project.lonelywheeler.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

fun Bitmap.convertToString(): String {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 95, baos)
    val b = baos.toByteArray()
    return Base64.encodeToString(b, Base64.DEFAULT)
}

fun MutableList<Bitmap>.convertToStringList(): MutableList<String> {
    return map {
        it.convertToString()
    } as MutableList;
}

fun String.convertToBitmap(): Bitmap {
    val imageBytes = Base64.decode(this, 0)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}

fun MutableList<String>.convertToBitmapList(): MutableList<Bitmap> {
/*    return map {
        it.convertToBitmap()
    };*/

    return map {
        it.convertToBitmap()
    } as MutableList

}