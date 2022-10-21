package uz.gita.online_shopping.presentation.screens.orders.checkout.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.online_shopping.R

// Created by Jamshid Isoqov an 10/19/2022
@AndroidEntryPoint
class OrderMapFragment : Fragment(R.layout.screen_map) {

    private lateinit var googleMap: GoogleMap
    private lateinit var center: LatLng

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val map = childFragmentManager.findFragmentById(R.id.map) as OrderMap
        map.getMapAsync(map)
        map.onMapReady {
            googleMap = it
            googleMap.uiSettings.apply {
                isCompassEnabled = false
                isRotateGesturesEnabled = false
                isMyLocationButtonEnabled = false
            }
            googleMap.addMarker(MarkerOptions().position(NUKUS).title("Nukus"))
            if (!this::center.isInitialized)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NUKUS, 5f))

            googleMap.setOnCameraMoveListener {

            }
            googleMap.setOnMyLocationChangeListener { location ->

            }


            center = googleMap.cameraPosition.target

            googleMap.setOnCameraMoveListener {
                center = googleMap.cameraPosition.target
            }
        }
    }

    companion object {
        private val NUKUS = LatLng(42.460168, 59.607280)
    }


}