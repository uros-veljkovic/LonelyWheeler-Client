package project.lonelywheeler.ui.view.activity.main.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import project.lonelywheeler.R
import project.lonelywheeler.databinding.FragmentModifyPedestrianVehicleBinding
import project.lonelywheeler.db.entity.product.vehicle.pedestrian.PedestrianVehicleEntity
import project.lonelywheeler.db.entity.product.vehicle.pedestrian.toPojo
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.equipment.EquipmentType
import project.lonelywheeler.ui.viewmodel.main.PedestrianVehicleViewModel
import project.lonelywheeler.util.adapter.binding.populateFrom
import project.lonelywheeler.util.constants.INTENT_REQUEST_CODE_IMAGE
import project.lonelywheeler.util.constants.RESOLUTION_1080X768

@AndroidEntryPoint
class ModifyPedestrianVehicleFragment : Fragment() {
    private val TAG = "ModifyPedestrianVehicleFragment"
    val viewModel: PedestrianVehicleViewModel by viewModels()
    lateinit var binding: FragmentModifyPedestrianVehicleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentModifyPedestrianVehicleBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        populateSpinners()
        initOnClickListeners()

        return binding.root
    }

    private fun populateSpinners() {
        binding.fragmentPedestrianVehicleSpnrPedestrianVehicleType.populateFrom(EquipmentType::class)
        binding.fragmentPedestrianVehicleProductBasicInfo.spnrProductCondition.populateFrom(
            Condition::class
        )
    }

    private fun initOnClickListeners() {
        binding.fragmentPedestrianVehicleProductBasicInfo.btnAddPicture.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, INTENT_REQUEST_CODE_IMAGE)
        }

        binding.fragmentPedestrianVehicleProductBasicInfo.btnRemovePicture.setOnClickListener {
            viewModel.vehicle.pictures.also { pictures ->
                if (pictures.lastIndex == viewModel.getIndexOfCurrentPicture()) {
                    if (pictures.size == 1) {
                        displayImage(null)
                    } else {
                        displayImage(pictures[viewModel.getIndexOfCurrentPicture() - 1])
                    }
                    viewModel.currentPictureIndex.decrease()
                    viewModel.lastPictureIndex.decrease()
                    pictures.removeAt(viewModel.getIndexOfCurrentPicture())

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
        viewModel.vehicle.pictures.also { pictures ->

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

    @RequiresApi(Build.VERSION_CODES.P)
    private fun Intent.generateImage(): Bitmap {
        return ImageDecoder.decodeBitmap(
            ImageDecoder.createSource(
                requireContext().contentResolver,
                this.data!!
            )
        )
    }

    override fun onResume() {
        super.onResume()

        observeActionTriggered()
        observeServerResponse()
        observePictureChange()
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

    private fun observeServerResponse() {
        viewModel.response.observe(viewLifecycleOwner, { response ->
            if (!response.entity?._id.isNullOrEmpty()) {
                showMessageFrom(response)
                viewModel.vehicle = response.entity?.toPojo()!!
                binding.notifyChange()
            } else {
                showMessageFrom(response)
            }
        })
    }

    private fun showMessageFrom(response: MyResponse<PedestrianVehicleEntity>) {
        Snackbar.make(
            requireActivity().findViewById(R.id.activityMain_drawerLayout),
            response.message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun observePictureChange() {
        binding.fragmentPedestrianVehicleProductBasicInfo.btnNextPicture.setOnClickListener {
            viewModel.currentPictureIndex.increase()
            displayImage(viewModel.getCurrentPicture())
        }

        binding.fragmentPedestrianVehicleProductBasicInfo.btnPreviousPicture.setOnClickListener {
            viewModel.currentPictureIndex.decrease()
            displayImage(viewModel.getCurrentPicture())
        }
    }

    private fun displayImage(bitmap: Bitmap?) {
        binding.fragmentPedestrianVehicleProductBasicInfo.ivProductPicture.setImageDrawable(
            bitmap?.toDrawable(
                resources
            )
        )
        binding.notifyChange()
    }

}