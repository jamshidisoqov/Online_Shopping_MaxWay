package uz.gita.online_shopping.directions.impl

import uz.gita.online_shopping.directions.BasketScreenDirections
import uz.gita.online_shopping.navigation.Navigator
import javax.inject.Inject


class BasketScreenDirectionsImpl @Inject constructor(
    private val navigator: Navigator
) : BasketScreenDirections {
    override suspend fun navigateCheckoutScreen() {
        // TODO navigator.navigateTo()
    }
}