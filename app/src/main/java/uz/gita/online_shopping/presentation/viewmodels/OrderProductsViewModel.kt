package uz.gita.online_shopping.presentation.viewmodels

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.online_shopping.data.models.dto.OrderDto

// Created by Jamshid Isoqov an 10/18/2022
interface OrderProductsViewModel {

    val loadingFlow: SharedFlow<Boolean>

    val messageFlow: SharedFlow<String>

    val successFlow: SharedFlow<String>

    val errorFlow: SharedFlow<String>

    val isDeliveryFlow: StateFlow<Boolean>

    val deliveryAddress: StateFlow<String>

    fun orderConfirmClick(orderDto: OrderDto)

    fun setDeliveryMethod(isDelivery: Boolean)

    fun navigateToMain()

    fun navigateToMap()
}