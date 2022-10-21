package uz.gita.online_shopping.presentation.viewmodels

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.online_shopping.data.models.ProductWithCount

// Created by Jamshid Isoqov an 10/18/2022
interface BasketViewModel {

    val loadingSharedFlow: SharedFlow<Boolean>

    val messageFlow: SharedFlow<String>

    val errorMessageFlow: SharedFlow<String>

    val openConfirmDialog:SharedFlow<Unit>

    fun removeProduct(productWithCount: ProductWithCount)

    fun deleteProduct(productWithCount: ProductWithCount)

    fun addProduct(productWithCount: ProductWithCount)

    fun confirmClicked()

    fun navigateOrderCheckoutScreen()
}