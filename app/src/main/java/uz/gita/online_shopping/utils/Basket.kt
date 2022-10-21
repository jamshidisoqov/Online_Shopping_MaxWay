package uz.gita.online_shopping.utils

import androidx.lifecycle.MutableLiveData
import uz.gita.online_shopping.data.models.ProductData
import uz.gita.online_shopping.data.models.ProductWithCount


// Created by Jamshid Isoqov an 10/10/2022
object Basket {
    var productsListLiveData = MutableLiveData<List<ProductWithCount>>()

    val onTheSelfList = listOf("Ordered", " Confirmed", "Delivered")
    val deliveredList = listOf("Ordered", " Confirmed", "On the way", "Delivered")

    private var productsList = ArrayList<ProductWithCount>()

    fun setList(list: List<ProductData>) {

        productsList.addAll(list.map {
            ProductWithCount(it, 0)
        })
        productsListLiveData.value = productsList

    }

    fun addProduct(productWithCount: ProductWithCount) {
        val index = findIndex(productWithCount)
        if (index != -1) {
            val data = productsList[index]
            productsList[index] = data.copy(count = data.count + 1)
            productsListLiveData.value = productsList
        }
    }

    private fun findIndex(productWithCount: ProductWithCount): Int {
        val data = productsList
        for (i in data.indices) {
            if (data[i].productData.id == productWithCount.productData.id) {
                return i
            }
        }
        return -1
    }

    fun removeProduct(productWithCount: ProductWithCount) {
        val index = findIndex(productWithCount)
        if (index != -1) {
            val data = productsList[index]
            productsList[index] = data.copy(count = data.count - 1)
            productsListLiveData.value = productsList
        }
    }

}