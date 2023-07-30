package com.student.daftari.viewmodels.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.daftari.repository.VerifyRepository
import com.student.daftari.utils.hasConnection
import com.student.daftari.viewmodels.VerifyScreenViewModel
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VerifyScreenViewModelImp @Inject constructor(private val repo:VerifyRepository):ViewModel(),VerifyScreenViewModel {
    override val successLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val errorLiveData: MutableLiveData<String> = MutableLiveData()
    override val resendDataLiveData: MutableLiveData<String> = MutableLiveData()

    override fun verifyCode(code: String, uid: String) {
     if (hasConnection()){
         repo.verifyCode(code,uid).onEach {
             it.onSuccess {
                 successLiveData.value=it
             }
             it.onFailure {
                 errorLiveData.value=it.message
             }
         }.launchIn(viewModelScope)
     }else{
         errorLiveData.value="No Internet"
     }
    }

    override fun resendCode(phone: String) {
        repo.resendCode(phone).onEach {
            it.onFailure {
                errorLiveData.value=it.message
            }
            it.onSuccess {
                resendDataLiveData.value=it
            }
        }.launchIn(viewModelScope)
    }
}