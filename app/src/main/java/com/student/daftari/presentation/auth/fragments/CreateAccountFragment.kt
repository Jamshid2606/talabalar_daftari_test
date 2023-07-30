package com.student.daftari.presentation.auth.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.student.daftari.R
import com.student.daftari.data.local.shp.AppReference
import com.student.daftari.data.model.User
import com.student.daftari.databinding.FragmentCreateAccountBinding
import com.student.daftari.presentation.auth.viewmodels.imp.AuthViewModelImp
import com.student.daftari.presentation.main.activity.MainActivity
import com.student.daftari.utils.enums.ScreenEnum
import com.student.daftari.utils.showToast
import com.student.daftari.utils.state
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreateAccountFragment :
    BaseFragment<FragmentCreateAccountBinding>(FragmentCreateAccountBinding::inflate) {
    private val viewModel: AuthViewModelImp by viewModels()
    private val shared by lazy { AppReference(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.successLiveData.observe(this) {
            shared.currentScreen =ScreenEnum.HOME
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        viewModel.errorLiveData.observe(this) { showToast(it) }
        viewModel.progressLiveData.observe(this) { state(it) }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreate() {
        userNameFocusedListener()
        passwordFocusListener()
        password2FocusListener()
        binding.yaratish.setOnClickListener {
            if (validation()) {
                val data = requireArguments().getSerializable("data ") as User
                val user = User()
                user.userName = binding.usernameEditText.text.toString()
                user.password = binding.passwordEditText.text.toString()
                user.phoneNumber = data.phoneNumber
                user.name = data.name
                viewModel.createAccount(user)
            }
        }
        binding.iconEslash.setOnClickListener {
            if (binding.iconEslash.drawable.constantState != resources.getDrawable(R.drawable.eslash_ich).constantState) {
                binding.iconEslash.setImageResource(R.drawable.eslash_ich)
            } else {
                binding.iconEslash.setImageResource(R.drawable.eslab_qolish)
            }
        }
    }

    private fun validation(): Boolean {
        if (!validUserName(binding.usernameEditText.text.toString()).isEmpty()) {
            YoYo.with(Techniques.Wobble).duration(500).repeat(0).playOn(binding.usernameTextInput)
            return false
        }
        if (!validPassword(binding.passwordEditText.text.toString()).isEmpty()) {
            YoYo.with(Techniques.Wobble).duration(500).repeat(0).playOn(binding.passwordTextInput)
            return false
        }
        if (!validPassword2(
                binding.passwordEditText.text.toString(),
                binding.password2EditText.text.toString()
            ).isEmpty()
        ) {
            YoYo.with(Techniques.Wobble).duration(500).repeat(0).playOn(binding.password2TextInput)
            return false
        }
        return true
    }

    private fun userNameFocusedListener() {
        binding.usernameEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.usernameTextInput.helperText =
                    validUserName(binding.usernameEditText.text.toString())
            }
        }
    }

    private fun passwordFocusListener() {
        binding.passwordEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.passwordTextInput.helperText =
                    validPassword(binding.passwordEditText.text.toString())
            }
        }
    }

    private fun password2FocusListener() {
        binding.password2EditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.password2TextInput.helperText = validPassword2(
                    binding.passwordEditText.text.toString(),
                    binding.password2EditText.text.toString()
                )
            }
        }
    }

    override fun onPause() {
        super.onPause()
    }

    private fun validUserName(userName: String): String {
        if (userName.length < 5) {
            return "User name kamida 5 ta belgidan iborat bo'lishi kerak"
        }
        return ""
    }

    private fun validPassword2(password1: String, password2: String): String {
        if (password1 != password2) {
            return "Parollar bir xil emas"
        }
        return ""
    }

    private fun validPassword(passwordText: String): String {
        if (passwordText.length < 8) {
            return "Minimum 8 ta belgi bo'lishi kerak"
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex())) {
            return "Kamida 1 ta katta harf bo'lishi kerak"
        }
        if (!passwordText.matches(".*[a-z].*".toRegex())) {
            return "Kamida 1 ta kichik harf bo'lishi kerak"
        }
        if (!passwordText.matches(".*[@#\$%^&*+=].*".toRegex())) {
            return "Kamida 1 ta maxsus belgi bo'lishi kerak (@#\$%^&*+=)"
        }
        return ""
    }

}