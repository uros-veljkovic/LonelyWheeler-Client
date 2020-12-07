package project.lonelywheeler.ui.view.activity.main.fragment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import bloder.com.blitzcore.enableWhenUsing
import dagger.hilt.android.AndroidEntryPoint
import project.lonelywheeler.databinding.FragmentAdvancedSearchBinding
import project.lonelywheeler.ui.viewmodel.main.ViewModelAdvancedSearch
import project.lonelywheeler.util.adapter.recyclerview.OfferItemSmallRvAdapter
import project.lonelywheeler.util.constants.SIGN_BIGGER_THEN
import project.lonelywheeler.util.constants.SIGN_LESS_THEN
import project.lonelywheeler.util.decorator.ItemDecoratorSmallLinearLayoutHorizontal
import project.lonelywheeler.util.extensions.observeComparison
import project.lonelywheeler.util.validator.FieldValidator


@AndroidEntryPoint
class AdvancedSearchFragment : Fragment(), OfferItemSmallRvAdapter.OnOfferItemClickListener {

    private val TAG = "AdvancedSearchFragment"
    private val viewModel: ViewModelAdvancedSearch by viewModels()
    private lateinit var binding: FragmentAdvancedSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentAdvancedSearchBinding.inflate(inflater)

        initBinding()
        initOnClickListeners()
        observeQueryEnabler()

        return binding.root
    }

    fun initBinding() {
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        binding.fragmentAdvancedSearchRvQueriedOffers.adapter =
            OfferItemSmallRvAdapter(this, mutableListOf())
        binding.fragmentAdvancedSearchRvQueriedOffers.addItemDecoration(
            ItemDecoratorSmallLinearLayoutHorizontal(8)
        )
        binding.fragmentAdvancedSearchRvQueriedOffers.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun initViewModel() {
        binding.query = viewModel.query
    }

    override fun onResume() {
        super.onResume()

        observeEditTexts()
    }

    fun observeEditTexts() {
        observeSiblingFields()
        observeServerResponse()
    }

    private fun observeQueryEnabler() {
        binding.apply {
            fragmentAdvancedSearchBtnSearch.enableWhenUsing(FieldValidator()) {
                fragmentAdvancedSearchEtPriceFrom.hasNoError()
                fragmentAdvancedSearchEtPriceTo.hasNoError()
                fragmentAdvancedSearchEtMaximumOfferAge.hasNoError()
                fragmentAdvancedSearchEtYearOfProductionMax.hasNoError()
                fragmentAdvancedSearchEtYearOfProductionMin.hasNoError()
            }
        }


    }

    private fun observeSiblingFields() {
        observeSiblingPriceFields()
        observeSiblingYearFields()
    }

    private fun observeSiblingPriceFields() {
        binding.apply {
            fragmentAdvancedSearchEtPriceFrom.observeComparison(
                SIGN_LESS_THEN,
                fragmentAdvancedSearchEtPriceTo
            )
            fragmentAdvancedSearchEtPriceTo.observeComparison(
                SIGN_BIGGER_THEN,
                fragmentAdvancedSearchEtPriceFrom
            )
        }

    }

    private fun observeSiblingYearFields() {
        binding.apply {
            fragmentAdvancedSearchEtYearOfProductionMin.observeComparison(
                SIGN_LESS_THEN,
                fragmentAdvancedSearchEtYearOfProductionMax
            )
            fragmentAdvancedSearchEtYearOfProductionMax.observeComparison(
                SIGN_BIGGER_THEN,
                fragmentAdvancedSearchEtYearOfProductionMin
            )
        }
    }

    fun observeServerResponse() {
        viewModel.list.observe(viewLifecycleOwner, { response ->
            response.entity?.let {
                val adapter =
                    binding.fragmentAdvancedSearchRvQueriedOffers.adapter as OfferItemSmallRvAdapter
                adapter.setList(response.entity!!)
            }
        })
    }


    fun initOnClickListeners() {
        binding.fragmentAdvancedSearchBtnSearch.setOnClickListener {
            viewModel.sendQuery()
        }
    }

    override fun onOfferItemClick(position: Int) {
        TODO("Not yet implemented")
    }


}