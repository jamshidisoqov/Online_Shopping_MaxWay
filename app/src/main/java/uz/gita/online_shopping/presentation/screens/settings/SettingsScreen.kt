package uz.gita.online_shopping.presentation.screens.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.online_shopping.R
import uz.gita.online_shopping.data.prefs.MySharedPref
import uz.gita.online_shopping.databinding.ScreenSettingsBinding
import uz.gita.online_shopping.presentation.dialogs.ChooseLanguageDialog
import javax.inject.Inject

// Created by Jamshid Isoqov an 10/21/2022
@AndroidEntryPoint
class SettingsScreen : Fragment(R.layout.screen_settings) {

    @Inject
    lateinit var mySharedPref: MySharedPref
    private val viewBinding: ScreenSettingsBinding by viewBinding()

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }
        viewBinding.containerLanguage.clicks().debounce(100L).onEach {
            val dialog = ChooseLanguageDialog()
            dialog.setChangeLanguageListener {
                changeLanguage()
            }
            dialog.show(childFragmentManager, "language")
        }.launchIn(lifecycleScope)

        changeLanguage()
    }


    private fun changeLanguage() {
        viewBinding.tvLan.text =
            resources.getString(if (mySharedPref.language == 0) R.string.uzbek else R.string.english)
    }
}