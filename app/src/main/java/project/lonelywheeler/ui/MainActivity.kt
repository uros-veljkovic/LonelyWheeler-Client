package project.lonelywheeler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import com.google.android.material.shape.MaterialShapeDrawable
import kotlinx.android.synthetic.main.activity_main.*
import project.lonelywheeler.R
import project.lonelywheeler.ui.bottomappbar.adapter.BottomAppBarCutCornersTopEdge
import project.lonelywheeler.util.FabAnimationProvider

class MainActivity : AppCompatActivity() {

    private var clicked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configBottomAppBar()
        configBottomAppBarOnClick()

        mainFab.setOnClickListener {
            onMainFabClick()
        }
    }

    private fun configBottomAppBarOnClick() {
        ibtnNavDrawer.setOnClickListener {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(nav_drawer)
            } else {
                drawer_layout.openDrawer(GravityCompat.START)
            }
        }
    }

    private fun configBottomAppBar() {
        val topEdge = BottomAppBarCutCornersTopEdge(
            bottomAppBar.fabCradleMargin,
            bottomAppBar.fabCradleRoundedCornerRadius,
            bottomAppBar.cradleVerticalOffset
        )
        val background = bottomAppBar.background as MaterialShapeDrawable
        background.shapeAppearanceModel =
            background.shapeAppearanceModel.toBuilder().setTopEdge(topEdge).build()
    }

    private fun onMainFabClick() {
        clicked = !clicked
        setVisibility(clicked)
        setAnimation(clicked)

    }

    private fun setVisibility(clicked: Boolean) {
        if (clicked) {
            fab1.visibility = View.VISIBLE
            fab2.visibility = View.VISIBLE
            fab3.visibility = View.VISIBLE
        } else {
            fab1.visibility = View.GONE
            fab2.visibility = View.GONE
            fab3.visibility = View.GONE
        }
        fab1.isClickable = clicked
        fab2.isClickable = clicked
        fab3.isClickable = clicked
    }

    private fun setAnimation(clicked: Boolean) {
        if (clicked) {
            mainFab.startAnimation(FabAnimationProvider.rotateOpen)
            fab1.startAnimation(FabAnimationProvider.fromBottom)
            fab2.startAnimation(FabAnimationProvider.fromBottom)
            fab3.startAnimation(FabAnimationProvider.fromBottom)
        } else {
            mainFab.startAnimation(FabAnimationProvider.rotateClose)
            fab1.startAnimation(FabAnimationProvider.toBottom)
            fab2.startAnimation(FabAnimationProvider.toBottom)
            fab3.startAnimation(FabAnimationProvider.toBottom)

        }
    }


}