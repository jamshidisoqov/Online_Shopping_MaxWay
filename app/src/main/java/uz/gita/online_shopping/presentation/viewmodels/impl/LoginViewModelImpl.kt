package uz.gita.online_shopping.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.gita.online_shopping.data.models.dto.ClientDto
import uz.gita.online_shopping.directions.LoginScreenDirection
import uz.gita.online_shopping.domain.LoginUseCase
import uz.gita.online_shopping.presentation.viewmodels.LoginViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val direction: LoginScreenDirection
) : LoginViewModel, ViewModel() {

    override val loadingFlow = MutableSharedFlow<Boolean>()

    override val toastFlow = MutableSharedFlow<String>()

    override val messageFlow = MutableSharedFlow<String>()

    override val errorFlow = MutableSharedFlow<String>()

    override fun login(phone: String, name: String) {
        viewModelScope.launch {
            if (phone.length < 17) {
                toastFlow.emit("Phone number input incorrect")
               cancel()
            }
            if (name.isEmpty()) {
                toastFlow.emit("Name is required")
                cancel()
            }
            loginUseCase.loginUser(ClientDto(phone, name))
                .onSuccess {
                    direction.navigateToPasswordChecker()
                }
        }
    }
}