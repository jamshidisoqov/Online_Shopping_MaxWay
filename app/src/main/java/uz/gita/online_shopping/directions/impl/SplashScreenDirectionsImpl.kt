package uz.gita.online_shopping.directions.impl

import uz.gita.online_shopping.directions.SplashScreenDirection
import uz.gita.online_shopping.navigation.Navigator
import uz.gita.online_shopping.presentation.screens.splash.SplashScreenDirections
import javax.inject.Inject

class SplashScreenDirectionsImpl @Inject constructor(
    private val navigator: Navigator
) : SplashScreenDirection {
    override suspend fun navigateToMain() {
        navigator.navigateTo(SplashScreenDirections.actionSplashScreenToMainScreen())
    }

    override suspend fun navigateToLogin() {
        navigator.navigateTo(SplashScreenDirections.actionSplashScreenToLoginScreen())
    }
}