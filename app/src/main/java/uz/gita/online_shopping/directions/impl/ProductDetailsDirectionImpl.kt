package uz.gita.online_shopping.directions.impl

import uz.gita.online_shopping.directions.ProductDetailsDirection
import uz.gita.online_shopping.navigation.Navigator
import uz.gita.online_shopping.presentation.screens.home.details.ProductDetailsScreenDirections
import javax.inject.Inject

class ProductDetailsDirectionImpl @Inject constructor(private val navigator: Navigator) :
    ProductDetailsDirection {
    override suspend fun navigateToBasket() {
        navigator.navigateTo(ProductDetailsScreenDirections.actionProductDetailsScreenToBasketScreen())
    }
}