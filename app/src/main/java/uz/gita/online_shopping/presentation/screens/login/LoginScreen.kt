package uz.gita.online_shopping.presentation.screens.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.online_shopping.R
import uz.gita.online_shopping.data.remote.RegistrationApi
import uz.gita.online_shopping.databinding.ScreenLoginBinding
import uz.gita.online_shopping.presentation.viewmodels.LoginViewModel
import uz.gita.online_shopping.presentation.viewmodels.impl.LoginViewModelImpl
import uz.gita.online_shopping.utils.extensions.*
import javax.inject.Inject

// Created by Jamshid Isoqov an 10/10/2022
@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {

    val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()

    @Inject
    lateinit var registrationApi: RegistrationApi

    val viewBinding: ScreenLoginBinding by viewBinding()

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.btnCheck.clicks()
            .debounce(100L)
            .onEach {
                val phone = viewBinding.inputPhone.text.toString()
                val name = viewBinding.inputName.text.toString()
                viewModel.login(phone, name)
            }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.toastFlow.onEach {
            toast(it)
        }.launchIn(lifecycleScope)

        viewModel.errorFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.loadingFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

    }
}