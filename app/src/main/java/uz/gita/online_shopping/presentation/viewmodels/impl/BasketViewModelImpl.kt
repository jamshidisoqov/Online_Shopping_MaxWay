package uz.gita.online_shopping.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.gita.online_shopping.data.models.ProductWithCount
import uz.gita.online_shopping.directions.BasketScreenDirections
import uz.gita.online_shopping.presentation.viewmodels.BasketViewModel
import uz.gita.online_shopping.utils.Basket
import javax.inject.Inject

@HiltViewModel
class BasketViewModelImpl @Inject constructor(
    private val basketScreenDirections: BasketScreenDirections
) : BasketViewModel, ViewModel() {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageFlow = MutableSharedFlow<String>()

    override val errorMessageFlow = MutableSharedFlow<String>()

    override val openConfirmDialog = MutableSharedFlow<Unit>()

    override fun removeProduct(productWithCount: ProductWithCount) {
        Basket.removeProduct(productWithCount)
    }

    override fun deleteProduct(productWithCount: ProductWithCount) {
        Basket.removeProduct(productWithCount.copy(count = 1))
    }

    override fun addProduct(productWithCount: ProductWithCount) {
        Basket.addProduct(productWithCount)
    }

    override fun confirmClicked() {
        viewModelScope.launch {
            openConfirmDialog.emit(Unit)
        }
    }

    override fun navigateOrderCheckoutScreen() {
        viewModelScope.launch {
            basketScreenDirections.navigateCheckoutScreen()
        }
    }

}