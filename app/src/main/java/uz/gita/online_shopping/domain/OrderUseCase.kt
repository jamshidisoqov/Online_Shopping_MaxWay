package uz.gita.online_shopping.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.online_shopping.data.models.OrderData
import uz.gita.online_shopping.data.models.dto.OrderDto
import uz.gita.online_shopping.data.models.other.ResultData

// Created by Jamshid Isoqov an 10/9/2022
interface OrderUseCase {

    fun getOrdersHistory(): Flow<ResultData<List<OrderData>>>

    fun getActiveOrders(): Flow<ResultData<List<OrderData>>>

    fun orderProducts(orderDto: OrderDto): Flow<ResultData<OrderData>>

}