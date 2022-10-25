package uz.gita.online_shopping.presentation.viewmodels.impl

import android.annotation.SuppressLint
import android.location.Geocoder
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.gita.online_shopping.presentation.viewmodels.MapViewModel
import uz.gita.online_shopping.utils.exceptions.NoInternetConnection
import uz.gita.online_shopping.utils.hasConnection
import javax.inject.Inject

@HiltViewModel
class MapViewModelImpl @Inject constructor(
    private val geocoder: Geocoder,
    private val fusedLocationClient: FusedLocationProviderClient
) : MapViewModel, ViewModel() {

    override val address = MutableSharedFlow<String>()

    override val currentLocationFlow = MutableLiveData<LatLng>()

    private var cancellationTokenSource = CancellationTokenSource()

    override val loadingFlow = MutableSharedFlow<Boolean>()

    override val toastFlow = MutableSharedFlow<String>()

    override val messageFlow = MutableSharedFlow<String>()

    override val errorFlow = MutableSharedFlow<String>()


    override fun getAddressByLocation(latLng: LatLng) {
        viewModelScope.launch(Dispatchers.IO) {
            loadingFlow.emit(true)
            try {
                if (hasConnection()) {
                    val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                    address.emit(withContext(Dispatchers.IO) {
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

    @SuppressLint("MissingPermission")
    override fun requestCurrentLocation() {
        viewModelScope.launch {
            loadingFlow.emit(true)
            fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            )
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val location = task.result
                        currentLocationFlow.postValue(LatLng(location.latitude, location.longitude))
                    } else {
                        errorFlow.tryEmit(task.exception!!.localizedMessage!!)
                    }
                }
        }

    }
}