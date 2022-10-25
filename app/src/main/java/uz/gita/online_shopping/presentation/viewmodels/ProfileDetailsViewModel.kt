package uz.gita.online_shopping.presentation.viewmodels

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

// Created by Jamshid Isoqov an 10/25/2022
interface ProfileDetailsViewModel {

    val nameFlow: StateFlow<String>

    val phoneFlow: StateFlow<String>

    val birthdayFlow:StateFlow<String>

    val openCalendarDialog: SharedFlow<Unit>

    fun openCalendar()

    fun saveClicked(name: String, birthday: String)


}