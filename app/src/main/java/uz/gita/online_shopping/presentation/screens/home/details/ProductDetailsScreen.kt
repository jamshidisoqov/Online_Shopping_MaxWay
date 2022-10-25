package uz.gita.online_shopping.presentation.screens.home.details

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.online_shopping.R
import uz.gita.online_shopping.databinding.ScreenProductDetailsBinding
import uz.gita.online_shopping.presentation.viewmodels.ProductViewModel
import uz.gita.online_shopping.presentation.viewmodels.impl.ProductViewModelImpl

// Created by Jamshid Isoqov an 10/10/2022
@AndroidEntryPoint
class ProductDetailsScreen : Fragment(R.layout.screen_product_details) {

    private val viewBinding: ScreenProductDetailsBinding by viewBinding()

    private val viewModel: ProductViewModel by viewModels<ProductViewModelImpl>()

    private val navArgs: ProductDetailsScreenArgs by navArgs()

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.productFlow.onEach { productWithCount ->

            val productData = productWithCount.productData

            viewBinding.apply {
                tvProductName.text = productData.name
                tvDescription.text = productData.desc
                tvProductCategoryName.text = productData.name
                Glide.with(requireContext())
                    .load(productData.imageUrl)
                    .placeholder(R.drawable.place)
                    .into(imageFood)
                btnToBasket.apply {
                    if (productWithCount.count == 0) {
                        text = resources.getString(R.string.to_basket)
                        setTextColor(Color.WHITE)
                        setBackgroundResource(R.drawable.bg_to_basket_btn)
                    } else {
                        setTextColor(Color.BLACK)
                        setBackgroundResource(R.drawable.bg_in_basket_btn)
                        text = resources.getString(R.string.in_basket)
                    }
                    setOnClickListener {
                        if (productWithCount.count > 0) {
                            viewModel.openBasketScreen()
                        } else {
                            viewModel.productBasketClick()
                        }
                    }
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.setProduct(navArgs.productCount)
    }
}