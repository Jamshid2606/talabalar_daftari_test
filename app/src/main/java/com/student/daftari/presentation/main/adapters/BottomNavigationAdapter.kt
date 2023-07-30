package com.student.daftari.presentation.main.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.student.daftari.presentation.add.AddScreen
import com.student.daftari.presentation.home.HomeScreen
import com.student.daftari.presentation.profile.ProfileScreen
import com.student.daftari.presentation.search.SearchScreen
import com.student.daftari.presentation.setting.SettingScreen

class BottomNavigationAdapter(fragmentManager: FragmentActivity) :
    FragmentStateAdapter(fragmentManager) {


    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeScreen()
            }
            1 -> {
                SettingScreen()
            }
            2 -> {
                AddScreen()
            }
            3 -> {
                SearchScreen()
            }
            else -> {
                ProfileScreen()
            }
        }

    }
}