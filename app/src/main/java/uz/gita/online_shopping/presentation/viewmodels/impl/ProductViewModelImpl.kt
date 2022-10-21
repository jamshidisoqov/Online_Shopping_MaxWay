package uz.gita.online_shopping.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.gita.online_shopping.data.models.ProductWithCount
import uz.gita.online_shopping.directions.MainScreenDirection
import uz.gita.online_shopping.presentation.viewmodels.ProductViewModel
import uz.gita.online_shopping.utils.Basket
import javax.inject.Inject

@HiltViewModel
class ProductViewModelImpl @Inject constructor(
    private val direction: MainScreenDirection
) : ProductViewModel, ViewModel() {

    override val productFlow = MutableSharedFlow<ProductWithCount>()

    private lateinit var productWithCount: ProductWithCount

    override fun setProduct(productWithCount: ProductWithCount) {
        this.productWithCount = productWithCount
        viewModelScope.launch {
            productFlow.emit(productWithCount)
        }
    }

    override fun openBasketScreen() {
        viewModelScope.launch {
            direction.navigateBasketScreen()
        }
    }

    override fun productBasketClick() {
        Basket.addProduct(productWithCount)
    }
}