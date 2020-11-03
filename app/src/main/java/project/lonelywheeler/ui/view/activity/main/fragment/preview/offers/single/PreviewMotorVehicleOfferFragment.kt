package project.lonelywheeler.ui.view.activity.main.fragment.preview.offers.single

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import project.lonelywheeler.R
import project.lonelywheeler.databinding.FragmentPreviewMotorVehicleOfferBinding
import project.lonelywheeler.databinding.SingleOfferGeneralInfoBinding
import project.lonelywheeler.ui.viewmodel.main.MotorVehicleViewModel
import project.lonelywheeler.util.constants.INTENT_RC_CALL
import project.lonelywheeler.util.constants.INTENT_RC_MESSAGE
import project.lonelywheeler.util.extensions.decrease
import project.lonelywheeler.util.extensions.increase
import project.lonelywheeler.util.extensions.observe
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions


@AndroidEntryPoint
class PreviewMotorVehicleOfferFragment : Fragment() {

    private val TAG = "PreviewMotorVehicleOffer"
    private var binding: FragmentPreviewMotorVehicleOfferBinding? = null
    private val viewModel: MotorVehicleViewModel by viewModels()
    private val navArgs: PreviewMotorVehicleOfferFragmentArgs by navArgs()
    private lateinit var offerId: String
    private lateinit var sellerId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        offerId = navArgs.offerId
        sellerId = navArgs.sellerId

        viewModel.readOffer(offerId)
        viewModel.readSeller(sellerId)
        viewModel.readIfOfferLiked(offerId)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPreviewMotorVehicleOfferBinding.inflate(layoutInflater, container, false)

        initViewModel()
        observePictureChange()
        initOnClickListeners()

        return binding!!.root
    }


    private fun initViewModel() {
        binding!!.fragmentPreviewOfferProgressBar.visibility = View.VISIBLE
        viewModel.responseEntity.observe(viewLifecycleOwner, { offer ->
            offer.entity?.let { entity ->
                if (!entity.pictures.isNullOrEmpty()) {
                    viewModel.currentPictureIndex.set(0)
                    viewModel.lastPictureIndex.set(entity.pictures.lastIndex)
                } else {
                    viewModel.resetIndexes()
                }
            }
        })

        viewModel.responseSeller.observe(viewLifecycleOwner, { seller ->
            seller.entity?.id?.let {
                binding?.let {
                    it.viewModel = viewModel
                    it.fragmentPreviewOfferProgressBar.visibility = View.GONE
                    it.executePendingBindings()
                    it.notifyChange()
                }
            }
        })


    }

    private fun showInformativeDialog(
        title: String,
        message: String,
    ) {
        MaterialAlertDialogBuilder(
            requireContext(),
        )
            .setTitle(title)
            .setMessage(message)
            .setNeutralButton(resources.getString(R.string.ok)) { _, _ -> }
            .show()
    }

    private fun showConfirmationDialog(
        title: String,
        message: String,
        callback: () -> Unit
    ) {
        MaterialAlertDialogBuilder(
            requireContext(),
        )
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                callback.invoke()
            }
            .setNeutralButton(resources.getString(R.string.ok)) { _, _ -> }
            .show()
    }

    private fun initOnClickListeners() {
        binding!!.generalInfo.apply {
            btnNextPicture.setOnClickListener {
                viewModel.currentPictureIndex.increase()
                showPicture()
            }
            btnPreviousPicture.setOnClickListener {
                viewModel.currentPictureIndex.decrease()
                showPicture()
            }
            btnLike.setOnClickListener {
                viewModel.like()
            }
        }
        binding!!.fragmentPreviewOfferBtnTelephone.setOnClickListener {
            if (viewModel.sellerHasMobileNumber()) {
                showInformativeDialog(
                    resources.getString(R.string.action_unperformable),
                    resources.getString(R.string.action_unperformable_call)
                )

            } else {
                showConfirmationDialog(
                    resources.getString(R.string.call_seller),
                    resources.getString(R.string.call_seller_message),
                    ::callSeller
                )
            }
        }
        binding!!.fragmentPreviewOfferBtnMessage.setOnClickListener {
            if (viewModel.sellerHasMobileNumber()) {
                showInformativeDialog(
                    resources.getString(R.string.action_unperformable),
                    resources.getString(R.string.action_unperformable_call)
                )
            } else {
                showConfirmationDialog(
                    resources.getString(R.string.send_sms),
                    resources.getString(R.string.send_sms_message),
                    ::sendMessageToSeller
                )

            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(INTENT_RC_CALL)
    private fun callSeller() {
        val persmission = Manifest.permission.CALL_PHONE
        if (EasyPermissions.hasPermissions(requireContext(), persmission)) {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:${viewModel.getMobileNumber()}")
            startActivity(callIntent)
        } else {
            EasyPermissions.requestPermissions(
                this, getString(R.string.requres_call_permission),
                INTENT_RC_CALL, persmission
            );
        }
    }

    @AfterPermissionGranted(INTENT_RC_MESSAGE)
    private fun sendMessageToSeller() {
        val persmission = Manifest.permission.SEND_SMS
        if (EasyPermissions.hasPermissions(requireContext(), persmission)) {
            val smsIntent =
                Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", viewModel.getMobileNumber(), null))
            smsIntent.putExtra("sms_body", "")
            startActivity(smsIntent)
        } else {
            EasyPermissions.requestPermissions(
                this, getString(R.string.requires_sms_permission),
                INTENT_RC_MESSAGE, persmission
            );
        }
    }

    private fun SingleOfferGeneralInfoBinding.showPicture() {
        ivProductPicture.setImageDrawable(
            viewModel.getCurrentPictureFromEntity()
        )
        executePendingBindings()
    }

    private fun observePictureChange() {
        viewModel.currentPictureIndex.observe {
            if (viewModel.currentPictureIndex.get() != -1) {
                binding!!.generalInfo.ivProductPicture.setImageDrawable(
                    viewModel.getCurrentPictureFromEntity()
                )
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}