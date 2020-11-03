package project.lonelywheeler.ui.view.activity.main.fragment.modify

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import project.lonelywheeler.R
import project.lonelywheeler.databinding.FragmentModifyMotorVehicleBinding
import project.lonelywheeler.db.entity.offfer.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.entity.offfer.vehicle.motor.toPojo
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.model.domain.offer.Condition
import project.lonelywheeler.model.domain.offer.vehicle.motor.*
import project.lonelywheeler.ui.viewmodel.main.MotorVehicleViewModel
import project.lonelywheeler.util.adapter.binding.populateFrom
import project.lonelywheeler.util.constants.INTENT_REQUEST_CODE_IMAGE
import project.lonelywheeler.util.constants.RESOLUTION_1080X768
import project.lonelywheeler.util.extensions.compressTo
import project.lonelywheeler.util.extensions.decrease
import project.lonelywheeler.util.extensions.generateImage
import project.lonelywheeler.util.extensions.increase


@AndroidEntryPoint
class ModifyMotorVehicleFragment : Fragment() {

    private val TAG = "MotorVehicleFragment"
    private val viewModel: MotorVehicleViewModel by viewModels()
    private lateinit var binding: FragmentModifyMotorVehicleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentModifyMotorVehicleBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        populateSpinners()
        initOnClickListeners()

        return binding.root
    }

    private fun populateSpinners() {
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

    private fun initOnClickListeners() {
        binding.fragmentMotorVehicleProductBasicInfo.btnAddPicture.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, INTENT_REQUEST_CODE_IMAGE)
        }

        /*
        * TODO: Premestiti celu logiku za brisanje slike u viewModel i kreirati promenljivu
        *       displayedPicture: MutableLiveDate<Bitmap> preko koje ce se direktno update-ovati slika na UI
        *       iz ovog fragmenta
        * */
        binding.fragmentMotorVehicleProductBasicInfo.btnRemovePicture.setOnClickListener {
            viewModel.motorVehicle.pictures.also { pictures ->
                if (pictures.lastIndex == viewModel.getIndexOfCurrentPicture()) {
                    if (pictures.size == 1) {
                        displayImage(null)
                    } else {
                        displayImage(pictures[viewModel.getIndexOfCurrentPicture() - 1])
                    }
                    pictures.removeAt(viewModel.getIndexOfCurrentPicture())
                    viewModel.currentPictureIndex.decrease()
                    viewModel.lastPictureIndex.decrease()

                } else {
                    displayImage(pictures[viewModel.getIndexOfCurrentPicture() + 1])
                    viewModel.lastPictureIndex.decrease()
                    pictures.removeAt(viewModel.getIndexOfCurrentPicture())
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.motorVehicle.pictures.also { pictures ->

            if (resultCode == Activity.RESULT_OK && requestCode == INTENT_REQUEST_CODE_IMAGE) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                    val bitmap = data!!.generateImage()
                    if (viewModel.isNewVehicle()) {
                        pictures.add(bitmap.compressTo(RESOLUTION_1080X768))
                    } else {
                        pictures.add(bitmap)
                    }

                    viewModel.currentPictureIndex.increase()
                    viewModel.lastPictureIndex.increase()

                    displayImage(bitmap)
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()

        observeActionTriggered()
        observeServerResponse()
        observePictureChange()
    }

    private fun observeActionTriggered() {
        binding.fragmentMotorVehicleBtnConfirm.setOnClickListener {
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

    private fun observeServerResponse() {
        viewModel.responseEntity.observe(viewLifecycleOwner, { response ->
            if (!response.entity?._id.isNullOrEmpty()) {
                showMessageFrom(response)
                viewModel.motorVehicle = response.entity?.toPojo()!!
                binding.notifyChange()
            } else {
                showMessageFrom(response)
            }
        })
    }

    private fun showMessageFrom(response: MyResponse<MotorVehicleEntity>) {
        Snackbar.make(
            requireActivity().findViewById(R.id.activityMain_drawerLayout),
            response.message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun observePictureChange() {
        binding.fragmentMotorVehicleProductBasicInfo.btnNextPicture.setOnClickListener {
            viewModel.currentPictureIndex.increase()
            displayImage(viewModel.getCurrentPicture())
        }

        binding.fragmentMotorVehicleProductBasicInfo.btnPreviousPicture.setOnClickListener {
            viewModel.currentPictureIndex.decrease()
            displayImage(viewModel.getCurrentPicture())
        }
    }

    private fun displayImage(bitmap: Bitmap?) {
        binding.fragmentMotorVehicleProductBasicInfo.ivProductPicture.setImageDrawable(
            bitmap?.toDrawable(
                resources
            )
        )
        binding.notifyChange()
    }

}

