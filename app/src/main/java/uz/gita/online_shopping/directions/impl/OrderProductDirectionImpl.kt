package uz.gita.online_shopping.directions.impl

import uz.gita.online_shopping.directions.OrderProductDirection
import uz.gita.online_shopping.navigation.Navigator
import uz.gita.online_shopping.presentation.screens.orders.checkout.OrderProductsScreenDirections
import javax.inject.Inject

class OrderProductDirectionImpl @Inject constructor(private val navigator: Navigator) :
    OrderProductDirection {
    override suspend fun navigateToMain() {
        navigator.navigateTo(OrderProductsScreenDirections.actionOrderProductsScreenToMainScreen())
    }

    override suspend fun navigateToMap() {
        navigator.navigateTo(OrderProductsScreenDirections.actionOrderProductsScreenToOrderMapFragment())
    }
}