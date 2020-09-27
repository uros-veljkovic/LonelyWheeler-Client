package project.lonelywheeler.ui.view.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.databinding.ObservableBoolean
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import com.google.android.material.shape.MaterialShapeDrawable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import project.lonelywheeler.R
import project.lonelywheeler.databinding.ActivityMainBinding
import project.lonelywheeler.ui.view.activity.main.bottomappbar.adapter.BottomAppBarCutCornersTopEdge

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var binding: ActivityMainBinding
    private var fabTrigger: ObservableBoolean = ObservableBoolean(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configNavigationComponent()
        configBottomAppBar()
        configBottomAppBarOnClick()

        binding.fabTrigger = fabTrigger
        mainFab.setOnClickListener { fabTrigger.revers() }
    }

    private fun configNavigationComponent() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
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

    private fun configBottomAppBarOnClick() {
        ibtnNavDrawer.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(navDrawer)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    fun ObservableBoolean.revers() {
        this.set(!this.get())
    }
}

