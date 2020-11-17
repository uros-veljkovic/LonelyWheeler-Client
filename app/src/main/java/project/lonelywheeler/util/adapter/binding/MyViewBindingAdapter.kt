package project.lonelywheeler.util.adapter.binding

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter
import bloder.com.blitzcore.enableWhen
import project.lonelywheeler.app.MyApplication

@BindingAdapter("app:shouldDisplayToHost")
fun View.shouldDisplayToHost(userID: String?) {
    userID?.let {
        visibility = if (userID == MyApplication.getCurrentUserID()) {
            VISIBLE
        } else {
            GONE
        }
    }
}

@BindingAdapter("app:shouldDisplayToGuest")
fun View.shouldDisplayToGuest(userID: String?) {
    userID?.let {
        visibility = if (userID != MyApplication.getCurrentUserID()) {
            VISIBLE
        } else {
            GONE
        }
    }
}


@BindingAdapter("app:shouldDisableTo")
fun View.shouldBeDisabledTo(userID: String?) {
    userID?.let {
        enableWhen {
            userID != MyApplication.getCurrentUserID()
        }
    }
}
