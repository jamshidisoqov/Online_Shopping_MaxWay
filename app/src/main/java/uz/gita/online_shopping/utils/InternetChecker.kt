package uz.gita.online_shopping.utils

import android.content.Context
import android.net.ConnectivityManager
import dagger.hilt.android.qualifiers.ApplicationContext


// Created by Jamshid Isoqov an 10/11/2022

@ApplicationContext
lateinit var context: Context

fun hasConnection(): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
}