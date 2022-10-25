package uz.gita.online_shopping.presentation.screens.orders.active

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
import uz.gita.online_shopping.databinding.PageActiveOrdersBinding
import uz.gita.online_shopping.presentation.viewmodels.ActiveOrderViewModel
import uz.gita.online_shopping.presentation.viewmodels.impl.ActiveOrderViewModelImpl
import uz.gita.online_shopping.utils.extensions.*

// Created by Jamshid Isoqov an 10/13/2022

@AndroidEntryPoint
class ActiveOrdersPage : Fragment(R.layout.page_active_orders) {

    private val viewModel: ActiveOrderViewModel by viewModels<ActiveOrderViewModelImpl>()

    private val viewBinding: PageActiveOrdersBinding by viewBinding()

    private val adapter: ActiveOrderAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ActiveOrderAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listActiveOrders.adapter = adapter

        viewModel.allActiveOrders.onEach {
            toast(it.size.toString())
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.loadingSharedFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.errorMessageFlow.onEach {
            showErrorDialog(message = it)
        }.launchIn(lifecycleScope)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllOrders()

    }

}