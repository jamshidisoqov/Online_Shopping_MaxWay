package uz.gita.online_shopping.presentation.viewmodels

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.online_shopping.data.models.Address

// Created by Jamshid Isoqov an 10/25/2022
interface BranchDetailsViewModel {

    val loadingFlow: SharedFlow<Boolean>

    val messageFlow: SharedFlow<String>

    val errorFlow: SharedFlow<String>

    val address: SharedFlow<String>

    fun getBranchLocation(address: Address)
}