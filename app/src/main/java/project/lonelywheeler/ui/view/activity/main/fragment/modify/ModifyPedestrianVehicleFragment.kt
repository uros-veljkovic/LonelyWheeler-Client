package project.lonelywheeler.ui.view.activity.main.fragment.modify

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import project.lonelywheeler.R
import project.lonelywheeler.databinding.FragmentModifyPedestrianVehicleBinding
import project.lonelywheeler.db.entity.offer.vehicle.pedestrian.PedestrianVehicleEntity
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.db.response.hasMessage
import project.lonelywheeler.db.response.isInDatabase
import project.lonelywheeler.model.enums.offer.Condition
import project.lonelywheeler.model.enums.offer.vehicle.pedestrian.PedestrianVehicleType
import project.lonelywheeler.ui.viewmodel.main.ViewModelPedestrianVehicle
import project.lonelywheeler.util.constants.*
import project.lonelywheeler.util.extensions.*

@AndroidEntryPoint
class ModifyPedestrianVehicleFragment : Fragment() {

    private val viewModel: ViewModelPedestrianVehicle by viewModels()
    private lateinit var offerId: String
    private lateinit var action: String
    lateinit var binding: FragmentModifyPedestrianVehicleBinding

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
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.readOffer(offerId)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentModifyPedestrianVehicleBinding.inflate(inflater, container, false)
        initBinding()
        initSpinners()
        observeServerResponse()

        return binding.root
    }

    private fun initSpinners() {
        val vehicle = viewModel.vehicle
        binding.apply {
            vehicle.apply {
                fragmentPedestrianVehicleSpnrPedestrianVehicleType.initOrRefresh(
                    PedestrianVehicleType::class,
                    pedestrianVehicleType
                )
                fragmentPedestrianVehicleProductBasicInfo.spnrProductCondition.initOrRefresh(
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
                if (action == ACTION_CREATING_OFFER && response.isInDatabase()) {
                    resetUI()
                    showMessage(response)
                    goToMainFragment()
                } else {
                    updateUi()
                }
            }
        })
    }

    private fun resetUI() {
        initSpinners()
        viewModel.reset()
    }

    private fun showMessage(response: MyResponse<PedestrianVehicleEntity>) {
        if (response.hasMessage())
            showMessageFrom(response)
    }

    private fun showMessageFrom(response: MyResponse<PedestrianVehicleEntity>) {
        Snackbar.make(
            binding.fragmentPedestrianVehicleContainer,
            response.message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun goToMainFragment() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_global_previewAllOffersFragment)
        }, 2)
    }

    private fun updateUi() {
        viewModel.update()
        initSpinners()
        binding.invalidateAll()
    }


    override fun onResume() {
        super.onResume()

        initOnClickListeners()
        observeActionTriggered()
        observeSpinners()
    }

    private fun observeSpinners() {
        binding.apply {
            viewModel!!.vehicle.apply {
                fragmentPedestrianVehicleSpnrPedestrianVehicleType.observeChange { value ->
                    pedestrianVehicleType = PedestrianVehicleType.toEnum(value)
                }
            }
        }
    }


    private fun initOnClickListeners() {
        binding.fragmentPedestrianVehicleProductBasicInfo.apply {
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


    private fun observeActionTriggered() {
        binding.fragmentPedestrianVehicleBtnConfirm.setOnClickListener {
            MaterialAlertDialogBuilder(
                requireContext(),
            )
                .setTitle(resources.getString(R.string.persist_vehicle_title))
                .setMessage(resources.getString(R.string.persit_vehicle_message))
                .setPositiveButton(resources.getString(R.string.yes)) { dialog, which ->
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