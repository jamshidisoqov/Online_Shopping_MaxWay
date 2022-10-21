package uz.gita.online_shopping.directions.impl

import uz.gita.online_shopping.data.models.ProductWithCount
import uz.gita.online_shopping.directions.MainScreenDirection
import uz.gita.online_shopping.navigation.Navigator
import uz.gita.online_shopping.presentation.screens.MainScreenDirections
import javax.inject.Inject

class MainScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : MainScreenDirection {
    override suspend fun navigateSearchScreen() =
        navigator.navigateTo(MainScreenDirections.actionMainScreenToSearchProductsScreen())

    override suspend fun navigateBasketScreen() =
        navigator.navigateTo(MainScreenDirections.actionMainScreenToOrderProductsScreen())

    override suspend fun navigateProductsDetailsScreen(productWithCount: ProductWithCount) =
        navigator.navigateTo(
            MainScreenDirections.actionMainScreenToProductDetailsScreen(
                productWithCount
            )
        )
}