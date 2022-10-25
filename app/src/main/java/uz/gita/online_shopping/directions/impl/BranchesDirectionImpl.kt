package uz.gita.online_shopping.directions.impl

import uz.gita.online_shopping.data.models.BranchData
import uz.gita.online_shopping.directions.BranchesDirection
import uz.gita.online_shopping.navigation.Navigator
import uz.gita.online_shopping.presentation.screens.branches.BranchesScreenDirections
import javax.inject.Inject

class BranchesDirectionImpl @Inject constructor(private val navigator: Navigator) :
    BranchesDirection {
    override suspend fun navigateToBranchesDetails(branchData: BranchData) {
        navigator.navigateTo(
            BranchesScreenDirections.actionBranchesScreenToBranchDetailsScreen(
                branchData
            )
        )
    }
}