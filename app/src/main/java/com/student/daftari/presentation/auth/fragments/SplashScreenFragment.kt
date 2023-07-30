package com.student.daftari.presentation.auth.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.CountDownTimer
import com.denzcoskun.imageslider.interfaces.ItemChangeListener
import com.denzcoskun.imageslider.models.SlideModel
import com.student.daftari.R
import com.student.daftari.utils.gone
import com.student.daftari.utils.show
import com.student.daftari.databinding.FragmentSplashScreenBinding
import com.student.daftari.presentation.main.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {
    private lateinit var mCountDownTimer : CountDownTimer
    override fun onViewCreate() {
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.splash_1,))
        imageList.add(SlideModel(R.drawable.splash_2,))
        binding.imageSlider.setImageList(imageList)
        binding.skip.setOnClickListener {
            toMainActivity()
        }
        binding.boshlash.setOnClickListener {
            startTimer()
        }
        binding.imageSlider.setItemChangeListener(object :ItemChangeListener{
            override fun onItemChanged(position: Int) {
                when(position){
                    1-> {
                        binding.boshlash.show()
                    }
                    0-> binding.boshlash.gone()
                }
            }
        })
    }
    private fun startTimer(){
        mCountDownTimer = object : CountDownTimer(2000, 1000) {
            override fun onTick(p0: Long) {}
            override fun onFinish() {
                toMainActivity()
            }
        }.start()
    }
    fun toMainActivity(){
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}