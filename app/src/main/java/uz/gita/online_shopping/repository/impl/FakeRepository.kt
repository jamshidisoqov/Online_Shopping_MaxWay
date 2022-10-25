package uz.gita.online_shopping.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.online_shopping.data.models.*
import uz.gita.online_shopping.data.models.dto.ClientDto
import uz.gita.online_shopping.data.models.dto.OrderDto
import uz.gita.online_shopping.data.models.enums.OrderStatus
import uz.gita.online_shopping.data.models.other.ResultData
import uz.gita.online_shopping.data.prefs.MySharedPref
import uz.gita.online_shopping.repository.MaxWayRepository
import uz.gita.online_shopping.utils.extensions.getCurrentDate
import javax.inject.Inject

// Created by Jamshid Isoqov an 10/24/2022

class FakeRepository @Inject constructor(
    private val mySharedPref: MySharedPref
) : MaxWayRepository {
    private val activeOrders = ArrayList<OrderData>()

    private val inActiveOrders = ArrayList<OrderData>()


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

    override fun getOrdersHistory(): Flow<ResultData<List<OrderData>>> = flow {
        emit(ResultData.Success(inActiveOrders))
    }

    override fun getActiveOrders(): Flow<ResultData<List<OrderData>>> = flow {
        emit(ResultData.Success(activeOrders))
    }

    override fun orderProducts(orderDto: OrderDto): Flow<ResultData<OrderData>> = flow {
        val orderData = OrderData(
            1, orderDto.productOrder, 12.0, orderDto.orderType, OrderStatus.ORDERED,
            getCurrentDate(), orderDto.address, orderDto.comment
        )
        activeOrders.add(orderData)

        emit(ResultData.Success(orderData))
    }

    override fun getAllBranches(): Flow<ResultData<List<BranchData>>> = flow {
        emit(
            ResultData.Success(
                listOf(
                    BranchData(
                        1,
                        "улица Катартал, 60/5",
                        address = Address(41.293915, 69.212829),
                        "https://maxway.uz/images/Rectangle/max-way.png",
                        "Max Way Parus",
                        "+998 71 200 54 00",
                        "10:00-03:00"
                    ),
                    BranchData(
                        2,
                        "улица Бабура, 174/6",
                        address = Address(41.2713019, 69.2577589),
                        "https://maxway.uz/images/Rectangle/max-way.png",
                        "MAX WAY MAGIC CITY",
                        "+998 71 200 54 00",
                        "10:00-22:00"
                    )

                )
            )
        )
    }

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