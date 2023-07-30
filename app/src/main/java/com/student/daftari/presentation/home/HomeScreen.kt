package com.student.daftari.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.student.daftari.R
import com.student.daftari.databinding.HomeScreenBinding
import com.student.daftari.presentation.adapters.HomeLessonsAdapter
import com.student.daftari.presentation.auth.fragments.BaseFragment
import com.student.daftari.utils.LocalData
import com.student.daftari.utils.LocalData.loadLessonsData
import kotlinx.android.synthetic.main.home_screen.*

class HomeScreen : BaseFragment<HomeScreenBinding>(HomeScreenBinding::inflate) {
    private val adapter by lazy { HomeLessonsAdapter() }
    override fun onViewCreate() {
        rv_pages.adapter = adapter
        adapter.submitList(loadLessonsData())

    }


}