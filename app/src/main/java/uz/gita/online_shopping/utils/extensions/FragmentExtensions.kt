package uz.gita.online_shopping.utils.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import uz.gita.online_shopping.MainActivity
import uz.gita.online_shopping.presentation.dialogs.ErrorDialog
import uz.gita.online_shopping.presentation.dialogs.MessageDialog
import uz.gita.online_shopping.presentation.dialogs.SuccessDialog

// Created by Jamshid Isoqov an 10/12/2022


fun Fragment.hideProgress() {
    (requireActivity() as MainActivity).hideProgress()
}

fun Fragment.showProgress() {
    (requireActivity() as MainActivity).showProgress()
}

fun Fragment.showErrorDialog(message: String) {
    val dialog = ErrorDialog(requireContext(), message)
    dialog.show()
}

fun Fragment.showMessageDialog(message: String) {
    val dialog = MessageDialog(requireContext(), message)
    dialog.show()
}

fun Fragment.showSuccessDialog(message: String) {
    val dialog = SuccessDialog(requireContext(), message)
    dialog.show()
}

fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}