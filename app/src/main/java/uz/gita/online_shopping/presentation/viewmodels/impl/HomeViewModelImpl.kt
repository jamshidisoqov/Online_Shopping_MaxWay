package uz.gita.online_shopping.presentation.viewmodels.impl

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.online_shopping.data.models.CategoryData
import uz.gita.online_shopping.data.models.ProductWithCount
import uz.gita.online_shopping.directions.MainScreenDirection
import uz.gita.online_shopping.domain.MainUseCase
import uz.gita.online_shopping.presentation.viewmodels.HomeViewModel
import uz.gita.online_shopping.utils.Basket
import uz.gita.online_shopping.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val mainUseCase: MainUseCase,
    private val direction: MainScreenDirection
) : HomeViewModel, ViewModel() {

    override val categoriesFlow = MutableSharedFlow<List<CategoryData>>()

    override val productFlow = MutableLiveData<List<ProductWithCount>>()

    override val loadingSharedFlow = MediatorLiveData<Boolean>()

    override val messageFlow = MutableSharedFlow<String>()

    override val errorMessageFlow = MutableSharedFlow<String>()


    override fun getAllCategories() {
        mainUseCase.getAllCategories()
            .onEach {
                it.onSuccess { list ->
                    categoriesFlow.emit(list)
                }
            }
            .onEach {
                it.onError { error ->
                    errorMessageFlow.emit(error.getMessage())
                }
            }
            .onEach {
                it.onMessage { message ->
                    messageFlow.emit(message)
                }
            }
            .launchIn(viewModelScope)
    }


    override fun categoryItemClick(categoryData: CategoryData) {
        //TODO sorted category
    }

    override fun getAllProducts() {
        viewModelScope.launch {
            loadingSharedFlow.value = true
            mainUseCase.getAllProducts().collectLatest {
                it.onSuccess { list ->
                    Basket.setList(list)
                }
                it.onMessage { message ->
                    messageFlow.emit(message)
                }
                it.onError { error ->
                    errorMessageFlow.emit(error.getMessage())
                }
                loadingSharedFlow.value = false
            }
        }
    }

    override fun addBasket(productWithCount: ProductWithCount) {
        Basket.addProduct(productWithCount)
    }

    override fun navigateBasketScreen() {
        viewModelScope.launch {
            direction.navigateBasketScreen()
        }
    }

    override fun openProductDetailsScreen(productWithCount: ProductWithCount) {
        viewModelScope.launch {
            direction.navigateProductsDetailsScreen(productWithCount)
        }
    }
}
