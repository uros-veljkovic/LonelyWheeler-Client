package project.lonelywheeler.util.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.util.*
import kotlin.reflect.KClass

fun String.convertToBitmap(): Bitmap {
    val imageBytes = Base64.decode(this, 0)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}

fun MutableList<String>.convertToBitmapList(): MutableList<Bitmap> {
    return map {
        it.convertToBitmap()
    } as MutableList
}


fun <T : Enum<T>> String.toEnum(enum: KClass<T>): T {
    val words = this.split(" ")
    val separator = ""

    val newValue = words.joinToString(separator) { word ->
        word.capitalize(Locale.ROOT)
    }

    return java.lang.Enum.valueOf(enum.java, newValue)
}

/**
 * To camel case turns a sentence like
 *  "I am Mitar Miric the great"
 *              into
 *  "IAmMitarMiricTheGreat"
 *
 */
fun String.toCamelCase(): String {
    return this
        .split(" ")
        .map { word ->
            word.capitalize()
        }.joinToString("")
}

fun String.removeFirst(): String {
    return this.removeRange(0, 1)
}

