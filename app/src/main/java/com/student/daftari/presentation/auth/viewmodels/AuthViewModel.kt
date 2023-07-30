package com.student.daftari.presentation.auth.viewmodels

import androidx.lifecycle.MutableLiveData
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.student.daftari.data.model.User

interface AuthViewModel {
    val errorLiveData:MutableLiveData<String>
    val successLiveData:MutableLiveData<Unit>
    val progressLiveData:MutableLiveData<Boolean>
    val accountLiveData:MutableLiveData<String>
    fun createAccount(user:User)
    fun loginWithPhoneName(phone:String,name:String)
    fun googleSign(googleSignIn: GoogleSignInAccount)
    fun loginTwitter()
    fun loginFaceBook(accessToken: AccessToken)


}