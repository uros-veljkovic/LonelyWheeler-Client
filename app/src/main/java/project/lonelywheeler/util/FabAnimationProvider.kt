package project.lonelywheeler.util

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import project.lonelywheeler.R
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.app.MyApplication.Companion.application

object FabAnimationProvider {

    val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            application!!.applicationContext,
            R.anim.rotate_open_anim
        )
    }

    val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            application!!.applicationContext,
            R.anim.rotate_close_anim
        )
    }

    val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            application!!.applicationContext,
            R.anim.from_bottom_anim
        )
    }

    val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            application!!.applicationContext,
            R.anim.to_bottom_anim
        )
    }
}