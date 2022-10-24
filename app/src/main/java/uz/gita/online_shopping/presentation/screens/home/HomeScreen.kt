package uz.gita.online_shopping.presentation.screens.home

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
import uz.gita.online_shopping.databinding.ScreenHomeBinding
import uz.gita.online_shopping.presentation.viewmodels.HomeViewModel
import uz.gita.online_shopping.presentation.viewmodels.impl.HomeViewModelImpl
import uz.gita.online_shopping.utils.Basket
import uz.gita.online_shopping.utils.extensions.hideProgress
import uz.gita.online_shopping.utils.extensions.showProgress

// Created by Jamshid Isoqov an 10/10/2022
@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {

    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()

    private val viewBinding: ScreenHomeBinding by viewBinding()

    private val categoryAdapter: CategoryAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CategoryAdapter()
    }
    private val productAdapter: ProductsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ProductsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllCategories()
        viewModel.getAllProducts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listCategories.adapter = categoryAdapter

        viewBinding.listProducts.adapter = productAdapter

        viewModel.loadingSharedFlow.observe(viewLifecycleOwner) {
            if (it) showProgress() else hideProgress()
        }

        viewBinding.tvSearch.setOnClickListener {
            viewModel.searchClicked()
        }


        productAdapter.setItemBasketClickListener {
            viewModel.addBasket(it)
        }
        productAdapter.setOpenBasketClickListener {
            viewModel.navigateBasketScreen()
        }
        productAdapter.setOpenProductDetailsListener {
            viewModel.openProductDetailsScreen(it)
        }

        categoryAdapter.setCategoryListener {
            viewModel.categoryItemClick(it)
        }

        viewModel.categoriesFlow.onEach {
            categoryAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        Basket.productsListLiveData.observe(viewLifecycleOwner) {
            productAdapter.submitList(it)
        }
    }

}