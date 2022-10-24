package uz.gita.online_shopping.directions.impl

import uz.gita.online_shopping.directions.BasketScreenDirection
import uz.gita.online_shopping.navigation.Navigator
import uz.gita.online_shopping.presentation.screens.basket.BasketScreenDirections
import javax.inject.Inject


class BasketScreenDirectionsImpl @Inject constructor(
    private val navigator: Navigator
) : BasketScreenDirection {
    override suspend fun navigateCheckoutScreen() {
        navigator.navigateTo(BasketScreenDirections.actionBasketScreenToOrderProductsScreen())
    }
}