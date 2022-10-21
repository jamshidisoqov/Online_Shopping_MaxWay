package uz.gita.online_shopping.directions

import uz.gita.online_shopping.data.models.ProductWithCount

// Created by Jamshid Isoqov an 10/12/2022
interface MainScreenDirection {

    suspend fun navigateSearchScreen()

    suspend fun navigateBasketScreen()

    suspend fun navigateProductsDetailsScreen(productWithCount: ProductWithCount)

}