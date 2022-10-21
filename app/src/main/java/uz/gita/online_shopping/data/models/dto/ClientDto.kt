package uz.gita.online_shopping.data.models.dto

// Created by Jamshid Isoqov an 10/9/2022
data class ClientDto(
    val phone: String,
    val name: String,
    val birthday: String? = "none"
)
