package uz.gita.online_shopping.domain.impl

import uz.gita.online_shopping.domain.ProfileUseCase
import uz.gita.online_shopping.repository.MaxWayRepository
import javax.inject.Inject

class ProfileUseCaseImpl @Inject constructor(
    private val repository: MaxWayRepository
) : ProfileUseCase {

    override suspend fun getName(): String = repository.getName()

    override suspend fun setName(name: String) = repository.setName(name)

    override suspend fun getPhone(): String = repository.getPhone()

    override suspend fun setPhone(phone: String) = repository.setPhone(phone)

    override suspend fun getBirthday(): String = repository.getBirthday()

    override suspend fun setBirthday(birthday: String) = repository.setBirthday(birthday)

}