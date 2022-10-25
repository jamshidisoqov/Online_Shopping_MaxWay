package uz.gita.online_shopping.directions

import uz.gita.online_shopping.data.models.ProductWithCount

// Created by Jamshid Isoqov an 10/25/2022
interface SearchScreenDirection {

    suspend fun navigateToProductDetails(productWithCount: ProductWithCount)

}