package uz.gita.online_shopping.directions.impl

import uz.gita.online_shopping.directions.PasswordCheckoutScreenDirection
import uz.gita.online_shopping.navigation.Navigator
import uz.gita.online_shopping.presentation.screens.login.checkout_password.PasswordCheckoutScreenDirections
import javax.inject.Inject

class PasswordCheckoutScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : PasswordCheckoutScreenDirection {
    override suspend fun navigateToMain() {
        navigator.navigateTo(PasswordCheckoutScreenDirections.actionPasswordCheckoutScreenToMainScreen())
    }
}