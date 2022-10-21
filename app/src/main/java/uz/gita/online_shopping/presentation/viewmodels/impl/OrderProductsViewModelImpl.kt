package uz.gita.online_shopping.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.online_shopping.data.models.Address
import uz.gita.online_shopping.data.models.dto.OrderDto
import uz.gita.online_shopping.domain.OrderUseCase
import uz.gita.online_shopping.presentation.viewmodels.OrderProductsViewModel
import uz.gita.online_shopping.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class OrderProductsViewModelImpl @Inject constructor(
    private val orderUseCase: OrderUseCase
) : OrderProductsViewModel, ViewModel() {

    override val loadingFlow = MutableSharedFlow<Boolean>()

    override val messageFlow = MutableSharedFlow<String>()

    override val successFlow = MutableSharedFlow<String>()

    override val errorFlow = MutableSharedFlow<String>()

    override val isDeliveryFlow = MutableStateFlow(false)

    override val deliveryAddress = MutableStateFlow("Delivery address")

    override fun openMap() {

    }

    override fun setDeliveryAddress(address: Address) {

    }

    override fun orderConfirmClick(orderDto: OrderDto) {
        viewModelScope.launch(Dispatchers.IO) {
            loadingFlow.emit(true)
            orderUseCase.orderProducts(orderDto).collectLatest {
                it.onSuccess { success ->
                    successFlow.emit("")
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
}