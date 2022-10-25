package uz.gita.online_shopping.data.models

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize

// Created by Jamshid Isoqov an 10/8/2022
@Parcelize
data class Address(
    val lat: Double,
    val lon: Double
) : Parcelable {
    fun toLatLong() = LatLng(lat, lon)
}
