package com.student.daftari.presentation.auth.viewmodels.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.student.daftari.data.model.User
import com.student.daftari.presentation.auth.viewmodels.AuthViewModel
import com.student.daftari.usecase.imp.AuthUseCaseImp
import com.student.daftari.utils.hasConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModelImp @Inject constructor(
    private val useCase: AuthUseCaseImp
) : ViewModel(), AuthViewModel {
    override val errorLiveData: MutableLiveData<String> = MutableLiveData()
    override val successLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override val accountLiveData: MutableLiveData<String> = MutableLiveData()

    override fun createAccount(user: User) {
        if (hasConnection()) {
            progressLiveData.value = true
            useCase.createAccount(user).onEach {
                it.onSuccess {
                    progressLiveData.value = false
                    successLiveData.value = it
                }

                it.onFailure {
                    progressLiveData.value = false
                    errorLiveData.value = it.message
                }
            }.launchIn(viewModelScope)
        } else {
            errorLiveData.value = "No internet"
        }
    }

    override fun loginWithPhoneName(phone: String, name: String) {
        if (hasConnection()) {
            progressLiveData.value = true
            useCase.loginWithPhone(phone, name).onEach { it ->
                it.onSuccess {
                    progressLiveData.value = false
                    accountLiveData.value = it
                }
                it.onFailure {
                    progressLiveData.value = false
                    errorLiveData.value = it.message
                }
            }.launchIn(viewModelScope)
        } else {
            errorLiveData.value = "No Connection :("
        }
    }

    override fun googleSign(googleSignIn: GoogleSignInAccount) {
        if (hasConnection()) {
            useCase.loginWithGoogle(googleSignIn).onEach {
                it.onSuccess { success ->
                    println("Tushdi :)d")
                    this.successLiveData.value = success
                }
                it.onFailure { error ->
                    println("XATO  ::::::::::::${error.message.toString()}")
                    errorLiveData.value = error.message
                }
            }.launchIn(viewModelScope)
        }
    }

    override fun loginTwitter() {
        if (hasConnection()) {
            progressLiveData.value = true
            useCase.loginWithTwitter().onEach {
                it.onSuccess {
                    this.successLiveData.value = it

                }
                it.onFailure {
                    errorLiveData.value = it.message
                }
            }.launchIn(viewModelScope)
        } else {
            errorLiveData.value = "No Internet :("
        }
    }

    override fun loginFaceBook(accessToken: AccessToken) {
        if (hasConnection()) {
            progressLiveData.value = true
            useCase.loginWithFacebook(accessToken).onEach {
                it.onSuccess {
                    progressLiveData.value = false
                    this.successLiveData.value = it
                }
                it.onFailure {
                    progressLiveData.value = false
                    errorLiveData.value = it.message
                }
            }.launchIn(viewModelScope)
        } else {
            errorLiveData.value = "No Connection :("
        }
    }


}