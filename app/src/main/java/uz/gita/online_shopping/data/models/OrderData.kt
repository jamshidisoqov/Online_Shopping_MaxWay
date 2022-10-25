package uz.gita.online_shopping.data.models

import uz.gita.online_shopping.data.models.enums.OrderStatus
import uz.gita.online_shopping.data.models.enums.OrderType

// Created by Jamshid Isoqov an 10/8/2022
data class OrderData(
    val id: Long,
    val productOrder: List<ProductOrder>,
    val allPrice: Double,
    val orderType: OrderType,
    val status: OrderStatus,
    val date: String,
    val address: Address? = null,
    val comment: String? = null
)
