package uz.gita.online_shopping.domain

// Created by Jamshid Isoqov an 10/21/2022
interface ProfileUseCase {

    suspend fun getName(): String

    suspend fun setName(name: String)

    suspend fun getPhone(): String

    suspend fun setPhone(phone: String)

    suspend fun getBirthday(): String

    suspend fun setBirthday(birthday: String)

}