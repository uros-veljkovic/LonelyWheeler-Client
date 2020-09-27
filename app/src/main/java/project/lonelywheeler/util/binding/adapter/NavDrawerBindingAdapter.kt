package project.lonelywheeler.util.binding.adapter

import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.BindingAdapter
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.navigation.NavigationView
import com.google.android.material.shape.MaterialShapeDrawable
import kotlinx.android.synthetic.main.activity_main.*
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.ui.view.activity.main.bottomappbar.adapter.BottomAppBarCutCornersTopEdge
import project.lonelywheeler.util.FabAnimationProvider

/*@BindingAdapter(value = ["drawerLayout", "navDrawer", "trigger"], requireAll = true)
fun drawerTrigger(
    bottomAppBar: BottomAppBar,
    drawerLayout: DrawerLayout,
    navDrawer: NavigationView,
    drawerShouldOpen: Boolean
) {
    if (drawerShouldOpen) {
        drawerLayout.openDrawer(navDrawer)
    } else {
        drawerLayout.closeDrawer(navDrawer)
    }
}

@BindingAdapter("app:configBottomAppBar")
fun configBottomAppBar(bottomAppBar: BottomAppBar) {
    val topEdge = BottomAppBarCutCornersTopEdge(
        bottomAppBar.fabCradleMargin,
        bottomAppBar.fabCradleRoundedCornerRadius,
        bottomAppBar.cradleVerticalOffset
    )
    val background = bottomAppBar.background as MaterialShapeDrawable
    background.shapeAppearanceModel =
        background.shapeAppearanceModel.toBuilder().setTopEdge(topEdge).build()
}*/

class NavDrawerBindingAdapter {


/*    private fun configBottomAppBarOnClick() {
        ibtnNavDrawer.setOnClickListener {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(nav_drawer)
            } else {
                drawer_layout.openDrawer(GravityCompat.START)
            }
        }
    }*/
}