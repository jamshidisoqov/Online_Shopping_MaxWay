package uz.gita.online_shopping.presentation.screens.branches.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.online_shopping.R
import uz.gita.online_shopping.databinding.ScreenBranchDetailsBinding
import uz.gita.online_shopping.presentation.viewmodels.BranchDetailsViewModel
import uz.gita.online_shopping.presentation.viewmodels.impl.BranchDetailsViewModelImpl
import uz.gita.online_shopping.utils.extensions.hideProgress
import uz.gita.online_shopping.utils.extensions.showErrorDialog
import uz.gita.online_shopping.utils.extensions.showMessageDialog
import uz.gita.online_shopping.utils.extensions.showProgress

// Created by Jamshid Isoqov an 10/25/2022
@AndroidEntryPoint
class BranchDetailsScreen : Fragment(R.layout.screen_branch_details) {

    private val viewBinding: ScreenBranchDetailsBinding by viewBinding()

    private val viewModel: BranchDetailsViewModel by viewModels<BranchDetailsViewModelImpl>()

    private val args: BranchDetailsScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }
        val branchData = args.branchData

        viewModel.address.onEach {
            viewBinding.tvLocation.text = it
        }.launchIn(lifecycleScope)

        viewModel.loadingFlow.onEach {
            if (it) showProgress() else
                hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.errorFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.getBranchLocation(branchData.address)

        Glide.with(requireContext())
            .load(branchData.imageUrl)
            .placeholder(R.drawable.place)
            .into(viewBinding.imageBranch)

        viewBinding.tvBranchName.text = branchData.name
        viewBinding.tvWay.text = branchData.way
        viewBinding.tvPhone.text = branchData.phone
        viewBinding.tvScheduleTime.text = branchData.schedule
    }

}