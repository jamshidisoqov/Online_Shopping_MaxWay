package uz.gita.online_shopping.presentation.viewmodels

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.SharedFlow
import uz.gita.online_shopping.data.models.CategoryData
import uz.gita.online_shopping.data.models.ProductWithCount

// Created by Jamshid Isoqov an 10/11/2022
interface HomeViewModel {

    val categoriesFlow: SharedFlow<List<CategoryData>>

    val productFlow:LiveData<List<ProductWithCount>>

    val loadingSharedFlow: LiveData<Boolean>

    val messageFlow: SharedFlow<String>

    val errorMessageFlow: SharedFlow<String>

    fun getAllCategories()

    fun categoryItemClick(categoryData: CategoryData,selectedPos:Int)

    fun getAllProducts()

    fun addBasket(productWithCount: ProductWithCount)

    fun navigateBasketScreen()

    fun openProductDetailsScreen(productWithCount: ProductWithCount)

    fun searchClicked()

}