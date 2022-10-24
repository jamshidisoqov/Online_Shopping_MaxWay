package uz.gita.online_shopping.directions.impl

import uz.gita.online_shopping.directions.ProfileScreenDirection
import uz.gita.online_shopping.navigation.Navigator
import uz.gita.online_shopping.presentation.screens.MainScreenDirections
import javax.inject.Inject

class ProfileScreenDirectionsImpl @Inject constructor(
    private val appNavigator: Navigator
) : ProfileScreenDirection {

    override suspend fun openEditProfile() {
        appNavigator.navigateTo(MainScreenDirections.actionMainScreenToProfileDetails())
    }

    override suspend fun openBranches() {
        appNavigator.navigateTo(MainScreenDirections.actionMainScreenToBranchesScreen())
    }

    override suspend fun openSettings() {
        appNavigator.navigateTo(MainScreenDirections.actionMainScreenToSettingsScreen())
    }

    override suspend fun openServices() {
        appNavigator.navigateTo(MainScreenDirections.actionMainScreenToServiceScreen())
    }
}