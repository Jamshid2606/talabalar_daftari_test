package com.student.daftari.repository.imp

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.student.daftari.app.App
import com.student.daftari.app.App.Companion.activity
import com.student.daftari.repository.VerifyRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class VerifyRepositoryImp @Inject constructor(private val auth: FirebaseAuth) : VerifyRepository {
    override fun verifyCode(code: String, id: String) = callbackFlow<Result<Unit>> {
        val credential = PhoneAuthProvider.getCredential(id, code)
        auth.signInWithCredential(credential).addOnCompleteListener(
            activity
        ) { task ->
            if (task.isSuccessful) {
                trySend(Result.success(Unit))
            } else {
                trySend(Result.failure(task.exception!!))
            }
        }
        awaitClose()
    }

    override fun resendCode(phone: String) = callbackFlow<Result<String>> {
        val verificationCallback =
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {}

                override fun onVerificationFailed(e: FirebaseException) {
                    trySend(Result.failure(Exception(e.message)))
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    trySend(Result.success(verificationId))
                }
            }

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone.toString())
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(App.activity)
            .setCallbacks(verificationCallback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        awaitClose()
    }
}