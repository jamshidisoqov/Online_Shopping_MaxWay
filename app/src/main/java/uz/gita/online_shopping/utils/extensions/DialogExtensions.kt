package uz.gita.online_shopping.utils.extensions

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.viewbinding.ViewBinding

// Created by Jamshid Isoqov an 10/12/2022

fun Dialog.config(viewBinding: ViewBinding) {
    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    setContentView(viewBinding.root)
}