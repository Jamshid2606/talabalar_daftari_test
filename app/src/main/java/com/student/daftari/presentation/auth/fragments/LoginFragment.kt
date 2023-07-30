package com.student.daftari.presentation.auth.fragments

import com.student.daftari.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override fun onViewCreate() {
        binding.root.setOnClickListener {

        }
    }
}