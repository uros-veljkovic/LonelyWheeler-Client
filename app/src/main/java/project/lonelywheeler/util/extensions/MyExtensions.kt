package project.lonelywheeler.util.extensions

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.db.entity.offfer.OfferEntity
import project.lonelywheeler.db.response.MyResponse
import java.util.*
import kotlin.concurrent.schedule
import kotlin.math.roundToInt
import kotlin.math.sqrt

inline fun <R> ObservableField<R>.observe(crossinline callback: (R) -> Unit) {
    this.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(p0: Observable?, p1: Int) {
            get()?.let { callback(it) }
        }
    })
}

inline fun ObservableBoolean.observe(crossinline callback: (Boolean) -> Unit) {
    this.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(p0: Observable?, p1: Int) {
            callback(get())
        }
    })
}

inline fun ObservableInt.observe(crossinline callback: (Int) -> Unit) {
    this.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(p0: Observable?, p1: Int) {
            callback(get())
        }
    })
}

fun ObservableInt.increase() {
    this.set(this.get() + 1)
}

fun ObservableInt.decrease() {
    this.set(this.get() - 1)
}

@RequiresApi(Build.VERSION_CODES.P)
fun Intent.generateImage(): Bitmap {
    return ImageDecoder.decodeBitmap(
        ImageDecoder.createSource(
            MyApplication.context().contentResolver,
            this.data!!
        )
    )
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


infix fun NavController.navigateWithDelayTo(actionId: Int) {
    Timer().schedule(1000) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                navigate(actionId)
            }
        }
    }
}
