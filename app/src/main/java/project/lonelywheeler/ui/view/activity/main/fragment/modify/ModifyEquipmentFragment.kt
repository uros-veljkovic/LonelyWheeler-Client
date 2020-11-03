package project.lonelywheeler.ui.view.activity.main.fragment.modify

import android.app.Activity
import android.content.Intent
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
import project.lonelywheeler.databinding.FragmentModifyEquipmentBinding
import project.lonelywheeler.db.entity.offfer.equipment.EquipmentEntity
import project.lonelywheeler.db.entity.offfer.equipment.toPojo
import project.lonelywheeler.db.response.MyResponse
import project.lonelywheeler.model.domain.offer.Condition
import project.lonelywheeler.model.domain.offer.equipment.EquipmentType
import project.lonelywheeler.ui.viewmodel.main.EquipmentViewModel
import project.lonelywheeler.util.adapter.binding.populateFrom
import project.lonelywheeler.util.constants.INTENT_REQUEST_CODE_IMAGE
import project.lonelywheeler.util.extensions.generateImage

@AndroidEntryPoint
class ModifyEquipmentFragment : Fragment() {

    val viewModel: EquipmentViewModel by viewModels()
    lateinit var binding: FragmentModifyEquipmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentModifyEquipmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        initSpinners()

        return binding.root
    }

    private fun initSpinners() {
        binding.fragmentMotorVehicleSpnrEquipmentType.populateFrom(EquipmentType::class)
        binding.fragmentMotorVehicleProductBasicInfo.spnrProductCondition.populateFrom(Condition::class)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        viewModel.equipment.pictures.also { pictures ->
            if (resultCode == Activity.RESULT_OK && requestCode == INTENT_REQUEST_CODE_IMAGE) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                    val bitmap = data!!.generateImage()
                    viewModel.attachPicture(bitmap)
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
                .setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                    viewModel.persist()
                }.setNegativeButton(resources.getString(R.string.no)) { _, _ -> }
                .show()
        }
    }

    private fun observeServerResponse() {
        viewModel.responseEntity.observe(viewLifecycleOwner, { response ->
            if (!response.entity?._id.isNullOrEmpty()) {
                showMessageFrom(response)
                viewModel.equipment = response.entity?.toPojo()!!
                binding.notifyChange()
            } else {
                showMessageFrom(response)
            }
        })
    }

    private fun showMessageFrom(response: MyResponse<EquipmentEntity>) {
        Snackbar.make(
            requireActivity().findViewById(R.id.activityMain_drawerLayout),
            response.message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun observePictureChange() {

        viewModel.displayedPicture.observe(viewLifecycleOwner, { displayedImage ->
            binding.fragmentMotorVehicleProductBasicInfo.ivProductPicture.setImageDrawable(
                displayedImage?.toDrawable(resources)
            )
            binding.executePendingBindings()
            binding.notifyChange()
        })

        binding.fragmentMotorVehicleProductBasicInfo.apply {
            btnNextPicture.setOnClickListener {
                viewModel.nextPicture()
            }

            btnPreviousPicture.setOnClickListener {
                viewModel.previousPicture()
            }

            btnAddPicture.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, INTENT_REQUEST_CODE_IMAGE)
            }

            btnRemovePicture.setOnClickListener {
                viewModel.removePicture()
            }
        }
    }

}

