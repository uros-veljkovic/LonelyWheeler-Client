package project.lonelywheeler.ui.view.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import project.lonelywheeler.R
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.databinding.ActivityMainBinding
import project.lonelywheeler.ui.view.activity.main.bottomappbar.adapter.BottomAppBarCutCornersTopEdge
import project.lonelywheeler.ui.view.activity.signin.SignInActivity
import project.lonelywheeler.ui.viewmodel.main.ViewModelOffers
import project.lonelywheeler.util.adapter.recyclerview.AllOfferRecViewAdapter
import project.lonelywheeler.util.constants.*
import project.lonelywheeler.util.extensions.navigateWithDelayTo

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    AllOfferRecViewAdapter.OnOfferItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    private var fabTrigger: ObservableBoolean = ObservableBoolean(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFabClickListeners()
        configureUI()

        binding.fabTrigger = fabTrigger
    }

    private fun initFabClickListeners() {
        val bundle = bundleOf("offerId" to NO_OFFER_ID)
        binding.apply {
            activityMainFabEquipment.setOnClickListener {
                fabTrigger?.reverse()
                navController.navigateWithDelayTo(R.id.action_global_modifyEquipmentFragment,
                    bundle)
            }
            activityMainFabHumanPoweredVehicle.setOnClickListener {
                fabTrigger?.reverse()
                navController.navigateWithDelayTo(R.id.action_global_modifyPedestrianVehicleFragment,
                    bundle)
            }

            activityMainFabMotorVehicle.setOnClickListener {
                fabTrigger?.reverse()
                navController.navigateWithDelayTo(R.id.action_global_modifyMotorVehicleFragment,
                    bundle)
            }

            activityMainFabMain.setOnClickListener {
                fabTrigger?.reverse()
            }
        }

    }

    private fun configureUI() {
        configureNavigationComponent()
        configureBottomAppBar()
        configureNavigationDrawer()
    }


    private fun configureNavigationDrawer() {
        navDrawer.setNavigationItemSelectedListener(this)
    }


    private fun configureNavigationComponent() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_graph_container) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun configureBottomAppBar() {
        configBottomAppBarAppearance()
        configBottomAppBarOnClick()

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.previewAllOffersFragment,
                R.id.previewAllSellersFragment,
                R.id.previewSellerProfileFragment,
                R.id.previewSellerOffersFragment,
                R.id.modifyMotorVehicleFragment,
                R.id.modifyEquipmentFragment,
                R.id.modifyPedestrianVehicleFragment,
                R.id.advancedSearchFragment
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
        activityMain_btnNavDrawer.setOnClickListener {
            if (binding.activityMainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.activityMainDrawerLayout.closeDrawer(navDrawer)
            } else {
                binding.activityMainDrawerLayout.openDrawer(GravityCompat.START)
            }
        }

        activityMain_btnFavorites.setOnClickListener {
            val bundle = bundleOf(
                "sellerId" to MyApplication.getCurrentUserID(),
                "action" to ACTION_READ_FAVORITES
            )
            navController.navigate(R.id.action_global_previewSellerOffersFragment, bundle)
        }

        activityMain_btnPreviewProfile.setOnClickListener {
            val bundle = bundleOf("sellerId" to MyApplication.getCurrentUserID())
            navController.navigate(R.id.action_global_previewSellerProfileFragment, bundle)
        }

    }

    fun ObservableBoolean.reverse() {
        this.set(!this.get())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                loadAndShowOffers(ENTITY_TYPE_MOTOR_VEHICLE)
            }
            R.id.nav_pedestrian_vehicles -> {
                loadAndShowOffers(ENTITY_TYPE_PEDESTRIAN_VEHICLE)
            }
            R.id.nav_equipment -> {
                loadAndShowOffers(ENTITY_TYPE_EQUIPMENT)
            }
            R.id.nav_all_sellers -> {
                navController.navigate(R.id.action_global_previewAllSellersFragment)
            }
            R.id.nav_advanced_search -> {
                navController.navigate(R.id.action_global_advancedSearchFragment)
            }
            R.id.nav_my_offers -> {
                val bundle = bundleOf(
                    "sellerId" to MyApplication.getCurrentUserID(),
                    "action" to ACTION_READ_PERSONAL_OFFERS
                )
                navController.navigate(R.id.action_global_previewSellerOffersFragment, bundle)
            }
            R.id.nav_logout -> {
                MyApplication.currentUser = null
                logout()
            }
        }

        activityMain_drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadAndShowOffers(entityTypeId: Int) {
        GlobalScope.launch {
            startPreviewAllFragmentWith(entityTypeId)
        }
    }

    fun logout() {
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


