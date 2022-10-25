package uz.gita.online_shopping.presentation.screens.service

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.online_shopping.R
import uz.gita.online_shopping.databinding.ScreenServiceBinding


// Created by Jamshid Isoqov an 10/21/2022
class ServiceScreen : Fragment(R.layout.screen_service) {

    private val viewBinding: ScreenServiceBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.containerContingencyPolicy.setOnClickListener {
            val urlString = "https://maxway.uz/about"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.setPackage("com.android.chrome")
            requireContext().startActivity(intent)
        }

        viewBinding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }


}