package uz.gita.online_shopping.domain.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.online_shopping.data.models.OrderData
import uz.gita.online_shopping.data.models.dto.OrderDto
import uz.gita.online_shopping.data.models.other.ResultData
import uz.gita.online_shopping.domain.OrderUseCase
import uz.gita.online_shopping.repository.MaxWayRepository
import javax.inject.Inject

class OrderUseCaseImpl @Inject constructor(
    private val maxWayRepository: MaxWayRepository
) : OrderUseCase {
    override fun getOrdersHistory(): Flow<ResultData<List<OrderData>>> =
        maxWayRepository.getOrdersHistory()

    override fun getActiveOrders(): Flow<ResultData<List<OrderData>>> =
        maxWayRepository.getActiveOrders()

    override fun orderProducts(orderDto: OrderDto): Flow<ResultData<OrderData>> =
        maxWayRepository.orderProducts(orderDto)
}