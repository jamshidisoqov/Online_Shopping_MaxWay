package uz.gita.online_shopping.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.online_shopping.domain.ProfileUseCase
import uz.gita.online_shopping.presentation.viewmodels.ProfileDetailsViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileDetailsViewModelImpl @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ProfileDetailsViewModel, ViewModel() {
    override val nameFlow = MutableStateFlow("")
    override val phoneFlow = MutableStateFlow("")
    override val birthdayFlow = MutableStateFlow("")
    override val openCalendarDialog = MutableSharedFlow<Unit>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            nameFlow.emit(profileUseCase.getName())
            phoneFlow.emit(profileUseCase.getPhone())
            birthdayFlow.emit(profileUseCase.getBirthday())
        }
    }

    override fun openCalendar() {
        viewModelScope.launch {
            openCalendarDialog.emit(Unit)
        }
    }

    override fun saveClicked(name: String, birthday: String) {
        viewModelScope.launch {
            profileUseCase.setName(name)
            profileUseCase.setBirthday(birthday)
        }
    }
}