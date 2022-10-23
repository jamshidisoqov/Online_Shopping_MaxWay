package uz.gita.online_shopping.directions.impl

import uz.gita.online_shopping.directions.LoginScreenDirection
import uz.gita.online_shopping.navigation.Navigator
import uz.gita.online_shopping.presentation.screens.login.LoginScreenDirections
import javax.inject.Inject

class LoginScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : LoginScreenDirection {
    override suspend fun navigateToPasswordChecker() {
        navigator.navigateTo(LoginScreenDirections.actionLoginScreenToPasswordCheckoutScreen())
    }
}