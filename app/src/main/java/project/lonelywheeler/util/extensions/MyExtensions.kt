package project.lonelywheeler.util.extensions

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import project.lonelywheeler.app.MyApplication
import java.util.*
import kotlin.concurrent.schedule

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


fun NavController.navigateWithDelayTo(actionId: Int, bundle: Bundle) {
    Timer().schedule(1000) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                navigate(actionId, bundle)
            }
        }
    }
}
