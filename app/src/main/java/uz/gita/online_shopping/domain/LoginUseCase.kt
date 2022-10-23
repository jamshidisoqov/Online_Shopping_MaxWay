package uz.gita.online_shopping.domain

import uz.gita.online_shopping.data.models.ClientData
import uz.gita.online_shopping.data.models.dto.ClientDto
import uz.gita.online_shopping.data.models.other.ResultData

// Created by Jamshid Isoqov an 10/9/2022
interface LoginUseCase {

    suspend fun loginUser(clientDto: ClientDto): ResultData<ClientData>

    suspend fun logOutUser(clientData: ClientData): ResultData<ClientData>

}