package uz.gita.online_shopping.presentation.screens.orders.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.online_shopping.R
import uz.gita.online_shopping.databinding.PageHistoryOrdersBinding
import uz.gita.online_shopping.presentation.viewmodels.HistoryOrderViewModel
import uz.gita.online_shopping.presentation.viewmodels.impl.HistoryOrderViewModelImpl
import uz.gita.online_shopping.utils.extensions.hideProgress
import uz.gita.online_shopping.utils.extensions.showErrorDialog
import uz.gita.online_shopping.utils.extensions.showMessageDialog
import uz.gita.online_shopping.utils.extensions.showProgress

// Created by Jamshid Isoqov an 10/13/2022
@AndroidEntryPoint
class HistoryOrdersPage : Fragment(R.layout.page_history_orders) {

    private val viewModel: HistoryOrderViewModel by viewModels<HistoryOrderViewModelImpl>()

    private val viewBinding: PageHistoryOrdersBinding by viewBinding()

    private val adapter: HistoryOrderAdapter by lazy(LazyThreadSafetyMode.NONE) {
        HistoryOrderAdapter()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.listHistoryOrders.adapter = adapter
        viewModel.allHistoryOrders.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            viewModel.navigateToOrderDetails(it)
        }

        viewModel.loadingSharedFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.errorMessageFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)
    }
}