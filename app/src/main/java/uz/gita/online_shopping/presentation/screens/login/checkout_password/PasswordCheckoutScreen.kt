package uz.gita.online_shopping.presentation.screens.login.checkout_password

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.online_shopping.R
import uz.gita.online_shopping.databinding.ScreenPasswordCheckoutBinding
import uz.gita.online_shopping.presentation.viewmodels.PasswordCheckoutViewModel
import uz.gita.online_shopping.presentation.viewmodels.impl.PasswordCheckoutViewModelImpl
import uz.gita.online_shopping.utils.extensions.toast
import java.util.concurrent.TimeUnit

// Created by Jamshid Isoqov an 10/10/2022
@AndroidEntryPoint
class PasswordCheckoutScreen : Fragment(R.layout.screen_password_checkout) {


    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var verificationId: String

    private val viewModel: PasswordCheckoutViewModel by viewModels<PasswordCheckoutViewModelImpl>()

    private val viewBinding: ScreenPasswordCheckoutBinding by viewBinding()

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        firebaseAuth = FirebaseAuth.getInstance()
        //sendVerificationCode("+998907144102")
        viewBinding.btnCheck.clicks()
            .debounce(100L)
            .onEach {
                val sms = viewBinding.smsChecker.enteredCode
                viewModel.check(sms, sms)
            }.launchIn(lifecycleScope)
    }


    private fun sendVerificationCode(phone: String) {
        val options = PhoneAuthOptions.newBuilder(firebaseAuth).setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(p0, p1)
                    verificationId = p0
                }

                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    val code = p0.smsCode
                    if (code != null) {
                        verifyCode(code)
                        Log.d("TTT", "onVerificationFailed:$code ")
                    }
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Log.d("TTT", "onVerificationFailed:${p0.localizedMessage} ")
                    toast(p0.localizedMessage!!)
                }

            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun verifyCode(code: String) {

    }
}