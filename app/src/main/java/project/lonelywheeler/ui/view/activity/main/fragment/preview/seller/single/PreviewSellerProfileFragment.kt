package project.lonelywheeler.ui.view.activity.main.fragment.preview.seller.single

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import project.lonelywheeler.databinding.FragmentPreviewSellerProfileBinding
import project.lonelywheeler.ui.viewmodel.main.ViewModelProfile
import project.lonelywheeler.util.adapter.binding.setMySingleSrc

@AndroidEntryPoint
class PreviewSellerProfileFragment : Fragment() {

    private val viewModel: ViewModelProfile by viewModels()
    private val navArgs: PreviewSellerProfileFragmentArgs by navArgs()
    private val sellerId: String by lazy { navArgs.sellerId }
    private lateinit var binding: FragmentPreviewSellerProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPreviewSellerProfileBinding.inflate(inflater)

        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        viewModel.loadSeller(sellerId)
        viewModel.responseUser.observe(viewLifecycleOwner, { response ->
            response.entity?.let { entity ->
                binding.userEntity = entity
                binding.fragmentPreviewProfileIvUserPicture.setMySingleSrc(entity.accountInfoEntity.picture)
                binding.fragmentPreviewProfileIvUserPicture.refreshDrawableState()
                binding.executePendingBindings()
                binding.notifyChange()
            }
        })

    }
}