package uz.gita.online_shopping.utils.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

// Created by Jamshid Isoqov an 10/12/2022


@SuppressLint("SimpleDateFormat")
fun getCurrentDate(): String {
    val c = Calendar.getInstance().time
    return SimpleDateFormat("MMM dd,yyyy").format(c).uppercase()
}

@SuppressLint("SimpleDateFormat")
fun getCurrentDate(date: Date): String {
    return SimpleDateFormat("MMM dd,yyyy").format(date).uppercase()
}

@SuppressLint("SimpleDateFormat")
fun String.toDate(): Date {
    val format = SimpleDateFormat("MMM dd,yyyy")
    format.parse(this)
    return format.calendar.time
}


