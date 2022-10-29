package uz.gita.online_shopping.data.models

// Created by Jamshid Isoqov an 10/8/2022
data class ClientData(
    val phone: String,
    val name: String,
    val address: Address? = null
)
