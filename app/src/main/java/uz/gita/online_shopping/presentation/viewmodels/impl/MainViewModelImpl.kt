package uz.gita.online_shopping.presentation.viewmodels.impl

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.online_shopping.directions.MainScreenDirection
import uz.gita.online_shopping.domain.MainUseCase
import uz.gita.online_shopping.presentation.viewmodels.MainViewModel
import uz.gita.online_shopping.utils.Basket
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val direction: MainScreenDirection
) : MainViewModel, ViewModel() {

    override val basketFlow = MutableLiveData<Double>()

    override val loadingLiveData = MediatorLiveData<Boolean>()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            loadingLiveData.addSource(Basket.productsListLiveData) {
                var summ = 0.0
                for (i in it) {
                    if (i.count > 0) {
                        summ += i.count * i.productData.price
                    }
                }
                basketFlow.postValue(summ)
            }
        }
    }

    override fun navigateBasket() {
        viewModelScope.launch {
            direction.navigateBasketScreen()
        }
    }

    override fun searchClicked() {
        viewModelScope.launch {
            direction.navigateSearchScreen()
        }
    }
}