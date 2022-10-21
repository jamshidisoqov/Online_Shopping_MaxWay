package uz.gita.online_shopping.domain.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.online_shopping.data.models.AdsData
import uz.gita.online_shopping.data.models.CategoryData
import uz.gita.online_shopping.data.models.ProductData
import uz.gita.online_shopping.data.models.other.ResultData
import uz.gita.online_shopping.domain.MainUseCase
import uz.gita.online_shopping.repository.MaxWayRepository
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor(
    private val maxWayRepository: MaxWayRepository
) : MainUseCase {
    override fun getAllAds(): Flow<ResultData<List<AdsData>>> =
        maxWayRepository.getAllAds()

    override fun getAllCategories(): Flow<ResultData<List<CategoryData>>> =
        maxWayRepository.getAllCategories()

    override fun getAllProducts(): Flow<ResultData<List<ProductData>>> =
        maxWayRepository.getAllProducts()

    override fun searchProducts(): Flow<ResultData<List<ProductData>>> =
        maxWayRepository.searchProducts()

}