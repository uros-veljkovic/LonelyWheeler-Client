package project.lonelywheeler.ui.view.activity.main.fragment.preview.offers.seller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import project.lonelywheeler.databinding.FragmentPreviewSellerOffersBinding
import project.lonelywheeler.ui.viewmodel.main.SellerOfferViewModel
import project.lonelywheeler.util.adapter.recyclerview.OfferItemBigRvAdapter
import project.lonelywheeler.util.decorator.ProductItemDecorator

@AndroidEntryPoint
class PreviewSellersOffersFragment : Fragment(), OfferItemBigRvAdapter.OnOfferItemClickListener {

    private val TAG = "PreviewSellersOffersFra"
    private val viewModel: SellerOfferViewModel by viewModels()
    lateinit var sellerId: String
    lateinit var binding: FragmentPreviewSellerOffersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentPreviewSellerOffersBinding.inflate(inflater, container, false)
        initSellerId()
        attachViewModel()
        attachAdapter()
        loadOffers()
        return binding.root
    }

    private fun initSellerId() {
        val bundleSellerId = arguments?.getString("sellerId").toString()
        Log.d(TAG, "Bundle seller id: $bundleSellerId")
        sellerId = bundleSellerId
    }

    private fun attachViewModel() {
        binding.viewModel = viewModel
        viewModel.loadOffers(sellerId)
    }

    private fun attachAdapter() {
        val adapter = OfferItemBigRvAdapter(this)
        binding.adapter = adapter

    }

    private fun loadOffers() {
        viewModel.offers.observe(viewLifecycleOwner, { offers ->
            Log.d(TAG, "loadOffers: ${offers.entity}")
            binding.myOffersFragmentRvMyOffers.addItemDecoration(ProductItemDecorator(16,16))
            binding.myOffersFragmentRvMyOffers.layoutManager = LinearLayoutManager(this.context)
            binding.adapter!!.setList(offers.entity!!)
            binding.executePendingBindings()
            binding.notifyChange()
        })
    }

    override fun onOfferItemClick(position: Int) {
        TODO("Not yet implemented")
    }

}