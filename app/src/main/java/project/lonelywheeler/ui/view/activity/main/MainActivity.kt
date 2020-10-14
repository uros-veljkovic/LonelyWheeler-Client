package project.lonelywheeler.ui.view.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.databinding.ObservableBoolean
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.google.android.material.shape.MaterialShapeDrawable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import project.lonelywheeler.R
import project.lonelywheeler.databinding.ActivityMainBinding
import project.lonelywheeler.ui.view.activity.main.bottomappbar.adapter.BottomAppBarCutCornersTopEdge
import project.lonelywheeler.ui.view.activity.signin.SignInActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

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
        configNavigationDrawer()
        configBtnClickListener()

        binding.fabTrigger = fabTrigger
        activityMain_fabMain.setOnClickListener { fabTrigger.reverse() }
    }

    private fun configBtnClickListener() {
        activityMain_fabEquipment.setOnClickListener {
            fabTrigger.reverse()
        }
        activityMain_fabHumanPoweredVehicle.setOnClickListener {
            fabTrigger.reverse()
        }

        activityMain_fabMotorVehicle.setOnClickListener {
            fabTrigger.reverse()
            navController.navigate(R.id.action_global_updateOfferFragment)
        }
    }

    private fun configNavigationDrawer() {
        navDrawer.setNavigationItemSelectedListener(this)
    }


    private fun configNavigationComponent() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_graph_container) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun configBottomAppBar() {
        configBottomAppBarAppearance()
        configBottomAppBarOnClick()

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.previewEntitiesFragment,
                R.id.previewProfileFragment,
                R.id.myOffersFragment
            )
        )

        NavigationUI.setupWithNavController(
            activityMain_bottomAppBar,
            navController,
            appBarConfiguration
        )
    }

    private fun configBottomAppBarAppearance() {
        val topEdge = BottomAppBarCutCornersTopEdge(
            activityMain_bottomAppBar.fabCradleMargin,
            activityMain_bottomAppBar.fabCradleRoundedCornerRadius,
            activityMain_bottomAppBar.cradleVerticalOffset
        )
        val background = activityMain_bottomAppBar.background as MaterialShapeDrawable
        background.shapeAppearanceModel =
            background.shapeAppearanceModel.toBuilder().setTopEdge(topEdge).build()
    }

    private fun configBottomAppBarOnClick() {
        activityMain_ibtnNavDrawer.setOnClickListener {
            if (binding.activityMainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.activityMainDrawerLayout.closeDrawer(navDrawer)
            } else {
                binding.activityMainDrawerLayout.openDrawer(GravityCompat.START)
            }
        }

        activityMain_btnMyOffersFragment.setOnClickListener {
            navController.navigate(R.id.action_global_myOffersFragment)
        }

        activityMain_btnPreviewProfileFragment.setOnClickListener {
            navController.navigate(R.id.action_global_previewProfileFragment)
        }

    }

    fun ObservableBoolean.reverse() {
        this.set(!this.get())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home,
            R.id.nav_pedestrian_vehicles,
            R.id.nav_equipment,
            R.id.nav_all_sellers -> {
                val entityId = bundleOf("entityId" to item.itemId)
                navController.navigate(R.id.action_global_previewEntitiesFragment, entityId)
            }
            R.id.nav_advanced_search -> {
                Toast.makeText(this, "Advanced search", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_my_offers -> {
                navController.navigate(R.id.action_global_myOffersFragment)
            }
            R.id.nav_logout -> {
                startActivity(Intent(this, SignInActivity::class.java))
            }
        }

        activityMain_drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}

