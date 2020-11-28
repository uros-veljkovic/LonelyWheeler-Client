package project.lonelywheeler.ui.view.activity.main.fragment.modify

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.product_basic_info.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import project.lonelywheeler.R
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.databinding.FragmentModifyMotorVehicleBinding
import project.lonelywheeler.db.entity.offer.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.db.response.isInDatabase
import project.lonelywheeler.model.enums.offer.Condition
import project.lonelywheeler.model.enums.offer.vehicle.motor.*
import project.lonelywheeler.ui.viewmodel.main.ViewModelMotorVehicle
import project.lonelywheeler.util.constants.ACTION_CREATING_OFFER
import project.lonelywheeler.util.constants.ACTION_UPDATING_OFFER
import project.lonelywheeler.util.constants.INTENT_REQUEST_CODE_IMAGE
import project.lonelywheeler.util.constants.NO_OFFER_ID
import project.lonelywheeler.util.extensions.initOrRefresh
import project.lonelywheeler.util.extensions.observeChange


@AndroidEntryPoint
class ModifyMotorVehicleFragment : Fragment() {

    private val TAG = "ModifyMotorVehicleFragment"

    private val viewModel: ViewModelMotorVehicle by viewModels()
    private lateinit var offerId: String
    private lateinit var action: String
    private lateinit var binding: FragmentModifyMotorVehicleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        offerId = arguments?.getString("offerId")!!
        determineFragmentActionAndTriggerViewModel()

    }

    private fun determineFragmentActionAndTriggerViewModel() {
        if (offerId == NO_OFFER_ID) {
            action = ACTION_CREATING_OFFER
            viewModel.reset()
        } else {
            action = ACTION_UPDATING_OFFER
            CoroutineScope(IO).launch {
                viewModel.readOffer(offerId)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentModifyMotorVehicleBinding.inflate(inflater, container, false)
        initSpinners()
        initBinding()
        observeServerResponse()

        return binding.root
    }

    private fun initSpinners() {
        val vehicle = viewModel.motorVehicle
        binding.apply {
            vehicle.apply {
                fragmentMotorVehicleSpnrCarBodyType.initOrRefresh(
                    CarBodyType::class,
                    carBodyType
                )
                fragmentMotorVehicleSpnrCarEmissionStandard.initOrRefresh(
                    EmissionStandard::class,
                    emissionStandard
                )
                fragmentMotorVehicleSpnrCarFuelType.initOrRefresh(
                    FuelType::class,
                    fuelType
                )
                fragmentMotorVehicleSpnrCarDrivetrain.initOrRefresh(
                    Drivetrain::class,
                    drivetrain
                )
                fragmentMotorVehicleSpnrCarGearbox.initOrRefresh(
                    GearboxType::class,
                    gearboxType
                )
                fragmentMotorVehicleSpnrCarSteeringWheelType.initOrRefresh(
                    SteeringWheelSide::class,
                    steeringWheelSide
                )
                fragmentMotorVehicleProductBasicInfo.spnrProductCondition.initOrRefresh(
                    Condition::class,
                    condition
                )
            }

        }
    }

    private fun initBinding() {
        binding.viewModel = viewModel
    }

    private fun observeServerResponse() {
        viewModel.responseEntity.observe(viewLifecycleOwner, { response ->
            response.entity?.let {
                /*
                    Ako je stigao odgovor sa servera i ponuda do tada nije postojala
                    znaci da smo kreirali ponudu i da se vracamo na fragment allOffers
                */
                //TODO: Osmisli logiku
                if (action equals ACTION_CREATING_OFFER && response.isInDatabase()) {
                    resetUI()
                    showMessage(response)
                    navigateToMainFragment()
                } else {
                    updateUi()
                }
            }
        })

    }

    infix fun Any?.equals(value: Any?) : Boolean{
        return this == value
    }

    private fun resetUI() {
        initSpinners()
        viewModel.reset()
    }


    override fun onResume() {
        super.onResume()

        initOnClickListeners()
        observeActionTriggered()
        observeSpinners()
    }
    private fun initOnClickListeners() {
        binding.fragmentMotorVehicleProductBasicInfo.apply {
            btnAddPicture.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_PICK
                intent.type = "image/*"
                startActivityForResult(intent, INTENT_REQUEST_CODE_IMAGE)
            }

            btnRemovePicture.setOnClickListener {
                viewModel.removePicture()
            }

            btnNextPicture.setOnClickListener {
                viewModel.nextPicture()
            }

            btnPreviousPicture.setOnClickListener {
                viewModel.previousPicture()
            }
        }
    }

    private fun observeSpinners() {
        binding.apply {
            viewModel!!.motorVehicle.apply {
                fragmentMotorVehicleSpnrCarBodyType.observeChange { value ->
                    carBodyType = CarBodyType.valueOfSerialized(value)
                }
                fragmentMotorVehicleSpnrCarDrivetrain.observeChange { value ->
                    drivetrain = Drivetrain.valueOf(value)
                }
                fragmentMotorVehicleSpnrCarEmissionStandard.observeChange { value ->
                    emissionStandard = EmissionStandard.valueOfSerialized(value)
                }
                fragmentMotorVehicleSpnrCarFuelType.observeChange { value ->
                    fuelType = FuelType.valueOfSerialized(value)
                }
                fragmentMotorVehicleSpnrCarGearbox.observeChange { value ->
                    gearboxType = GearboxType.valueOf(value)
                }
                fragmentMotorVehicleSpnrCarSteeringWheelType.observeChange { value ->
                    steeringWheelSide = SteeringWheelSide.valueOf(value)
                }
                spnrProductCondition.observeChange { value ->
                    condition = Condition.valueOfSerialized(value)
                }
            }

        }
    }

    private fun updateUi() {
        viewModel.update()
        initSpinners()
        binding.invalidateAll()
    }

    private fun showMessage(response: MyResponse<MotorVehicleEntity>) {
        binding.fragmentMotorvehicleProgressBar.visibility = View.GONE
        if (response.hasMessage())
            showMessageFrom(response)
    }

    private fun navigateToMainFragment() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_global_previewAllOffersFragment)
        }, 2)
    }

    private fun showMessageFrom(response: MyResponse<MotorVehicleEntity>) {
        Snackbar.make(
            binding.fragmentMotorVehicleContainer,
            response.message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun observeActionTriggered() {
        binding.fragmentMotorVehicleBtnConfirm.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.persist_vehicle_title))
                .setMessage(resources.getString(R.string.persit_vehicle_message))
                .setPositiveButton(resources.getString(R.string.yes)) { dialog, which ->
                    binding.fragmentMotorvehicleProgressBar.visibility = View.VISIBLE
                    viewModel.persist()
                }.setNegativeButton(resources.getString(R.string.no)) { dialog, which -> }
                .show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK &&
            requestCode == INTENT_REQUEST_CODE_IMAGE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                val picture = data!!.getBitmap()
                viewModel.attach(picture)

                binding.executePendingBindings()
                binding.invalidateAll()
            }
        }
    }

}

inline fun <R> ObservableField<R>.observe(crossinline callback: (R) -> Unit) {
    this.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(p0: Observable?, p1: Int) {
            callback(get()!!)
        }
    })
}

fun Intent.getBitmap(): Bitmap {
    val inputStream =
        MyApplication.context().contentResolver.openInputStream(this.data!!)
    return BitmapFactory.decodeStream(inputStream)
}



