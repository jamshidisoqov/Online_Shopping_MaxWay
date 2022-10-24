package uz.gita.online_shopping.presentation.viewmodels

import androidx.lifecycle.LiveData
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.SharedFlow

// Created by Jamshid Isoqov an 10/24/2022
interface MapViewModel {

    val loadingFlow: SharedFlow<Boolean>

    val toastFlow: SharedFlow<String>

    val messageFlow: SharedFlow<String>

    val errorFlow: SharedFlow<String>

    val address:SharedFlow<String>

    val currentLocationFlow:LiveData<LatLng>

    fun getAddressByLocation(latLng: LatLng)

    fun requestCurrentLocation()
}