package com.student.daftari.presentation.profile

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.student.daftari.R
import com.student.daftari.databinding.ProfileScreenBinding
import com.student.daftari.presentation.auth.fragments.BaseFragment
import kotlinx.android.synthetic.main.profile_screen.*

class ProfileScreen:BaseFragment<ProfileScreenBinding>(ProfileScreenBinding::inflate) {
    override fun onViewCreate() {
        edit_txt.setOnClickListener {
            findNavController().navigate(R.id.editProfileScreen)
        }
    }
}