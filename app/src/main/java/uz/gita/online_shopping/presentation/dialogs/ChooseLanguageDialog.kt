package uz.gita.online_shopping.presentation.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.online_shopping.data.prefs.MySharedPref
import uz.gita.online_shopping.databinding.DialogLanguageBinding
import uz.gita.online_shopping.utils.extensions.inVisible
import uz.gita.online_shopping.utils.extensions.visible
import javax.inject.Inject

// Created by Jamshid Isoqov an 10/25/2022
@AndroidEntryPoint
class ChooseLanguageDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogLanguageBinding

    private var changeLanguageListener:(()->Unit)? = null

    fun setChangeLanguageListener(block:()->Unit){
        changeLanguageListener = block
    }

    @Inject
    lateinit var mySharedPref: MySharedPref

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogLanguageBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        checkLanguage()
        binding.containerUzbekLng.setOnClickListener {
            mySharedPref.language = 0
            checkLanguage()
        }
        binding.containerEnglishLng.setOnClickListener {
            mySharedPref.language = 1
            checkLanguage()
        }

        return binding.root
    }

    private fun checkLanguage() {
        val lan = mySharedPref.language
        binding.apply {
            if (lan == 0) {
                imageUzbCheck.visible()
                imageEngCheck.inVisible()
            } else {
                imageUzbCheck.inVisible()
                imageEngCheck.visible()
            }
        }
        changeLanguageListener?.invoke()
    }
}