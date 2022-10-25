package uz.gita.online_shopping.presentation.viewmodels

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.online_shopping.data.models.ProductWithCount

// Created by Jamshid Isoqov an 10/25/2022
interface SearchViewModel {

    val productSharedFlow: SharedFlow<List<ProductWithCount>>

    fun navigateToProductDetails(productWithCount: ProductWithCount)

    fun search(query: String)

}