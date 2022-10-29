package uz.gita.online_shopping.data.models.dto

import uz.gita.online_shopping.data.models.Address

data class ClientDto(
    val fullName: String = "",
    val phoneNumber: String = "",
    val address: Address? = null
)
