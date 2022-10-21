package uz.gita.online_shopping.presentation.viewmodels

import kotlinx.coroutines.flow.StateFlow

// Created by Jamshid Isoqov an 10/21/2022
interface ProfileViewModel {
    val nameFlow: StateFlow<String>

    val phoneFlow: StateFlow<String>

    fun editProfile()

    fun openBranches()

    fun openSettings()

    fun openServices()
}