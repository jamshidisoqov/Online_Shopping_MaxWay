package uz.gita.online_shopping.domain.impl

import uz.gita.online_shopping.data.models.ClientData
import uz.gita.online_shopping.data.models.dto.ClientDto
import uz.gita.online_shopping.data.models.other.ResultData
import uz.gita.online_shopping.domain.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor() : LoginUseCase {
    override suspend fun loginUser(clientDto: ClientDto): ResultData<ClientData> {
        return ResultData.Success(
            ClientData(
                "token",
                clientDto.phone,
                clientDto.name,
                null
            )
        )
    }

    override suspend fun logOutUser(clientData: ClientData): ResultData<ClientData> {
        TODO("Not yet implemented")
    }
}