package project.lonelywheeler.ui.view.activity.main.fragment.preview.offers.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import project.lonelywheeler.databinding.FragmentPreviewAllOffersBinding
import project.lonelywheeler.ui.viewmodel.main.AllOffersViewModel
import project.lonelywheeler.util.adapter.recyclerview.OfferItemSmallRvAdapter
import project.lonelywheeler.util.constants.ENTITY_TYPE_EQUIPMENT
import project.lonelywheeler.util.constants.ENTITY_TYPE_MOTOR_VEHICLE
import project.lonelywheeler.util.constants.ENTITY_TYPE_PEDESTRIAN_VEHICLE
import project.lonelywheeler.util.decorator.ProductItemDecorator

@AndroidEntryPoint
class PreviewAllOffersFragment : Fragment(), OfferItemSmallRvAdapter.OnOfferItemClickListener {

    private val TAG = "PreviewAllOffers"
    private val viewModel: AllOffersViewModel by viewModels()
    private lateinit var binding: FragmentPreviewAllOffersBinding
    private var entityTypeId: Int? = null;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentPreviewAllOffersBinding.inflate(inflater, container, false)
        initBinding()
        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        entityTypeId = PreviewAllOffersFragmentArgs.fromBundle(requireArguments()).entityId
        viewModel.read(entityTypeId!!)

        viewModel.response.observe(viewLifecycleOwner, { response ->
            val entities = response.entity
            ((binding.fragmentAllOffersRvAllOffers.adapter) as OfferItemSmallRvAdapter).setList(
                entities ?: listOf()
            )
            binding.executePendingBindings()
            binding.notifyChange()
        })
    }

    private fun initBinding() {
        binding.viewModel = viewModel
        binding.fragmentAllOffersRvAllOffers.addItemDecoration(ProductItemDecorator(8, 16))
        binding.fragmentAllOffersRvAllOffers.adapter = OfferItemSmallRvAdapter(this)
    }

    override fun onOfferItemClick(position: Int) {
        val offerId = viewModel.response.value?.entity?.get(position)?._id
        val sellerId = viewModel.response.value?.entity?.get(position)?.sellerId

        when (entityTypeId) {
            ENTITY_TYPE_MOTOR_VEHICLE -> {
                val action =
                    PreviewAllOffersFragmentDirections.actionPreviewAllOffersFragmentToPreviewMotorVehicleFragment(
                        offerId!!,
                        sellerId!!
                    )
                findNavController().navigate(action)
            }
            ENTITY_TYPE_EQUIPMENT -> {
                val action =
                    PreviewAllOffersFragmentDirections.actionPreviewAllOffersFragmentToPreviewEquipmentOfferFragment(
                        offerId!!,
                        sellerId!!
                    )
                findNavController().navigate(action)
            }
            ENTITY_TYPE_PEDESTRIAN_VEHICLE -> {
                val action =
                    PreviewAllOffersFragmentDirections.actionPreviewAllOffersFragmentToPreviewPedestrianVehicleOfferFragment(
                        offerId!!,
                        sellerId!!
                    )
                findNavController().navigate(action)
            }
        }
    }
}