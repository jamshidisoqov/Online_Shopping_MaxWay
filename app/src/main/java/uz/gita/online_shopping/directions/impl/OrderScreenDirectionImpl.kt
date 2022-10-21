package uz.gita.online_shopping.directions.impl

import uz.gita.online_shopping.data.models.OrderData
import uz.gita.online_shopping.directions.OrderScreenDirection
import uz.gita.online_shopping.navigation.Navigator
import javax.inject.Inject

class OrderScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : OrderScreenDirection {

    override suspend fun navigateToActiveOrderDetails(orderData: OrderData) {

    }

    override suspend fun navigateToHistoryOrderDetails(orderData: OrderData) {
      //Todo  navigator.navigateTo()
    }
}