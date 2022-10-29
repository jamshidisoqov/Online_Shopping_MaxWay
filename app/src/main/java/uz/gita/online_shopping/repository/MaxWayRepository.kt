package uz.gita.online_shopping.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.online_shopping.data.models.*
import uz.gita.online_shopping.data.models.dto.Category
import uz.gita.online_shopping.data.models.dto.ClientDto
import uz.gita.online_shopping.data.models.dto.OrderDto
import uz.gita.online_shopping.data.models.dto.ProductDto
import uz.gita.online_shopping.data.models.other.ResultData

// Created by Jamshid Isoqov an 10/9/2022
interface MaxWayRepository {

    fun loginUser(clientDto: ClientDto): Flow<ResultData<ClientData>>

    fun unRegisterUser(clientData: ClientData): Flow<ResultData<ClientData>>

    fun getAllAds(): Flow<ResultData<List<AdsData>>>

    fun getAllCategories(): Flow<ResultData<List<Category>>>

    fun getAllProducts(): Flow<ResultData<List<ProductDto>>>

    fun searchProducts(): Flow<ResultData<List<ProductDto>>>

    fun getOrdersHistory(): Flow<ResultData<List<OrderData>>>

    fun getActiveOrders(): Flow<ResultData<List<OrderData>>>

    fun orderProducts(orderDto: OrderDto): Flow<ResultData<OrderData>>

    fun getAllBranches(): Flow<ResultData<List<BranchData>>>

    suspend fun getName(): String

    suspend fun setName(name: String)

    suspend fun getPhone(): String

    suspend fun setPhone(phone: String)

    suspend fun getBirthday(): String

    suspend fun setBirthday(birthday: String)

    suspend fun getToken(): String

    suspend fun setToken(token: String)

}
