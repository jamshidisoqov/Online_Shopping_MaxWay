package uz.gita.online_shopping.domain.impl

import uz.gita.online_shopping.domain.BranchesUseCase
import uz.gita.online_shopping.repository.MaxWayRepository
import javax.inject.Inject

class BranchesUseCaseImpl @Inject constructor(private val repository: MaxWayRepository) :
    BranchesUseCase {
    override fun getAllBranches() = repository.getAllBranches()
}