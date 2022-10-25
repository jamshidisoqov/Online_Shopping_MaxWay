package uz.gita.online_shopping.presentation.screens.branches.details

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.online_shopping.BuildConfig
import uz.gita.online_shopping.R
import uz.gita.online_shopping.databinding.ScreenBranchDetailsBinding
import uz.gita.online_shopping.presentation.viewmodels.BranchDetailsViewModel
import uz.gita.online_shopping.presentation.viewmodels.impl.BranchDetailsViewModelImpl
import uz.gita.online_shopping.utils.extensions.*


// Created by Jamshid Isoqov an 10/25/2022
@AndroidEntryPoint
class BranchDetailsScreen : Fragment(R.layout.screen_branch_details) {

    private val viewBinding: ScreenBranchDetailsBinding by viewBinding()

    private val viewModel: BranchDetailsViewModel by viewModels<BranchDetailsViewModelImpl>()

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) permissionApprovedSnackBar() else permissionDeniedSnackBar()
        }
    private val args: BranchDetailsScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }
        val branchData = args.branchData

        viewModel.address.onEach {
            viewBinding.tvLocation.text = it
        }.launchIn(lifecycleScope)

        viewModel.loadingFlow.onEach {
            if (it) showProgress() else
                hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.errorFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.getBranchLocation(branchData.address)

        Glide.with(requireContext())
            .load(branchData.imageUrl)
            .placeholder(R.drawable.place)
            .into(viewBinding.imageBranch)

        viewBinding.tvBranchName.text = branchData.name
        viewBinding.tvWay.text = branchData.way
        viewBinding.tvPhone.text = branchData.phone
        viewBinding.tvScheduleTime.text = branchData.schedule

        viewBinding.tvPhone.setOnClickListener {
            if (hasPermission(Manifest.permission.CALL_PHONE)) {
                val uri = "tel:" + args.branchData.phone
                val intent = Intent(Intent.ACTION_CALL)
                intent.data = Uri.parse(uri)
                startActivity(intent)
            }else {
                requestPermission.launch(Manifest.permission.CALL_PHONE)
            }
        }
    }

    private fun permissionApprovedSnackBar() {
        Snackbar.make(
            viewBinding.root, R.string.permission_approved_explanation,
            BaseTransientBottomBar.LENGTH_LONG
        ).show()
    }

    private fun permissionDeniedSnackBar() {
        Snackbar.make(
            viewBinding.root,
            R.string.fine_permission_denied_explanation,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .setAction(R.string.settings) {  launchSettings()}
            .setActionTextColor(Color.WHITE)
            .show()
    }

    private fun launchSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts(
            "package",
            BuildConfig.APPLICATION_ID, null
        )
        intent.data = uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

}