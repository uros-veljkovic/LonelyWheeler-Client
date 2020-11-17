package project.lonelywheeler.ui.view.activity.main.fragment.preview.seller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import project.lonelywheeler.R
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.databinding.FragmentPreviewSellerOffersBinding
import project.lonelywheeler.db.entity.offer.equipment.EquipmentEntity
import project.lonelywheeler.db.entity.offer.vehicle.motor.MotorVehicleEntity
import project.lonelywheeler.db.entity.offer.vehicle.pedestrian.PedestrianVehicleEntity
import project.lonelywheeler.ui.viewmodel.main.ViewModelPersonalOffers
import project.lonelywheeler.util.adapter.recyclerview.OfferItemBigRvAdapter
import project.lonelywheeler.util.constants.ACTION_READ_FAVORITES
import project.lonelywheeler.util.decorator.SmallItemDecorator

@AndroidEntryPoint
class PreviewSellersOffersFragment : Fragment(), OfferItemBigRvAdapter.OnMyOfferItemClickListener {

    private val TAG = "PreviewSellersOffersFra"
    private val viewModel: ViewModelPersonalOffers by viewModels()
    lateinit var userID: String
    lateinit var action: String
    lateinit var binding: FragmentPreviewSellerOffersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentPreviewSellerOffersBinding.inflate(inflater, container, false)
        initSellerId()
        attachViewModel()
        attachAdapter()
        observeLoadingOffers()
        return binding.root
    }

    private fun initSellerId() {
        val bundleSellerId = arguments?.getString("sellerId").toString()
        val bundleAction = arguments?.getString("action").toString()

        userID = bundleSellerId
        action = bundleAction
    }

    private fun attachViewModel() {
        if (action == ACTION_READ_FAVORITES) {
            viewModel.loadFavorites(MyApplication.getCurrentUserID())
        } else {
            viewModel.loadPersonalOffers(userID)
        }
        binding.viewModel = viewModel
    }

    private fun attachAdapter() {
        val adapter = OfferItemBigRvAdapter(this, userID)
        binding.adapter = adapter

    }

    private fun observeLoadingOffers() {
        viewModel.offers.observe(viewLifecycleOwner, { offers ->
            binding.myOffersFragmentRvMyOffers.addItemDecoration(SmallItemDecorator(16, 16))
            binding.myOffersFragmentRvMyOffers.layoutManager = LinearLayoutManager(this.context)
            binding.adapter!!.setList(offers.entity!!)
            binding.executePendingBindings()
            binding.notifyChange()
        })
    }

    override fun onUpdate(position: Int) {
        val offer = binding.adapter!!.getItem(position)
        val offerId = offer._id
        var action: NavDirections? = null

        when (offer.entityClassSimpleName) {
            MotorVehicleEntity::class.simpleName -> {
                action =
                    PreviewSellersOffersFragmentDirections.actionPreviewSellerOffersFragmentToModifyMotorVehicleFragment(
                        offerId!!,
                    )
            }
            PedestrianVehicleEntity::class.simpleName -> {
                action =
                    PreviewSellersOffersFragmentDirections.actionPreviewSellerOffersFragmentToModifyPedestrianVehicleFragment(
                        offerId!!,
                    )
            }
            EquipmentEntity::class.simpleName -> {
                action =
                    PreviewSellersOffersFragmentDirections.actionPreviewSellerOffersFragmentToModifyEquipmentFragment(
                        offerId!!,
                    )
            }
        }
        findNavController().navigate(action!!)
    }

    override fun onDelete(position: Int) {
        MaterialAlertDialogBuilder(
            requireContext(),
        ).setTitle(resources.getString(R.string.delete_offer_title))
            .setMessage(resources.getString(R.string.delete_offer_message))
            .setPositiveButton(resources.getString(R.string.yes)) { dialog, which ->
                val entity = binding.adapter!!.deleteItem(position)
                viewModel.deleteOffer(entity)
            }.setNegativeButton(resources.getString(R.string.no)) { dialog, which -> }
            .show()
    }

}
