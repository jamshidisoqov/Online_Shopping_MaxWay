package uz.gita.online_shopping.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.online_shopping.data.models.*
import uz.gita.online_shopping.data.models.dto.Category
import uz.gita.online_shopping.data.models.dto.ClientDto
import uz.gita.online_shopping.data.models.dto.OrderDto
import uz.gita.online_shopping.data.models.dto.ProductDto
import uz.gita.online_shopping.data.models.other.ResultData
import uz.gita.online_shopping.data.prefs.MySharedPref
import uz.gita.online_shopping.data.remote.MaxWayApi
import uz.gita.online_shopping.repository.MaxWayRepository
import uz.gita.online_shopping.utils.exceptions.NoInternetConnection
import uz.gita.online_shopping.utils.extensions.func
import uz.gita.online_shopping.utils.hasConnection
import javax.inject.Inject

class MaxWayRepositoryImpl @Inject constructor(
    private val maxWayApi: MaxWayApi,
    private val mySharedPref: MySharedPref
) : MaxWayRepository {


    override fun loginUser(clientDto: ClientDto): Flow<ResultData<ClientData>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.loginUser(clientDto).func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    override fun unRegisterUser(clientData: ClientData): Flow<ResultData<ClientData>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.unRegisterUser().func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    override fun getAllAds(): Flow<ResultData<List<AdsData>>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.getAllAds().func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    override fun getAllCategories(): Flow<ResultData<List<Category>>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.getAllCategories().func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    override fun getAllProducts(): Flow<ResultData<List<ProductDto>>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.getAllProducts().func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    override fun searchProducts(): Flow<ResultData<List<ProductDto>>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.searchProducts("data").func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    override fun getOrdersHistory(): Flow<ResultData<List<OrderData>>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.getOrdersHistory().func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    override fun getActiveOrders(): Flow<ResultData<List<OrderData>>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.getActiveOrders().func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    override fun orderProducts(orderDto: OrderDto): Flow<ResultData<OrderData>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.orderProducts(orderDto).func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    override fun getAllBranches(): Flow<ResultData<List<BranchData>>> =
        flow {
            if (hasConnection()) {
                emit(maxWayApi.getAllBranches().func())
            } else {
                throw NoInternetConnection()
            }
        }.catch { error ->
            emit(ResultData.Error(error))
        }.flowOn(Dispatchers.IO)

    override suspend fun getName(): String = mySharedPref.name

    override suspend fun setName(name: String) {
        mySharedPref.name = name
    }

    override suspend fun getPhone(): String = mySharedPref.phone

    override suspend fun setPhone(phone: String) {
        mySharedPref.phone = phone
    }

    override suspend fun getBirthday(): String = mySharedPref.birthday

    override suspend fun setBirthday(birthday: String) {
        mySharedPref.birthday = birthday
    }

    override suspend fun getToken(): String = mySharedPref.token

    override suspend fun setToken(token: String) {
        mySharedPref.token = token
    }


}




