package project.lonelywheeler.ui.view.activity.main.fragment.modify

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import project.lonelywheeler.R
import project.lonelywheeler.databinding.FragmentModifyMotorVehicleBinding
import project.lonelywheeler.db.entity.offer.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.db.response.entityExistInDatabase
import project.lonelywheeler.db.response.hasMessage
import project.lonelywheeler.model.domain.offer.Condition
import project.lonelywheeler.model.domain.offer.vehicle.motor.*
import project.lonelywheeler.ui.viewmodel.main.MotorVehicleViewModel
import project.lonelywheeler.util.adapter.binding.populateFrom
import project.lonelywheeler.util.constants.INTENT_REQUEST_CODE_IMAGE
import project.lonelywheeler.util.constants.NO_OFFER_ID
import project.lonelywheeler.util.extensions.generateImage


@AndroidEntryPoint
class ModifyMotorVehicleFragment : Fragment() {

    private val viewModel: MotorVehicleViewModel by viewModels()
    private lateinit var offerId: String
    private lateinit var binding: FragmentModifyMotorVehicleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        offerId = arguments?.getString("offerId")!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentModifyMotorVehicleBinding.inflate(inflater, container, false)
        initSpinners()
        initViewModel()
        observeServerResponse()

        return binding.root
    }

    private fun initSpinners() {
        binding.apply {
            fragmentMotorVehicleSpnrCarBodyType.populateFrom(CarBodyType::class)
            fragmentMotorVehicleSpnrCarEmissionStandard.populateFrom(EmissionStandard::class)
            fragmentMotorVehicleSpnrCarFuelType.populateFrom(FuelType::class)
            fragmentMotorVehicleSpnrCarDrivetrain.populateFrom(Drivetrain::class)
            fragmentMotorVehicleSpnrCarGearbox.populateFrom(GearboxType::class)
            fragmentMotorVehicleSpnrCarSteeringWheelType.populateFrom(SteeringWheelSide::class)
            fragmentMotorVehicleProductBasicInfo.spnrProductCondition.populateFrom(Condition::class)
        }
    }

    private fun initViewModel() {
        if (offerId != NO_OFFER_ID) {
            viewModel.readOffer(offerId)
        }
        binding.viewModel = viewModel
    }

    private fun observeServerResponse() {
        viewModel.responseEntity.observe(viewLifecycleOwner, { response ->
            response.entity?.let {
                showMessage(response)
                if (response.entityExistInDatabase()) {
                    updateUi(response)
                }
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == INTENT_REQUEST_CODE_IMAGE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val bitmap = data!!.generateImage()
                viewModel.attachPicture(bitmap)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        initOnClickListeners()
        observeActionTriggered()
        observePictureChange()
    }

    fun initOnClickListeners() {
        binding.fragmentMotorVehicleProductBasicInfo.apply {
            btnAddPicture.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
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

    fun observeActionTriggered() {
        binding.fragmentMotorVehicleBtnConfirm.setOnClickListener {
            MaterialAlertDialogBuilder(
                requireContext(),
            )
                .setTitle(resources.getString(R.string.persist_vehicle_title))
                .setMessage(resources.getString(R.string.persit_vehicle_message))
                .setPositiveButton(resources.getString(R.string.yes)) { dialog, which ->
                    binding.fragmentMotorvehicleProgressBar.visibility = View.VISIBLE
                    viewModel.persist()
                }.setNegativeButton(resources.getString(R.string.no)) { dialog, which -> }
                .show()
        }
    }


    fun observePictureChange() {
        viewModel.displayedPicture.observe(viewLifecycleOwner, { picture ->
            binding.fragmentMotorVehicleProductBasicInfo.apply {
                val drawable = picture?.toDrawable(resources)
                ivProductPicture.setImageDrawable(drawable)
                ivProductPicture.refreshDrawableState()
            }
            binding.notifyChange()
            binding.executePendingBindings()
        })
    }


    private fun updateUi(response: MyResponse<MotorVehicleEntity>) {
        binding.viewModel!!.motorVehicle.value = response.entity?.toObservable()
//        binding.viewModel!!.motorVehicle.postValue(response.entity!!.toObservable())
        binding.notifyChange()
        binding.executePendingBindings()
    }

    private fun showMessage(response: MyResponse<MotorVehicleEntity>) {
        binding.fragmentMotorvehicleProgressBar.visibility = View.GONE
        if (response.hasMessage())
            showMessageFrom(response)
    }

    private fun goToMainFragment() {
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

    private fun displayImage(bitmap: Bitmap?) {
        Glide.with(this)
            .asBitmap()
            .load(bitmap)
            .into(binding.fragmentMotorVehicleProductBasicInfo.ivProductPicture)

        binding.notifyChange()
    }

}

inline fun <R> ObservableField<R>.observe(crossinline callback: (R) -> Unit) {
    this.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(p0: Observable?, p1: Int) {
            callback(get()!!)
        }
    })
}

