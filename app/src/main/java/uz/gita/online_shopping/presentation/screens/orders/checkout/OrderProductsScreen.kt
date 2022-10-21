package uz.gita.online_shopping.presentation.screens.orders.checkout

import android.annotation.SuppressLint
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
import uz.gita.online_shopping.data.models.ProductWithCount
import uz.gita.online_shopping.databinding.ListItemOrderProductsBinding
import uz.gita.online_shopping.databinding.ScreenOrderProductsBinding
import uz.gita.online_shopping.presentation.viewmodels.OrderProductsViewModel
import uz.gita.online_shopping.presentation.viewmodels.impl.OrderProductsViewModelImpl
import uz.gita.online_shopping.utils.Basket
import uz.gita.online_shopping.utils.extensions.*

// Created by Jamshid Isoqov an 10/10/2022
@AndroidEntryPoint
class OrderProductsScreen : Fragment(R.layout.screen_order_products) {

    private val viewModel: OrderProductsViewModel by viewModels<OrderProductsViewModelImpl>()

    private val viewBinding: ScreenOrderProductsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.loadingFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.errorFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.successFlow.onEach {
            showSuccessDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.deliveryAddress.onEach {
            viewBinding.tvMapDeliveryAddress.text = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.isDeliveryFlow.onEach {
            if (it) {
                viewBinding.imageCheckDelivery.visible()
                viewBinding.imageCheckOnTheWay.inVisible()
                viewBinding.containerMap.gone()
            } else {
                viewBinding.imageCheckDelivery.inVisible()
                viewBinding.imageCheckOnTheWay.visible()
                viewBinding.containerMap.visible()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        Basket.productsListLiveData.observe(viewLifecycleOwner) {
            loadOrders(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadOrders(list: List<ProductWithCount>) {
        for (i in list) {
            val orderBinding = ListItemOrderProductsBinding.inflate(layoutInflater)
            orderBinding.tvOrderProductNameWithCount.text = "${i.productData.name} ${i.count}"
            orderBinding.tvOrderProductPrice.text = i.productData.price.getFinanceType()
            viewBinding.productsContainer.addView(orderBinding.root)
        }
    }

}