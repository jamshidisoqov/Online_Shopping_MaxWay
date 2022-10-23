package uz.gita.online_shopping.presentation.viewmodels

import kotlinx.coroutines.flow.SharedFlow

// Created by Jamshid Isoqov an 10/21/2022
interface LoginViewModel {
    val loadingFlow: SharedFlow<Boolean>

    val toastFlow: SharedFlow<String>

    val messageFlow: SharedFlow<String>

    val errorFlow: SharedFlow<String>

    fun login(phone: String, name: String)

}