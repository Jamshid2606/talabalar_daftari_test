package com.student.daftari.usecase

import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.student.daftari.data.model.User
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
        fun loginWithPhone(phone:String,name:String): Flow<Result<String>>
        fun loginWithGoogle(account: GoogleSignInAccount): Flow<Result<Unit>>
        fun loginWithFacebook(accessToken: AccessToken): Flow<Result<Unit>>
        fun loginWithTwitter(): Flow<Result<Unit>>
        fun createAccount(user: User): Flow<Result<Unit>>
}