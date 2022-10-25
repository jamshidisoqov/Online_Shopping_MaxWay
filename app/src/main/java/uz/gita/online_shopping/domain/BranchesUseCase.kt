package uz.gita.online_shopping.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.online_shopping.data.models.BranchData
import uz.gita.online_shopping.data.models.other.ResultData

// Created by Jamshid Isoqov an 10/25/2022
interface BranchesUseCase {

    fun getAllBranches(): Flow<ResultData<List<BranchData>>>

}