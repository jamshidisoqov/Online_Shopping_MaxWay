package uz.gita.online_shopping.utils.extensions

import retrofit2.Response
import uz.gita.online_shopping.data.models.BaseResponse
import uz.gita.online_shopping.data.models.other.ResultData

// Created by Jamshid Isoqov an 10/11/2022


fun <T> Response<BaseResponse<T>>.func(): ResultData<T> {
    val data = this
    if (data.isSuccessful) {
        if (data.body() != null) {
            val body = data.body()!!
            return if (body.successful) {
                ResultData.Success(body.data)
            } else {
                ResultData.Message(body.message!!)
            }
        }
    }
    return ResultData.Error(Throwable(data.errorBody()!!.string()))
}