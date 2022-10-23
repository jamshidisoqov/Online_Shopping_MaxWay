package uz.gita.online_shopping.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.online_shopping.directions.SplashScreenDirection
import uz.gita.online_shopping.domain.ProfileUseCase
import uz.gita.online_shopping.presentation.viewmodels.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val direction: SplashScreenDirection
) : SplashViewModel, ViewModel() {

    override fun navigate() {
        viewModelScope.launch {
            delay(1500)
            if (profileUseCase.getToken().isEmpty()) {
                direction.navigateToLogin()
            } else {
                direction.navigateToMain()
            }
        }
    }
}