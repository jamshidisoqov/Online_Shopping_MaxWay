package uz.gita.online_shopping.data.models.dto

import uz.gita.online_shopping.data.models.ProductData

data class ProductDto(
    val name: String,
    val category: Category? = null,
    val imageUrl: String,
    val price: Double,
    val description: String,
    val id: Long,
    val createdDate:String
){
    fun toProductData() = ProductData(id,name,imageUrl,price,description,category!!.id)
}