package uz.gita.online_shopping.presentation.screens.orders.checkout.map

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.ldralighieri.corbind.view.clicks
import uz.gita.online_shopping.BuildConfig
import uz.gita.online_shopping.R
import uz.gita.online_shopping.databinding.ScreenMapBinding
import uz.gita.online_shopping.presentation.viewmodels.MapViewModel
import uz.gita.online_shopping.presentation.viewmodels.impl.MapViewModelImpl
import uz.gita.online_shopping.utils.Basket
import uz.gita.online_shopping.utils.extensions.*

// Created by Jamshid Isoqov an 10/19/2022
@AndroidEntryPoint
class OrderMapFragment : Fragment(R.layout.screen_map) {

    private val viewModel: MapViewModel by viewModels<MapViewModelImpl>()

    private val viewBinding: ScreenMapBinding by viewBinding()

    private var job: Job? = null
    private lateinit var googleMap: GoogleMap
    private lateinit var center: LatLng

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) permissionApprovedSnackBar() else permissionDeniedSnackBar()
        }

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val map = childFragmentManager.findFragmentById(R.id.map) as OrderMap
        map.getMapAsync(map)
        map.onMapReady {
            googleMap = it
            googleMap.uiSettings.apply {
                isCompassEnabled = false
                isRotateGesturesEnabled = false
                isMyLocationButtonEnabled = false
            }
            googleMap.addMarker(MarkerOptions().position(NUKUS).title("Nukus"))
            if (!this::center.isInitialized)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NUKUS, 5f))


            googleMap.setOnCameraIdleListener {
                job?.cancel()
                job = viewLifecycleOwner.lifecycleScope.launch {
                    delay(2000L)
                    viewModel.getAddressByLocation(googleMap.cameraPosition.target)
                }
            }

            center = googleMap.cameraPosition.target

        }

        viewBinding.btnMyLocation.clicks().debounce(100L).onEach {
            if (isLocationEnabled()) locationRequest()
            else showAlert()
        }.launchIn(lifecycleScope)



        viewModel.address.onEach {
            viewBinding.tvAddressName.text = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.loadingFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.errorFlow.onEach {
            showErrorDialog(message = it)
        }.launchIn(lifecycleScope)

        viewModel.currentLocationFlow.observe(viewLifecycleOwner) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it, 10f))
            viewModel.getAddressByLocation(it)
        }

        viewBinding.btnConfirm.setOnClickListener {
            Basket.locationLiveData.value =
                Pair(viewBinding.tvAddressName.text.toString(), googleMap.cameraPosition.target)
            findNavController().navigateUp()
        }

    }

    companion object {
        private val NUKUS = LatLng(42.460168, 59.607280)
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
            .setAction(R.string.settings) { launchSettings() }
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

    private fun locationRequest() {
        if (hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) viewModel.requestCurrentLocation()
        else requestPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }


    private fun showAlert() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle(getString(R.string.enable_location))
            .setMessage(getString(R.string.enable_location_message))
            .setPositiveButton(getString(R.string.location_settings)) { _, _ ->
                val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(myIntent)
            }
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
        dialog.show()
    }

}