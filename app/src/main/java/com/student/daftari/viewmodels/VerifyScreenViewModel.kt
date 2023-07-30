package com.student.daftari.viewmodels

import androidx.lifecycle.MutableLiveData

interface VerifyScreenViewModel {
    val successLiveData:MutableLiveData<Unit>
    val errorLiveData:MutableLiveData<String>
    val resendDataLiveData:MutableLiveData<String>

    fun verifyCode(code:String,uid:String)
    fun resendCode(phone:String)
}