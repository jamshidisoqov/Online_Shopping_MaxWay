package uz.gita.online_shopping.presentation.viewmodels

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.online_shopping.data.models.OrderData

// Created by Jamshid Isoqov an 10/13/2022
interface ActiveOrderViewModel {

    val allActiveOrders: SharedFlow<List<OrderData>>

    val loadingSharedFlow: SharedFlow<Boolean>

    val messageFlow: SharedFlow<String>

    val errorMessageFlow: SharedFlow<String>

    fun getAllOrders()

    fun navigateToOrderDetails()

}