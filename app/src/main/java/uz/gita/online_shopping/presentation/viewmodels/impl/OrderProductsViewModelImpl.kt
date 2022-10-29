package uz.gita.online_shopping.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.gita.online_shopping.data.models.dto.OrderDto
import uz.gita.online_shopping.directions.OrderProductDirection
import uz.gita.online_shopping.domain.OrderUseCase
import uz.gita.online_shopping.presentation.viewmodels.OrderProductsViewModel
import uz.gita.online_shopping.utils.Basket
import uz.gita.online_shopping.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class OrderProductsViewModelImpl @Inject constructor(
    private val orderUseCase: OrderUseCase,
    private val direction: OrderProductDirection
) : OrderProductsViewModel, ViewModel() {

    override val loadingFlow = MutableSharedFlow<Boolean>()


    override val messageFlow = MutableSharedFlow<String>()

    override val successFlow = MutableSharedFlow<String>()

    override val errorFlow = MutableSharedFlow<String>()

    override val isDeliveryFlow = MutableStateFlow(false)

    override val deliveryAddress = MutableStateFlow("Delivery address")

    override fun orderConfirmClick(orderDto: OrderDto) {
        viewModelScope.launch(Dispatchers.IO) {
            loadingFlow.emit(true)
            orderUseCase.orderProducts(orderDto).collectLatest {
                it.onSuccess {
                    successFlow.emit("Successfully ordered ")
                    withContext(Dispatchers.Main) {
                        Basket.setList(Basket.productsList.map { it.productData })
                        navigateToMain()
                    }
                }
                it.onError { error ->
                    errorFlow.emit(error.getMessage())
                }
                it.onMessage { message ->
                    messageFlow.emit(message)
                }
                loadingFlow.emit(false)
            }
        }
    }

    override fun setDeliveryMethod(isDelivery: Boolean) {
        viewModelScope.launch { isDeliveryFlow.emit(isDelivery) }
    }

    override fun navigateToMain() {
        viewModelScope.launch {
            direction.navigateToMain()
        }
    }

    override fun navigateToMap() {
        viewModelScope.launch {
            direction.navigateToMap()
        }
    }
}