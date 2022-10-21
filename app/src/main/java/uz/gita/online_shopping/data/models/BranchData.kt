package uz.gita.online_shopping.data.models

// Created by Jamshid Isoqov an 10/9/2022
data class BranchData(
    val id: Long,
    val way: String,
    val address: Address,
    val imageUrl: String,
    val name: String,
    val phone: String,
    val schedule: String
)
