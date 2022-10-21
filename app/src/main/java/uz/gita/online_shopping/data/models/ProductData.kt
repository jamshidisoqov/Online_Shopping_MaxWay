package uz.gita.online_shopping.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// Created by Jamshid Isoqov an 10/8/2022
@Parcelize
data class ProductData(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val price: Double,
    val desc: String,
    val categoryId: Long
):Parcelable
