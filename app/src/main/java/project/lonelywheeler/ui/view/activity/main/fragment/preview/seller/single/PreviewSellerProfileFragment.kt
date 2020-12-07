package project.lonelywheeler.ui.view.activity.main.fragment.preview.seller.single

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import project.lonelywheeler.R
import project.lonelywheeler.app.MyApplication
import project.lonelywheeler.databinding.FragmentPreviewSellerProfileBinding
import project.lonelywheeler.model.observable.liked.SellerRateCounterObservable
import project.lonelywheeler.model.observable.liked.UserLikingSellerObservable
import project.lonelywheeler.model.observable.user.UserObservable
import project.lonelywheeler.ui.view.activity.main.MainActivity
import project.lonelywheeler.ui.viewmodel.main.ViewModelProfile

@AndroidEntryPoint
class PreviewSellerProfileFragment : Fragment() {

    private val TAG = "PreviewSellerProfileFragment"
    private val viewModel: ViewModelProfile by activityViewModels()
    private val navArgs: PreviewSellerProfileFragmentArgs by navArgs()
    private val sellerId: String by lazy { navArgs.sellerId }
    private val userId: String by lazy { MyApplication.getCurrentUserID() }
    private lateinit var binding: FragmentPreviewSellerProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentPreviewSellerProfileBinding.inflate(inflater)
        binding.viewModel = viewModel

        initViewModel()
        observeServerResponse()
        return binding.root
    }

    private fun initViewModel() {
        CoroutineScope(IO).launch {
            launch {
                viewModel.loadSeller(sellerId)
            }
            launch {
                viewModel.loadLiking(userId, sellerId)
            }
            launch {
                viewModel.loadRateCounter(sellerId)
            }
        }

    }

    private fun observeServerResponse() {
        viewModel.responseLikingEntity.observe(viewLifecycleOwner, { response ->
            viewModel.liking = response
                .entity?.toObservable()
                ?: UserLikingSellerObservable()
            binding.invalidateAll()
        })
        viewModel.responseUserEntity.observe(viewLifecycleOwner, { response ->
            viewModel.userObservable = response
                .entity?.toObservable()
                ?: UserObservable()
            binding.invalidateAll()

        })
        viewModel.responseRateCounterEntity.observe(viewLifecycleOwner, { response ->
            viewModel.rateCounter = response
                .entity?.toObservable()
                ?: SellerRateCounterObservable()
            binding.invalidateAll()
        })

    }

    override fun onResume() {
        super.onResume()

        init()
    }

    fun init() {
        onClickListeners()
    }

    fun onClickListeners() {
        binding.apply {
            fragmentPreviewProfileBtnLike.setOnClickListener {
                viewModel!!.like(sellerId)
            }
            fragmentPreviewProfileBtnDislike.setOnClickListener {
                viewModel!!.dislike(sellerId)
            }
            fragmentPreviewProfileBtnDeleteProfile.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(resources.getString(R.string.delete_account))
                    .setMessage(resources.getString(R.string.delete_account_message))
                    .setPositiveButton(resources.getString(R.string.yes)) { dialog, which ->
                        viewModel!!.deleteProfile()
                        (activity as MainActivity).logout()
                    }.setNegativeButton(resources.getString(R.string.no)) { dialog, which -> }
                    .show()
            }
        }
    }
}