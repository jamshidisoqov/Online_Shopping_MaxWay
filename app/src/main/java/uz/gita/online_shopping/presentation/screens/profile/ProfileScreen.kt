package uz.gita.online_shopping.presentation.screens.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.online_shopping.R
import uz.gita.online_shopping.databinding.ScreenProfileBinding
import uz.gita.online_shopping.presentation.viewmodels.ProfileViewModel
import uz.gita.online_shopping.presentation.viewmodels.impl.ProfileViewModelImpl

// Created by Jamshid Isoqov an 10/10/2022
class ProfileScreen : Fragment(R.layout.screen_profile) {

    private val viewModel: ProfileViewModel by viewModels<ProfileViewModelImpl>()

    private val viewBinding: ScreenProfileBinding by viewBinding()

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.nameFlow.onEach {
            viewBinding.tvUserName.text = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.phoneFlow.onEach {
            viewBinding.tvPhoneNumber.text = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewBinding.containerLocation.clicks().debounce(100L).onEach {
            viewModel.openBranches()
        }.launchIn(lifecycleScope)

        viewBinding.containerSettings.clicks().debounce(100L).onEach {
            viewModel.openSettings()
        }.launchIn(lifecycleScope)

        viewBinding.containerService.clicks().debounce(100L).onEach {
            viewModel.openServices()
        }.launchIn(lifecycleScope)

        viewBinding.imageEdit.clicks().debounce(100L).onEach {
            viewModel.editProfile()
        }.launchIn(lifecycleScope)


    }


}