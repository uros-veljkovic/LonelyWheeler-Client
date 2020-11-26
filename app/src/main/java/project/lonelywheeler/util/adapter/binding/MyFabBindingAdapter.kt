package project.lonelywheeler.util.adapter.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import project.lonelywheeler.util.anim.FabAnimationProvider


@BindingAdapter("app:mainFabAnimation")
fun FloatingActionButton.mainFabAnimation(fabShouldOpen: Boolean) {
    if (fabShouldOpen)
        startAnimation(FabAnimationProvider.rotateOpen)
    else {
        startAnimation(FabAnimationProvider.rotateClose)
    }
}

@BindingAdapter("app:miniFabAnimation")
fun FloatingActionButton.miniFabAnimation(fabShouldAppear: Boolean) {
    if (fabShouldAppear) {
        visibility = View.VISIBLE
        startAnimation(FabAnimationProvider.fromBottom)
    } else {
        startAnimation(FabAnimationProvider.toBottom)
        visibility = View.GONE
    }
    isClickable = fabShouldAppear
}

