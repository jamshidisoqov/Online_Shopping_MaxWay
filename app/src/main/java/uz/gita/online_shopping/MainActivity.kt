package uz.gita.online_shopping

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.online_shopping.navigation.NavigationHandler
import uz.gita.online_shopping.presentation.dialogs.ProgressDialog
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    lateinit var navigationHandler: NavigationHandler

    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navigationHandler.navigationStack
            .onEach { it.invoke(fragment.findNavController()) }
            .launchIn(lifecycleScope)
        dialog = ProgressDialog(this)
    }

    fun showProgress() {
        dialog.show()
    }


    fun hideProgress() {
        dialog.cancel()
    }
}