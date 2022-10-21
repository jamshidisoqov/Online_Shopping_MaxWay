package uz.gita.online_shopping.presentation.dialogs

import android.app.Dialog
import android.content.Context

// Created by Jamshid Isoqov an 10/12/2022
class ConfirmDialog(ctx: Context) : Dialog(ctx) {

    private var setConfirmClickListener: (() -> Unit)? = null

    fun setConfirmClickListener() {
        
    }

}