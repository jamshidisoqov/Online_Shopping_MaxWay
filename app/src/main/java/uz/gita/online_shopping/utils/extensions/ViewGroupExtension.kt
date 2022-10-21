package uz.gita.online_shopping.utils.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// Created by Jamshid Isoqov an 8/24/2022


fun ViewGroup.inflate(resId: Int): View {
    return LayoutInflater.from(this.context).inflate(resId, this, false)
}