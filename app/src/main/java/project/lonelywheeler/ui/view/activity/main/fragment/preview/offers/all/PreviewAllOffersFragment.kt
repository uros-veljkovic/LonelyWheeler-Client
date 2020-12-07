package project.lonelywheeler.ui.view.activity.main.fragment.preview.offers.all

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import project.lonelywheeler.databinding.FragmentPreviewAllOffersBinding
import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.ui.viewmodel.main.ViewModelOffers
import project.lonelywheeler.util.adapter.recyclerview.OfferItemSmallRvAdapter
import project.lonelywheeler.util.constants.ENTITY_TYPE_EQUIPMENT
import project.lonelywheeler.util.constants.ENTITY_TYPE_MOTOR_VEHICLE
import project.lonelywheeler.util.constants.ENTITY_TYPE_PEDESTRIAN_VEHICLE
import project.lonelywheeler.util.decorator.ItemDecoratorSmallGridLayout
import kotlin.properties.Delegates


@AndroidEntryPoint
class PreviewAllOffersFragment : Fragment(), OfferItemSmallRvAdapter.OnOfferItemClickListener {

    private val viewModel: ViewModelOffers by activityViewModels()
    private lateinit var binding: FragmentPreviewAllOffersBinding
    private var entityTypeId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        entityTypeId = PreviewAllOffersFragmentArgs.fromBundle(requireArguments()).entityId
/*        CoroutineScope(IO).launch{
            viewModel.read(entityTypeId)
        }*/
    }

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
        viewModel.response.observe(viewLifecycleOwner, { response ->
            val entities = response.entity
            ((binding.fragmentAllOffersRvAllOffers.adapter) as OfferItemSmallRvAdapter).setList(
                entities?.toMutableList() ?: mutableListOf()
            )
        })
    }

    private fun initBinding() {
        binding.viewModel = viewModel
        binding.fragmentAllOffersRvAllOffers.addItemDecoration(ItemDecoratorSmallGridLayout(8, 16))
        binding.fragmentAllOffersRvAllOffers.adapter =
            OfferItemSmallRvAdapter(this, viewModel.getOffers().toMutableList())

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

    override fun onResume() {
        super.onResume()

        binding.fragmentAllOffersEtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }
        })
    }

    private fun filter(text: String) {
        val adapter = binding.fragmentAllOffersRvAllOffers.adapter as OfferItemSmallRvAdapter
        val filteredOfferList: MutableList<OfferEntity> = mutableListOf()

        if (text.isEmpty()) {
            adapter.setList(adapter.getFullList().toMutableList())
            return
        }

        for (offer in adapter.getFullList()) {
            if (offer.basicInfo.brand.contains(text, true)
                || offer.basicInfo.model!!.contains(text, true)
            ) {
                filteredOfferList.add(offer)
            }
        }
        adapter.filterPreviewedList(filteredOfferList)
    }
}