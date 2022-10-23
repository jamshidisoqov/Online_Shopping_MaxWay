package uz.gita.online_shopping.directions.impl

import uz.gita.online_shopping.directions.ProfileScreenDirection
import uz.gita.online_shopping.navigation.Navigator
import javax.inject.Inject

class ProfileScreenDirectionsImpl @Inject constructor(
    private val appNavigator: Navigator
) : ProfileScreenDirection {

    override suspend fun openEditProfile() {
        //TODO navigate
    }

    override suspend fun openBranches() {
        //TODO navigate
    }

    override suspend fun openSettings() {
        //TODO navigate
    }

    override suspend fun openServices() {
        //TODO navigate
    }
}