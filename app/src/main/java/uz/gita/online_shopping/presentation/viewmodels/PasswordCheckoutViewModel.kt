package uz.gita.online_shopping.presentation.viewmodels

import kotlinx.coroutines.flow.SharedFlow

// Created by Jamshid Isoqov an 10/23/2022
interface PasswordCheckoutViewModel {

    val loadingFlow: SharedFlow<Boolean>

    val messageFlow: SharedFlow<String>

    val toastFlow: SharedFlow<String>

    val errorFlow: SharedFlow<String>

    fun check(password: String, verifySms: String)

}