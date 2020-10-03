package project.lonelywheeler.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

fun Bitmap.toByteArray(): ByteArray {
    ByteArrayOutputStream().apply {
        compress(Bitmap.CompressFormat.JPEG, 10, this)
        return toByteArray()
    }
}

fun List<Bitmap>.toListOfByteArrays(): List<ByteArray> {
    val listOfByteArray = arrayListOf<ByteArray>()

    for (bitmap in this) {
        listOfByteArray.add(bitmap.toByteArray())
    }

    return listOfByteArray
}

fun ByteArray.toBitmap(): Bitmap {
    return BitmapFactory.decodeByteArray(this, 0, size)
}

fun List<ByteArray>.toBitmapList(): List<Bitmap> {
    val bitmapList = arrayListOf<Bitmap>()

    for (byteArray in this) {
        bitmapList.add(byteArray.toBitmap())
    }

    return bitmapList
}