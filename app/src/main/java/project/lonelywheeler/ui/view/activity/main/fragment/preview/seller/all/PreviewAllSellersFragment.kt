package project.lonelywheeler.ui.view.activity.main.fragment.preview.seller.all

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
import project.lonelywheeler.databinding.FragmentPreviewAllSellersBinding
import project.lonelywheeler.db.entity.user.UserEntity
import project.lonelywheeler.ui.viewmodel.main.ViewModelSellers
import project.lonelywheeler.util.adapter.recyclerview.UserItemSmallRvAdapter
import project.lonelywheeler.util.decorator.SmallItemDecorator

@AndroidEntryPoint
class PreviewAllSellersFragment : Fragment(), UserItemSmallRvAdapter.OnOfferItemClickListener {

    private lateinit var binding: FragmentPreviewAllSellersBinding
    private val viewModel: ViewModelSellers by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentPreviewAllSellersBinding.inflate(inflater, container, false)

        initRecyclerView()
        initViewModel()
        observeViewModel()

        return binding.root
    }

    private fun initViewModel() {
        binding.viewModel = viewModel
    }

    private fun initRecyclerView() {
        val adapter = UserItemSmallRvAdapter(this)
        val decorator = SmallItemDecorator(8, 16)
        binding.fragmentAllUsersRecViewUsers.apply {
            this.adapter = adapter
            addItemDecoration(decorator)
        }
    }

    private fun observeViewModel() {
        viewModel.response.observe(viewLifecycleOwner, { response ->
            response.entity?.let {
                (binding.fragmentAllUsersRecViewUsers.adapter as UserItemSmallRvAdapter).setList(
                    response.entity as MutableList<UserEntity>)
            }

        })
    }

    override fun onOfferItemClick(position: Int) {
        val userEntity = viewModel.getItem(position)
        val userId = userEntity.id

        val action =
            PreviewAllSellersFragmentDirections
                .actionPreviewAllSellersFragmentToPreviewSellerProfileFragment(userId!!)
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        observeSellerSearch()
    }

    private fun observeSellerSearch() {
        binding.fragmentAllOffersEtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                filterUsers(s.toString())
            }
        })
    }

    private fun filterUsers(text: String) {
        val adapter = binding.fragmentAllUsersRecViewUsers.adapter as UserItemSmallRvAdapter
        val filteredOfferList: MutableList<UserEntity> = mutableListOf()

        if (text.isEmpty()) {
            adapter.setList(adapter.getFullList().toMutableList())
            return
        }

        for (user in adapter.getFullList()) {
            if (user.accountInfoEntity.username.contains(text, true)) {
                filteredOfferList.add(user)
            }
        }
        adapter.filterDisplayedList(filteredOfferList)
    }
}