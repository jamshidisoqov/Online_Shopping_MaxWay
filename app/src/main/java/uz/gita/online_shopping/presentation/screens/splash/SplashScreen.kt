package uz.gita.online_shopping.presentation.screens.splash

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.online_shopping.R
import uz.gita.online_shopping.presentation.viewmodels.impl.SplashViewModelImpl
import uz.gita.online_shopping.presentation.viewmodels.SplashViewModel

// Created by Jamshid Isoqov an 10/21/2022
@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()
    override fun onResume() {
        super.onResume()
        viewModel.navigate()
    }
}