package uz.gita.online_shopping.data.remote

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import uz.gita.online_shopping.data.models.RegistrationData

// Created by Jamshid Isoqov an 10/24/2022
interface RegistrationApi {


    @POST("sms/2/text/advanced")
    suspend fun verifySms(
        @Header("Authorization") value: String = "IBSSO 88dce91862b411dbb47007a5743382e5-990c5d6c-f127-46d3-8e3c-14b8aee8ea3d",
        @Body registrationData: RegistrationData
    )

}