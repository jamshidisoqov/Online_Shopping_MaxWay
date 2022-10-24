package uz.gita.online_shopping.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import uz.gita.online_shopping.App


// Created by Jamshid Isoqov an 10/11/2022

fun hasConnection(): Boolean = App.instance.hasConnection()

fun Context.hasConnection(): Boolean {
    val result: Boolean
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities =
        connectivityManager.activeNetwork ?: return false
    val actNw =
        connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
    result = when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
    return result
}