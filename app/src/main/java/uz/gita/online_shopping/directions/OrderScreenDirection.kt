package uz.gita.online_shopping.directions

import uz.gita.online_shopping.data.models.OrderData

// Created by Jamshid Isoqov an 10/13/2022
interface OrderScreenDirection {

   suspend fun navigateToActiveOrderDetails(orderData: OrderData)

   suspend fun navigateToHistoryOrderDetails(orderData: OrderData)

}