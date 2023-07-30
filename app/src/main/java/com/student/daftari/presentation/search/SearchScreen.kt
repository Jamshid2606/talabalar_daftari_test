package com.student.daftari.presentation.search

import android.widget.ArrayAdapter
import com.student.daftari.databinding.SearchScreenBinding
import com.student.daftari.presentation.auth.fragments.BaseFragment
import kotlinx.android.synthetic.main.search_screen.*


class SearchScreen:BaseFragment<SearchScreenBinding>(SearchScreenBinding::inflate) {
    var subjects = arrayOf("Tarix", "Matematika", "Ingiliz tili", "Iqtisodiyot", "Ona tili", "Adabiyot")
    override fun onViewCreate() {
        autoCompleteTextView()

    }
    private fun autoCompleteTextView(){
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line, subjects)
        binding.searchEditTxt.setAdapter(adapter)

        search_edit_txt.threshold =3

    }
}