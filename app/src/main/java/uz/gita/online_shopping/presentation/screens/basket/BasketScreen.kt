package uz.gita.online_shopping.presentation.screens.basket

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.online_shopping.R
import uz.gita.online_shopping.data.models.ProductWithCount
import uz.gita.online_shopping.databinding.ScreenBasketBinding
import uz.gita.online_shopping.presentation.viewmodels.BasketViewModel
import uz.gita.online_shopping.presentation.viewmodels.impl.BasketViewModelImpl
import uz.gita.online_shopping.utils.Basket
import uz.gita.online_shopping.utils.extensions.getFinanceType

// Created by Jamshid Isoqov an 10/10/2022
@AndroidEntryPoint
class BasketScreen : Fragment(R.layout.screen_basket) {

    private val viewModel: BasketViewModel by viewModels<BasketViewModelImpl>()

    private var oldSum = 0.0

    private val viewBinding: ScreenBasketBinding by viewBinding()

    private val adapter: BasketAdapter by lazy(LazyThreadSafetyMode.NONE) {
        BasketAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listProducts.adapter = adapter

        viewBinding.listProducts.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        viewBinding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }

        adapter.setItemAddListener {
            viewModel.addProduct(it)
        }
        adapter.setItemDeleteListener {
            viewModel.deleteProduct(it)
        }
        adapter.setItemRemovedListener {
            viewModel.removeProduct(it)
        }
        Basket.productsListLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            tvPriceAnimator(it)
        }

    }

    @SuppressLint("Recycle")
    private fun tvPriceAnimator(list: List<ProductWithCount>) {
        var sum = 0.0
        list.forEach {
            sum += it.productData.price * it.count
        }
        ValueAnimator.ofFloat(oldSum.toFloat(), sum.toFloat()).apply {
            viewBinding.tvOrderValue.text = (animatedValue as Float).toBigDecimal().getFinanceType()
            duration = 1000L
            start()
        }
        oldSum = sum
    }
}