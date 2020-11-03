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
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.databinding.ActivityMainBinding
import project.lonelywheeler.ui.view.activity.main.bottomappbar.adapter.BottomAppBarCutCornersTopEdge
import project.lonelywheeler.ui.view.activity.signin.SignInActivity
import project.lonelywheeler.util.adapter.recyclerview.AllOfferRecViewAdapter
import project.lonelywheeler.util.constants.ENTITY_TYPE_EQUIPMENT
import project.lonelywheeler.util.constants.ENTITY_TYPE_MOTOR_VEHICLE
import project.lonelywheeler.util.constants.ENTITY_TYPE_PEDESTRIAN_VEHICLE
import project.lonelywheeler.util.extensions.navigateWithDelayTo

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    AllOfferRecViewAdapter.OnOfferItemClickListener {

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
        initFabClickListeners()

        binding.fabTrigger = fabTrigger
    }

    private fun initFabClickListeners() {
        binding.apply {
            activityMainFabEquipment.setOnClickListener {
                fabTrigger?.reverse()
                navController navigateWithDelayTo R.id.action_global_modifyEquipmentFragment
            }
            activityMainFabHumanPoweredVehicle.setOnClickListener {
                fabTrigger?.reverse()
                navController navigateWithDelayTo R.id.action_global_modifyPedestrianVehicleFragment
            }

            activityMainFabMotorVehicle.setOnClickListener {
                fabTrigger?.reverse()
                navController navigateWithDelayTo R.id.action_global_modifyMotorVehicleFragment
            }

            activityMainFabMain.setOnClickListener {
                fabTrigger?.reverse()
            }
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
                R.id.previewAllOffersFragment,
                R.id.previewProfileFragment,
                R.id.previewSellerOffersFragment,
                R.id.modifyMotorVehicleFragment,
                R.id.modifyEquipmentFragment
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
            val bundle = bundleOf("sellerId" to MyApplication.currentUserId)
            navController.navigate(R.id.action_global_sellersOffersFragment, bundle)
        }

        activityMain_btnPreviewProfileFragment.setOnClickListener {
            val bundle = bundleOf("userId" to MyApplication.currentUserId)
            navController.navigate(R.id.action_global_previewProfileFragment, bundle)
        }

    }

    fun ObservableBoolean.reverse() {
        this.set(!this.get())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                startPreviewAllFragmentWith(ENTITY_TYPE_MOTOR_VEHICLE)
            }
            R.id.nav_pedestrian_vehicles -> {
                startPreviewAllFragmentWith(ENTITY_TYPE_PEDESTRIAN_VEHICLE)
            }
            R.id.nav_equipment -> {
                startPreviewAllFragmentWith(ENTITY_TYPE_EQUIPMENT)
            }
            R.id.nav_all_sellers -> {
                Toast.makeText(this, "All sellers fragment !", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_advanced_search -> {
                Toast.makeText(this, "Advanced search", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_my_offers -> {
                val bundle = bundleOf("sellerId" to MyApplication.currentUserId!!)
                navController.navigate(R.id.action_global_sellersOffersFragment, bundle)
            }
            R.id.nav_logout -> {
                logout()
            }
        }

        activityMain_drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun logout() {
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

    private fun startPreviewAllFragmentWith(entityTypeId: Int) {
        val entityId = bundleOf("entityId" to entityTypeId)
        navController.navigate(R.id.action_global_previewAllOffersFragment, entityId)
    }

    override fun onOfferItemClick(position: Int) {
        TODO("Not yet implemented")
    }

}


