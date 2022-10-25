package uz.gita.online_shopping.utils.extensions

import com.google.android.gms.maps.model.LatLng
import uz.gita.online_shopping.data.models.Address

// Created by Jamshid Isoqov an 10/24/2022


fun LatLng.toAddress():Address = Address(this.latitude,this.longitude)