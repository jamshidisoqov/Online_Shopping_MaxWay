package uz.gita.online_shopping.directions

import uz.gita.online_shopping.data.models.BranchData

// Created by Jamshid Isoqov an 10/25/2022
interface BranchesDirection {

    suspend fun navigateToBranchesDetails(branchData: BranchData)
}