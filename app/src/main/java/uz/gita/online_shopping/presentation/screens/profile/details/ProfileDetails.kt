package uz.gita.online_shopping.presentation.screens.profile.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
import uz.gita.online_shopping.databinding.ScreenProfileDetailsBinding
import uz.gita.online_shopping.presentation.dialogs.ChooseDateDialog
import uz.gita.online_shopping.presentation.viewmodels.ProfileDetailsViewModel
import uz.gita.online_shopping.presentation.viewmodels.impl.ProfileDetailsViewModelImpl
import uz.gita.online_shopping.utils.extensions.getCurrentDate
import uz.gita.online_shopping.utils.extensions.toDate

// Created by Jamshid Isoqov an 10/20/2022
@AndroidEntryPoint
class ProfileDetails : Fragment(R.layout.screen_profile_details) {

    private val viewModel: ProfileDetailsViewModel by viewModels<ProfileDetailsViewModelImpl>()

    private val viewBinding: ScreenProfileDetailsBinding by viewBinding()

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.nameFlow.onEach {
            viewBinding.inputName.setText(it)
        }.launchIn(lifecycleScope)

        viewModel.phoneFlow.onEach {
            viewBinding.inputPhone.text = it
        }.launchIn(lifecycleScope)

        viewModel.birthdayFlow.onEach {
            viewBinding.inputBirthday.text = it
        }.launchIn(lifecycleScope)

        viewModel.openCalendarDialog.onEach {
            val dialog = ChooseDateDialog(
                requireContext(),
                viewBinding.inputBirthday.text.toString().toDate()
            )
            dialog.setConfirmClickListener {
                viewBinding.inputBirthday.text = getCurrentDate(it)
            }
            dialog.show()
        }.launchIn(lifecycleScope)

        viewBinding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }

        viewBinding.inputBirthday.setOnClickListener {
            viewModel.openCalendar()
        }
        viewBinding.btnConfirm.clicks().debounce(100L).onEach {
            viewModel.saveClicked(
                viewBinding.inputName.text.toString(),
                viewBinding.inputBirthday.text.toString()
            )
            findNavController().navigateUp()
        }.launchIn(lifecycleScope)
    }

}