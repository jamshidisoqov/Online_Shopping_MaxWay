package uz.gita.online_shopping.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.online_shopping.data.models.AdsData
import uz.gita.online_shopping.data.models.CategoryData
import uz.gita.online_shopping.data.models.ProductData
import uz.gita.online_shopping.data.models.other.ResultData

// Created by Jamshid Isoqov an 10/9/2022
interface MainUseCase {

    fun getAllAds(): Flow<ResultData<List<AdsData>>>

    fun getAllCategories(): Flow<ResultData<List<CategoryData>>>

    fun getAllProducts(): Flow<ResultData<List<ProductData>>>

    fun searchProducts(): Flow<ResultData<List<ProductData>>>

}