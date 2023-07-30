package com.student.daftari.usecase.imp

import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.student.daftari.data.model.User
import com.student.daftari.repository.AuthRepository
import com.student.daftari.usecase.AuthUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCaseImp @Inject constructor(private val repository: AuthRepository) :AuthUseCase{
    override fun loginWithPhone(phone: String, name: String): Flow<Result<String>> {
        return repository.loginWithPhone(phone, name)
    }

    override fun loginWithGoogle(account: GoogleSignInAccount): Flow<Result<Unit>> {
        return repository.loginWithGoogle(account)
    }

    override fun loginWithFacebook(accessToken: AccessToken): Flow<Result<Unit>> {
        return  repository.loginWithFacebook(accessToken)
    }

    override fun loginWithTwitter(): Flow<Result<Unit>> {
        return repository.loginWithTwitter()
    }

    override fun createAccount(user: User): Flow<Result<Unit>> {
       return repository.createAccount(user)
    }
}