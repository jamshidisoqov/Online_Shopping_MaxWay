package uz.gita.online_shopping.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.online_shopping.data.models.*
import uz.gita.online_shopping.data.models.dto.ClientDto
import uz.gita.online_shopping.data.models.dto.OrderDto
import uz.gita.online_shopping.data.models.other.ResultData
import uz.gita.online_shopping.repository.MaxWayRepository
import javax.inject.Inject

// Created by Jamshid Isoqov an 10/24/2022
class FakeRepository @Inject constructor() : MaxWayRepository {
    override fun loginUser(clientDto: ClientDto): Flow<ResultData<ClientData>> {
        TODO("Not yet implemented")
    }

    override fun unRegisterUser(clientData: ClientData): Flow<ResultData<ClientData>> {
        TODO("Not yet implemented")
    }

    override fun getAllAds(): Flow<ResultData<List<AdsData>>> {
        TODO("Not yet implemented")
    }

    override fun getAllCategories(): Flow<ResultData<List<CategoryData>>> = flow {
        emit(ResultData.Success(listOf(CategoryData(1, "Fast Food"), CategoryData(2, "Burgers"))))
    }

    override fun getAllProducts(): Flow<ResultData<List<ProductData>>> = flow {
        emit(
            ResultData.Success(
                listOf(
                    ProductData(
                        1,
                        "Max Burgers",
                        "https://cdn.delever.uz/delever/608c5ecf-6869-4641-b77c-6ca9d9de7c7a",
                        12_000.0,
                        "Информация: турецкий хлеб",
                        1
                    ),
                    ProductData(
                        2,
                        "Донар Кебаб куриный",
                        "https://cdn.delever.uz/delever/8e6a6416-a004-4d47-a875-e746f4342f90",
                        23_000.0,
                        "Информация: турецкий хлеб",
                        1
                    )
                )
            )
        )
    }

    override fun searchProducts(): Flow<ResultData<List<ProductData>>> {
        TODO("Not yet implemented")
    }

    override fun getOrdersHistory(): Flow<ResultData<List<OrderData>>> {
        TODO("Not yet implemented")
    }

    override fun getActiveOrders(): Flow<ResultData<List<OrderData>>> {
        TODO("Not yet implemented")
    }

    override fun orderProducts(orderDto: OrderDto): Flow<ResultData<OrderData>> {
        TODO("Not yet implemented")
    }

    override fun getAllBranches(): Flow<ResultData<List<BranchData>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getName(): String {
        TODO("Not yet implemented")
    }

    override suspend fun setName(name: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getPhone(): String {
        TODO("Not yet implemented")
    }

    override suspend fun setPhone(phone: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getBirthday(): String {
        TODO("Not yet implemented")
    }

    override suspend fun setBirthday(birthday: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getToken(): String = "token"

    override suspend fun setToken(token: String) {
        TODO("Not yet implemented")
    }
}