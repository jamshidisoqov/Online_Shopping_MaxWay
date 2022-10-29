package uz.gita.online_shopping.data.models.dto

import uz.gita.online_shopping.data.models.CategoryData

data class Category(
    val createdDate: String,
    val id: Long,
    val information: String,
    val name: String
){
    fun toCategoryData() = CategoryData(id, name, information, createdDate)
}