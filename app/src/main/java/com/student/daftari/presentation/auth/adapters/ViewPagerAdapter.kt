package com.student.daftari.presentation.auth.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.student.daftari.data.model.User
import com.student.daftari.presentation.auth.fragments.LoginFragment
import com.student.daftari.presentation.auth.fragments.RegisterFragment

class ViewPagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {

    private lateinit var listenerItemClick:((String,String)->Unit)
    fun setRegisterBtnClickListener(listener:((String,String)->Unit)){
        listenerItemClick=listener
    }
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LoginFragment()
            else -> {
                val registerFragment = RegisterFragment()

                registerFragment.registerBtnClickListener { user, s ->
                    listenerItemClick.invoke(user,s)
                }
                registerFragment
            }


        }
    }

    override fun getItemCount(): Int = 2

}