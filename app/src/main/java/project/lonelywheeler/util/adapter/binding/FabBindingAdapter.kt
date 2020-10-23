package project.lonelywheeler.util.adapter.binding

import android.view.View
import androidx.databinding.BindingAdapter
import project.lonelywheeler.util.anim.FabAnimationProvider


@BindingAdapter("app:mainFabAnimation")
fun mainFabAnimation(view: View, fabShouldOpen: Boolean) {
    if (fabShouldOpen)
        view.startAnimation(FabAnimationProvider.rotateOpen)
    else {
        view.startAnimation(FabAnimationProvider.rotateClose)
    }
}

@BindingAdapter("app:miniFabAnimation")
fun miniFabAnimation(view: View, fabShouldAppear: Boolean) {
    if (fabShouldAppear) {
        view.visibility = View.VISIBLE
        view.startAnimation(FabAnimationProvider.fromBottom)
    } else {
        view.startAnimation(FabAnimationProvider.toBottom)
        view.visibility = View.GONE
    }
    view.isClickable = fabShouldAppear
}

