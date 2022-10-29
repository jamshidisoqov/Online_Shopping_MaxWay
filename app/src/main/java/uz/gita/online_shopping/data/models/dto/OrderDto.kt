package uz.gita.online_shopping.data.models.dto

import uz.gita.online_shopping.data.models.Address
import uz.gita.online_shopping.data.models.enums.OrderType

data class OrderDto(
    val allOrderValue: HashSet<OrderItemDto>,
    val comment: String,
    val orderType: OrderType,
    val address: Address? = null,
    val userId: Long = 0
)
