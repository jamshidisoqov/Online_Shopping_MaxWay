package uz.gita.online_shopping.navigation

import androidx.navigation.NavDirections

typealias Direction = NavDirections

interface Navigator {
    suspend fun navigateTo(direction: Direction)
}