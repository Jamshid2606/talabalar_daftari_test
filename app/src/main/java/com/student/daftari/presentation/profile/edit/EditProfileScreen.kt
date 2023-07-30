package com.student.daftari.presentation.profile.edit

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.navigation.fragment.findNavController
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.student.daftari.R
import com.student.daftari.databinding.EditProfileScreenBinding
import com.student.daftari.presentation.auth.fragments.BaseFragment
import com.student.daftari.utils.gone
import com.student.daftari.utils.visible
import kotlinx.android.synthetic.main.edit_profile_screen.*
import java.util.*

class EditProfileScreen :
    BaseFragment<EditProfileScreenBinding>(EditProfileScreenBinding::inflate) {
    private var isClick = false
    private var position = ""
    override fun onViewCreate() = binding.run {
        initDatePicker()
        initSchoolType()
        initClick()
    }

    private fun initClick() {
        binding
            .apply {
                edit_profile_toolbar.setNavigationOnClickListener {
                    findNavController().popBackStack()
                }

            }
    }

    private fun initDatePicker() {
        binding.apply {
            containerAge.setOnClickListener {
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { view, year, monthOfYear, dayOfMonth ->
                        age_txt.text =
                            "$dayOfMonth" + "." + (monthOfYear + 1) + "." + year
                    },
                    year,
                    month,
                    day
                )
                datePickerDialog.show()
            }

        }
    }

    private fun initSchoolType() {
        binding.apply {
            container_school_type.setOnClickListener {
                if (!isClick) {
                    isClick = true
                    YoYo.with(Techniques.FadeInUp).duration(700)
                        .playOn(containerSchoolTttype)
                    container_school_tttype.visible()
                } else {
                    isClick = false
                    YoYo.with(Techniques.FadeOutUp).duration(500)
                        .playOn(containerSchoolTttype)
                    container_school_tttype.gone()

                }
            }

            radioContainer.setOnClickListener {
                position = "Unversitet"
                clearRadioWithPosition(1)
            }

            radioContainer2.setOnClickListener {
                position = "Kollej"
                clearRadioWithPosition(2)
            }

            radioContainer3.setOnClickListener {
                position = "Litsey"
                clearRadioWithPosition(3)
            }

            radioContainer4.setOnClickListener {
                position = "Maktabz"
                clearRadioWithPosition(4)
            }
        }

    }

    private fun clearRadioWithPosition(position: Int) {
        binding.apply {
            when (position) {
                1 -> {
                    radio1.setImageResource(R.drawable.radio_bg)
                    radio1.borderColor = Color.WHITE

                    radio2.setImageResource(R.drawable.unselected_radio_bg)
                    radio2.borderColor = Color.WHITE

                    radio3.setImageResource(R.drawable.unselected_radio_bg)
                    radio3.borderColor = Color.WHITE

                    radio4.setImageResource(R.drawable.unselected_radio_bg)
                    radio4.borderColor = Color.WHITE
                }
                2 -> {
                    radio2.setImageResource(R.drawable.radio_bg)
                    radio2.borderColor = Color.WHITE

                    radio1.setImageResource(R.drawable.unselected_radio_bg)
                    radio1.borderColor = Color.WHITE

                    radio3.setImageResource(R.drawable.unselected_radio_bg)
                    radio3.borderColor = Color.WHITE

                    radio4.setImageResource(R.drawable.unselected_radio_bg)
                    radio4.borderColor = Color.WHITE
                }

                3 -> {
                    radio3.setImageResource(R.drawable.radio_bg)
                    radio3.borderColor = Color.WHITE

                    radio2.setImageResource(R.drawable.unselected_radio_bg)
                    radio2.borderColor = Color.WHITE

                    radio1.setImageResource(R.drawable.unselected_radio_bg)
                    radio1.borderColor = Color.WHITE

                    radio4.setImageResource(R.drawable.unselected_radio_bg)
                    radio4.borderColor = Color.WHITE
                }
                4 -> {
                    radio4.setImageResource(R.drawable.radio_bg)
                    radio4.borderColor = Color.WHITE

                    radio2.setImageResource(R.drawable.unselected_radio_bg)
                    radio2.borderColor = Color.WHITE

                    radio1.setImageResource(R.drawable.unselected_radio_bg)
                    radio1.borderColor = Color.WHITE

                    radio3.setImageResource(R.drawable.unselected_radio_bg)
                    radio3.borderColor = Color.WHITE
                }
            }
        }
    }

}