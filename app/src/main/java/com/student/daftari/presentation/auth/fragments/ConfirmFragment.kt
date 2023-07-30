package com.student.daftari.presentation.auth.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher
import com.student.daftari.R
import com.student.daftari.data.model.User
import com.student.daftari.databinding.FragmentConfirmBinding
import com.student.daftari.utils.parseCode
import com.student.daftari.utils.showToast
import com.student.daftari.utils.visible
import com.student.daftari.viewmodels.imp.VerifyScreenViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_confirm.*
import kotlinx.coroutines.delay


@AndroidEntryPoint
class ConfirmFragment : BaseFragment<FragmentConfirmBinding>(FragmentConfirmBinding::inflate) {
    private val viewModel by viewModels<VerifyScreenViewModelImp>()
    private val timeLiveData: MutableLiveData<Int> = MutableLiveData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timeLiveData.observe(this, timeObserver)
        viewModel.successLiveData.observe(this, successObserver)
        viewModel.errorLiveData.observe(this, errorObserver)
        viewModel.resendDataLiveData.observe(this, resendObserver)
    }

    override fun onViewCreate() {
        val data = requireArguments().getString("uid")
        initResendCode()
        initAutoReadSms()
        tasdiqlash.setOnClickListener {
            viewModel.verifyCode(code.text.toString(), data.toString())
        }
        code.addTextChangedListener {
            if (it.toString().length == 6) {
                viewModel.verifyCode(code.text.toString(), data.toString())
            }
        }
    }

    private fun initAutoReadSms() {

    }

    private fun initResendCode() {
        val data = requireArguments().getSerializable("data") as User
        var count = 50
        lifecycleScope.launchWhenResumed {
            while (count > 0) {
                delay(1000)
                count -= 1
                timeLiveData.value = count
            }
        }
        resendCode.setOnClickListener {
            viewModel.resendCode(data.phoneNumber)
        }
    }

    private val successObserver = Observer<Unit> {
        val bundle = Bundle()
        bundle.putSerializable("data", requireArguments().getSerializable("data")!!)
        findNavController().navigate(R.id.createAccountFragment, bundle)
    }

    private val errorObserver = Observer<String> {
        showToast(it)
    }

    private val resendObserver = Observer<String> {
        showToast("Kod Qayta Yuborildi !")
        initResendCode()
    }

    private val timeObserver = Observer<Int> {
        time.text = it.toString()
        if (it == 0) {
            resendCode.visible()
        }
    }
}