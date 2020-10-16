package project.lonelywheeler.ui.view.activity.main.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.ObservableInt
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import project.lonelywheeler.R
import project.lonelywheeler.databinding.FragmentMotorVehicleBinding
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.vehicle.motor.*
import project.lonelywheeler.ui.viewmodel.main.MotorVehicleViewModel
import project.lonelywheeler.util.binding.adapter.populateFrom
import project.lonelywheeler.util.constants.INTENT_REQUEST_CODE_IMAGE

@AndroidEntryPoint
class MotorVehicleFragment : Fragment() {

    private val TAG = "MotorVehicleFragment"
    val viewModel: MotorVehicleViewModel by viewModels()
    lateinit var binding: FragmentMotorVehicleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMotorVehicleBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        populateSpinners()
        initOnClickListeners()

        return binding.root
    }

    private fun populateSpinners() {
        binding.fragmentMotorVehicleSpnrCarBodyType.populateFrom(CarBodyType::class)
        binding.fragmentMotorVehicleSpnrCarEmissionStandard.populateFrom(EmissionStandard::class)
        binding.fragmentMotorVehicleSpnrCarFuelType.populateFrom(FuelType::class)
        binding.fragmentMotorVehicleSpnrCarDrivetrain.populateFrom(Drivetrain::class)
        binding.fragmentMotorVehicleSpnrCarGearbox.populateFrom(GearboxType::class)
        binding.fragmentMotorVehicleSpnrCarSteeringWheelType.populateFrom(SteeringWheelSide::class)
        binding.fragmentMotorVehicleProductBasicInfo.spnrProductCondition.populateFrom(Condition::class)
    }

    private fun initOnClickListeners() {
        binding.fragmentMotorVehicleProductBasicInfo.btnAddPicture.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, INTENT_REQUEST_CODE_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.motorVehicle.pictures.apply {

            if (resultCode == Activity.RESULT_OK && requestCode == INTENT_REQUEST_CODE_IMAGE) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                    val bitmap = ImageDecoder.decodeBitmap(
                        ImageDecoder.createSource(
                            requireContext().contentResolver,
                            data?.data!!
                        )
                    )
                    add(bitmap)
                    viewModel.lastPictureIndex.increase()
                    if (binding.fragmentMotorVehicleProductBasicInfo.ivProductPicture.drawable == null) {
                        binding.fragmentMotorVehicleProductBasicInfo.ivProductPicture.setImageDrawable(
                            bitmap.toDrawable(resources)
                        )
                    }
                    binding.notifyChange()
                    binding.executePendingBindings()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.response.observe(viewLifecycleOwner, { response ->
            Log.d(TAG, "onResume: \n\n${response.entity?.toString()}")

            if (!response.entity?.id.isNullOrEmpty()) {
                Snackbar.make(
                    requireActivity().findViewById(R.id.activityMain_drawerLayout),
                    response.message,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })

        observePictureChange()
    }

    private fun observePictureChange() {
        binding.fragmentMotorVehicleProductBasicInfo.btnNextPicture.setOnClickListener {
            viewModel.currentPictureIndex.increase()
            binding.fragmentMotorVehicleProductBasicInfo.ivProductPicture.setImageDrawable(
                (viewModel.motorVehicle.pictures.get(viewModel.currentPictureIndex.get())).toDrawable(
                    resources
                )
            )
            binding.notifyChange()
        }

        binding.fragmentMotorVehicleProductBasicInfo.btnPreviousPicture.setOnClickListener {
            viewModel.currentPictureIndex.decrease()
            binding.fragmentMotorVehicleProductBasicInfo.ivProductPicture.setImageDrawable(
                (viewModel.motorVehicle.pictures.get(viewModel.currentPictureIndex.get())).toDrawable(
                    resources
                )
            )
            binding.notifyChange()
        }
    }

    fun ObservableInt.increase() {
        this.set(this.get() + 1)
    }

    fun ObservableInt.decrease() {
        this.set(this.get() - 1)
    }

}
