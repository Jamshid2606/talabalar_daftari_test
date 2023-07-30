package com.student.daftari.presentation.main.screen

import android.os.Bundle
import android.view.View
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.student.daftari.R
import com.student.daftari.databinding.MainScreenBinding
import com.student.daftari.presentation.auth.fragments.BaseFragment
import com.student.daftari.presentation.main.adapters.BottomNavigationAdapter
import kotlinx.android.synthetic.main.main_screen.*

class MainScreen :BaseFragment<MainScreenBinding>(MainScreenBinding::inflate) {
    override fun onViewCreate() {
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val adapter =BottomNavigationAdapter(requireActivity())
        mainPager.adapter = adapter
        mainPager.isUserInputEnabled = false
        main_bottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.book_home -> {
                    mainPager.currentItem = 0
                }
                R.id.setting_item -> {

                    mainPager.currentItem = 1
                }
                R.id.add -> {
                    mainPager.currentItem = 2

                }
                R.id.search -> {
                    mainPager.currentItem = 3

                }
                R.id.profile -> {
                    mainPager.currentItem = 4
                }
            }
            true
        }

    }

    override fun onResume() {
        super.onResume()
    }

}