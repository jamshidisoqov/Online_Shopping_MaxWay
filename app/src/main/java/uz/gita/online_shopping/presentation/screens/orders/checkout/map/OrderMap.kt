package uz.gita.online_shopping.presentation.screens.orders.checkout.map

import android.location.Location
import android.location.LocationListener
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

// Created by Jamshid Isoqov an 10/19/2022
class OrderMap: OnMapReadyCallback, SupportMapFragment(), LocationListener  {

    private var map: (googleMap: GoogleMap) -> Unit = {}

    fun onMapReady(map: (googleMap: GoogleMap) -> Unit) {
        this.map = map
    }
    override fun onMapReady(p0: GoogleMap) {
        map.invoke(p0)
    }

    override fun onLocationChanged(location: Location) {

    }
}