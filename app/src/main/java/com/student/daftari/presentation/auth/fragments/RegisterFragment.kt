package com.student.daftari.presentation.auth.fragments

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.student.daftari.R
import com.student.daftari.databinding.FragmentRegisterBinding
import com.student.daftari.presentation.auth.viewmodels.imp.AuthViewModelImp
import com.student.daftari.utils.checkPhone
import com.student.daftari.utils.showToast
import com.student.daftari.utils.state
import com.student.daftari.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register.*

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private lateinit var registerBtnClickListener: (String, String) -> Unit

    fun registerBtnClickListener(listener: ((String, String) -> Unit)) {
        registerBtnClickListener = listener
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreate() {
        binding.register.setOnClickListener {
            if (validation()) {
               registerBtnClickListener.invoke(name_edit_text.text.toString(),phone_edit_text.text.toString())
            }
        }

    }

    private fun validation(): Boolean {
        if (binding.nameEditText.text.toString() == "") {
            YoYo.with(Techniques.Wobble).duration(500).repeat(0).playOn(binding.nameTextInput)
            return false
        } else {
            if (binding.nameEditText.text.toString().length < 3) {
                YoYo.with(Techniques.Wobble).duration(500).repeat(0).playOn(binding.nameTextInput)
                toast("Ism kamida 3 ta belgidan iborat bo'lishi kerak")
                return false
            }
        }
        if (binding.phoneEditText.text.toString() == "") {
            YoYo.with(Techniques.Wobble).duration(500).repeat(0).playOn(binding.phoneTextInput)
            return false
        } else {
            if (!binding.phoneEditText.text.toString().checkPhone()) {
                YoYo.with(Techniques.Wobble).duration(500).repeat(0).playOn(binding.phoneTextInput)
                return false
            }
        }
        return true
    }

    override fun onPause() {
        super.onPause()
        state(false)

    }
}