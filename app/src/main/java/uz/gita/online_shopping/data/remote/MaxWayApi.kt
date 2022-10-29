package uz.gita.online_shopping.data.remote

import retrofit2.Response
import retrofit2.http.*
import uz.gita.online_shopping.data.models.*
import uz.gita.online_shopping.data.models.dto.Category
import uz.gita.online_shopping.data.models.dto.ClientDto
import uz.gita.online_shopping.data.models.dto.OrderDto
import uz.gita.online_shopping.data.models.dto.ProductDto

// Created by Jamshid Isoqov an 10/9/2022
interface MaxWayApi {

    @POST("register")
    suspend fun loginUser(@Body clientDto: ClientDto): Response<BaseResponse<ClientData>>

    @POST("unregister")
    suspend fun unRegisterUser(): Response<BaseResponse<ClientData>>

    @GET("path")
    fun getAllAds(): Response<BaseResponse<List<AdsData>>>

    @GET("api/v1/category/all")
    suspend fun getAllCategories(): Response<BaseResponse<List<Category>>>

    @GET("api/v1/product/all")
    suspend fun getAllProducts(): Response<BaseResponse<List<ProductDto>>>

    @GET("path")
    suspend fun searchProducts(@Query("query") query: String): Response<BaseResponse<List<ProductDto>>>

    @GET("path")
    suspend fun getOrdersHistory(): Response<BaseResponse<List<OrderData>>>

    @GET("path")
    suspend fun getActiveOrders(): Response<BaseResponse<List<OrderData>>>

    @POST("path")
    suspend fun orderProducts(@Body orderDto: OrderDto): Response<BaseResponse<OrderData>>

    @GET("path")
    suspend fun getAllBranches(): Response<BaseResponse<List<BranchData>>>
}