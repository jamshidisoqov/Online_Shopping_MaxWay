package uz.gita.online_shopping.presentation.viewmodels.impl

import android.location.Geocoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.gita.online_shopping.data.models.Address
import uz.gita.online_shopping.presentation.viewmodels.BranchDetailsViewModel
import uz.gita.online_shopping.utils.exceptions.NoInternetConnection
import uz.gita.online_shopping.utils.hasConnection
import javax.inject.Inject

@HiltViewModel
class BranchDetailsViewModelImpl @Inject constructor(
    private val geocoder: Geocoder
) : BranchDetailsViewModel, ViewModel() {


    override val address = MutableSharedFlow<String>()

    override val loadingFlow = MutableSharedFlow<Boolean>()

    override val messageFlow = MutableSharedFlow<String>()

    override val errorFlow = MutableSharedFlow<String>()


    override fun getBranchLocation(address: Address) {
        val latLng = address.toLatLong()
        viewModelScope.launch(Dispatchers.IO) {
            loadingFlow.emit(true)
            try {
                if (hasConnection()) {
                    val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                    this@BranchDetailsViewModelImpl.address.emit(withContext(Dispatchers.IO) {
                        loadingFlow.emit(false)
                        try {
                            if (addresses.size > 0) addresses[0].getAddressLine(0)
                            else "Не указан"
                        } catch (e: Exception) {
                            e.localizedMessage
                        }
                    }!!)
                } else throw NoInternetConnection()
            } catch (e: Exception) {
                loadingFlow.emit(false)
                errorFlow.emit(e.localizedMessage!!)
            }
        }
    }
}