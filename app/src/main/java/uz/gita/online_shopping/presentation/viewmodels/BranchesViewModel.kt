package uz.gita.online_shopping.presentation.viewmodels

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.SharedFlow
import uz.gita.online_shopping.data.models.BranchData

// Created by Jamshid Isoqov an 10/25/2022
interface BranchesViewModel {

    val loadingSharedFlow: LiveData<Boolean>

    val messageFlow: SharedFlow<String>

    val errorMessageFlow: SharedFlow<String>

    val branchesFlow: SharedFlow<List<BranchData>>

    fun navigateToBranchDetails(branchData: BranchData)

}