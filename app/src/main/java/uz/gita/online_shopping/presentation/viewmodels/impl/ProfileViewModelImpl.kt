package uz.gita.online_shopping.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.online_shopping.directions.ProfileScreenDirection
import uz.gita.online_shopping.domain.ProfileUseCase
import uz.gita.online_shopping.presentation.viewmodels.ProfileViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModelImpl @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val direction: ProfileScreenDirection
) : ProfileViewModel, ViewModel() {
    override val nameFlow = MutableStateFlow("")

    override val phoneFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
            nameFlow.emit(profileUseCase.getName())
            phoneFlow.emit(profileUseCase.getPhone())
        }
    }

    override fun editProfile() {

    }

    override fun openBranches() {

    }

    override fun openSettings() {

    }

    override fun openServices() {

    }
}