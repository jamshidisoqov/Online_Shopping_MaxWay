package uz.gita.online_shopping.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.gita.online_shopping.directions.PasswordCheckoutScreenDirection
import uz.gita.online_shopping.presentation.viewmodels.PasswordCheckoutViewModel
import javax.inject.Inject

@HiltViewModel
class PasswordCheckoutViewModelImpl @Inject constructor(
    private val direction: PasswordCheckoutScreenDirection
) : PasswordCheckoutViewModel, ViewModel() {
    override val loadingFlow = MutableSharedFlow<Boolean>()

    override val messageFlow = MutableSharedFlow<String>()

    override val toastFlow = MutableSharedFlow<String>()

    override val errorFlow = MutableSharedFlow<String>()

    override fun check(password: String, verifySms: String) {
        viewModelScope.launch {
            if (password == verifySms) {
                toastFlow.emit("Account crated")
                direction.navigateToMain()
            } else {
                toastFlow.emit("Sms not verified")
            }
        }
    }

}