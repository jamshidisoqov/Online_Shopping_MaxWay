package uz.gita.online_shopping.presentation.screens.home.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.widget.textChanges
import uz.gita.online_shopping.R
import uz.gita.online_shopping.databinding.ScreenSearchProductsBinding
import uz.gita.online_shopping.presentation.viewmodels.SearchViewModel
import uz.gita.online_shopping.presentation.viewmodels.impl.SearchViewModelImpl

// Created by Jamshid Isoqov an 10/10/2022
@OptIn(FlowPreview::class)
@AndroidEntryPoint
class SearchProductsScreen : Fragment(R.layout.screen_search_products) {

    private val viewModel: SearchViewModel by viewModels<SearchViewModelImpl>()

    private val viewBinding: ScreenSearchProductsBinding by viewBinding()

    private val adapter: SearchAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SearchAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.listSearchProducts.adapter = adapter

        viewModel.productSharedFlow.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewBinding.inputProduct.textChanges().debounce(200L).onEach {
            viewModel.search(it.toString())
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            viewModel.navigateToProductDetails(it)
        }

        viewBinding.tvCancel.setOnClickListener {
            findNavController().navigateUp()
        }


    }


}