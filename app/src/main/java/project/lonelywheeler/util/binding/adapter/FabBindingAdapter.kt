package project.lonelywheeler.util.binding.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import kotlinx.android.synthetic.main.activity_main.*
import project.lonelywheeler.util.FabAnimationProvider


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
        \\TODO: Replace position of two lines below
        view.visibility = View.GONE
        view.startAnimation(FabAnimationProvider.toBottom)
    }
    view.isClickable = fabShouldAppear
}

class FabBindingAdapter {


}
