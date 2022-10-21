package uz.gita.online_shopping.data.models.dto

import uz.gita.online_shopping.data.models.Address
import uz.gita.online_shopping.data.models.ProductOrder
import uz.gita.online_shopping.data.models.enums.OrderStatus
import uz.gita.online_shopping.data.models.enums.OrderType

// Created by Jamshid Isoqov an 10/9/2022
data class OrderDto(
    val productOrder: List<ProductOrder>,
    val allOrderValue: Double,
    val orderType: OrderType,
    val address: Address? = null,
    val comment: String = "all is well"
)
