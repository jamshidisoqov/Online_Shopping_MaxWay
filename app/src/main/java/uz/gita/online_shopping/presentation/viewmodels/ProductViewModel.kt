package uz.gita.online_shopping.presentation.viewmodels

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.online_shopping.data.models.ProductWithCount

// Created by Jamshid Isoqov an 10/13/2022
interface ProductViewModel {

    val productFlow: SharedFlow<ProductWithCount>

    fun setProduct(productWithCount: ProductWithCount)

    fun openBasketScreen()

    fun productBasketClick()

}