package uz.gita.online_shopping.data.remote

import retrofit2.Response
import retrofit2.http.*
import uz.gita.online_shopping.data.models.*
import uz.gita.online_shopping.data.models.dto.ClientDto
import uz.gita.online_shopping.data.models.dto.OrderDto

// Created by Jamshid Isoqov an 10/9/2022
interface MaxWayApi {

    @POST("register")
    suspend fun loginUser(@Body clientDto: ClientDto): Response<BaseResponse<ClientData>>

    @PUT("")
    suspend fun updateUser(@Body clientDto: ClientDto): Response<BaseResponse<ClientData>>

    @POST("unregister")
    suspend fun unRegisterUser(): Response<BaseResponse<ClientData>>

    @GET("path")
    fun getAllAds(): Response<BaseResponse<List<AdsData>>>

    @GET("path")
    suspend fun getAllCategories(): Response<BaseResponse<List<CategoryData>>>

    @GET("path")
    suspend fun getAllProducts(): Response<BaseResponse<List<ProductData>>>

    @GET("path")
    suspend fun searchProducts(@Query("query") query: String): Response<BaseResponse<List<ProductData>>>

    @GET("path")
    suspend fun getOrdersHistory(): Response<BaseResponse<List<OrderData>>>

    @GET("path")
    suspend fun getActiveOrders(): Response<BaseResponse<List<OrderData>>>

    @POST("path")
    suspend fun orderProducts(@Body orderDto: OrderDto): Response<BaseResponse<OrderData>>

    @GET("path")
    suspend fun getAllBranches(): Response<BaseResponse<List<BranchData>>>
}