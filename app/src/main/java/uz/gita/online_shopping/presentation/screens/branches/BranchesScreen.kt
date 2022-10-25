package uz.gita.online_shopping.presentation.screens.branches

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.online_shopping.R
import uz.gita.online_shopping.databinding.ScreenBranchesBinding
import uz.gita.online_shopping.presentation.viewmodels.BranchesViewModel
import uz.gita.online_shopping.presentation.viewmodels.impl.BranchesViewModelImpl
import uz.gita.online_shopping.utils.extensions.hideProgress
import uz.gita.online_shopping.utils.extensions.showErrorDialog
import uz.gita.online_shopping.utils.extensions.showMessageDialog
import uz.gita.online_shopping.utils.extensions.showProgress

// Created by Jamshid Isoqov an 10/21/2022
@AndroidEntryPoint
class BranchesScreen : Fragment(R.layout.screen_branches) {

    private val viewModel: BranchesViewModel by viewModels<BranchesViewModelImpl>()

    private val viewBinding: ScreenBranchesBinding by viewBinding()

    private val adapter: BranchesAdapter by lazy(LazyThreadSafetyMode.NONE) {
        BranchesAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listBranches.adapter = adapter

        viewModel.branchesFlow.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            viewModel.navigateToBranchDetails(it)
        }

        viewModel.loadingSharedFlow.observe(viewLifecycleOwner) {
            if (it) showProgress() else hideProgress()
        }

        viewModel.messageFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.errorMessageFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        viewBinding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }


}