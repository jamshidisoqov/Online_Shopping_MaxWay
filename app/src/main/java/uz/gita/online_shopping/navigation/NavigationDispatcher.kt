package uz.gita.online_shopping.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationDispatcher @Inject constructor() : NavigationHandler, Navigator {
    override val navigationStack = MutableSharedFlow<NavigationArgs>()

    private suspend fun navigator(args: NavigationArgs) {
        navigationStack.emit(args)
    }

    override suspend fun navigateTo(direction: Direction) = navigator {
        navigate(direction)
    }
}