package uz.gita.online_shopping.directions.impl

import uz.gita.online_shopping.data.models.ProductWithCount
import uz.gita.online_shopping.directions.SearchScreenDirection
import uz.gita.online_shopping.navigation.Navigator
import uz.gita.online_shopping.presentation.screens.home.search.SearchProductsScreenDirections
import javax.inject.Inject

class SearchScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : SearchScreenDirection {
    override suspend fun navigateToProductDetails(productWithCount: ProductWithCount) {
        navigator.navigateTo(SearchProductsScreenDirections.actionSearchProductsScreenToProductDetailsScreen(productWithCount))
    }
}