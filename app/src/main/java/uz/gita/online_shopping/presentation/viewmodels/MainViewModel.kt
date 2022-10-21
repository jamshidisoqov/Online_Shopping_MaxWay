package uz.gita.online_shopping.presentation.viewmodels

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.SharedFlow

// Created by Jamshid Isoqov an 10/12/2022
interface MainViewModel {

    val basketFlow:LiveData<Double>

    val loadingLiveData:LiveData<Boolean>

    fun navigateBasket()

    fun searchClicked()

}