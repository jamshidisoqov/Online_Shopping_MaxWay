package uz.gita.online_shopping.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import uz.gita.online_shopping.databinding.DialogConfirmBinding
import uz.gita.online_shopping.utils.extensions.config

// Created by Jamshid Isoqov an 10/12/2022
class ConfirmDialog(ctx: Context) : Dialog(ctx) {

    private var confirmClickListener: (() -> Unit)? = null

    private lateinit var binding: DialogConfirmBinding

    fun setConfirmClickListener(block: () -> Unit) {
        confirmClickListener = block
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogConfirmBinding.inflate(layoutInflater)
        config(viewBinding = binding)
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnConfirm.setOnClickListener {
            confirmClickListener?.invoke()
            dismiss()
        }
    }

}