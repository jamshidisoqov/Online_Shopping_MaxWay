package uz.gita.online_shopping.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.gita.online_shopping.data.models.ProductWithCount
import uz.gita.online_shopping.directions.SearchScreenDirection
import uz.gita.online_shopping.presentation.viewmodels.SearchViewModel
import uz.gita.online_shopping.utils.Basket
import javax.inject.Inject

@HiltViewModel
class SearchViewModelImpl @Inject constructor(
    private val direction: SearchScreenDirection
) : SearchViewModel, ViewModel() {

    override val productSharedFlow = MutableSharedFlow<List<ProductWithCount>>()


    override fun navigateToProductDetails(productWithCount: ProductWithCount) {
        viewModelScope.launch {
            direction.navigateToProductDetails(productWithCount)
        }
    }

    override fun search(query: String) {
        viewModelScope.launch {
            val list = Basket.productsList.filter {
                it.productData.name.contains(query)
            }
            productSharedFlow.emit(list)
        }
    }
}