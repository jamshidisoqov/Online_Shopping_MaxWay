package uz.gita.online_shopping.directions.impl

import uz.gita.online_shopping.directions.ProfileScreenDirections
import uz.gita.online_shopping.navigation.Navigator
import javax.inject.Inject

class ProfileScreenDirectionsImpl @Inject constructor(
    private val appNavigator: Navigator
) : ProfileScreenDirections {

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