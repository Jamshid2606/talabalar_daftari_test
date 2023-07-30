package com.student.daftari.repository.imp

import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.student.daftari.app.App
import com.student.daftari.app.App.Companion.activity
import com.student.daftari.data.model.User
import com.student.daftari.repository.AuthRepository
import com.student.daftari.utils.CollectionNames
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : AuthRepository {
    override fun loginWithPhone(phone: String, name: String) = callbackFlow {
        val verificationCallback =
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                }

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
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(App.activity)
            .setCallbacks(verificationCallback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        awaitClose()
    }.flowOn(Dispatchers.IO)


    override fun loginWithGoogle(account: GoogleSignInAccount) = callbackFlow<Result<Unit>> {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener() { task ->
            task.addOnSuccessListener { userData ->
                val collectionRef = db.collection(CollectionNames.users)
                collectionRef.whereEqualTo(
                    "uid",
                    userData.user!!.uid
                ).get().addOnCompleteListener {
                    it.addOnSuccessListener {
                        if (it.isEmpty) {
                            val user = User()
                            user.name = userData.user!!.displayName.toString()
                            user.email = userData.user!!.email.toString()
                            user.uid = userData.user!!.uid
                            user.addTime = System.currentTimeMillis()
                            db.collection(CollectionNames.users).add(user).addOnCompleteListener {
                                trySend(Result.success(Unit))
                            }.addOnFailureListener {
                                trySend(Result.failure(Exception("Xatolik sodir bo`ldi !")))
                            }
                        } else {
                            trySend(Result.success(Unit))
                        }
                    }
                }.addOnFailureListener { error ->
                    trySend(Result.failure(error))
                }

            }
            task.addOnFailureListener {
                trySend(Result.failure(it))
            }
        }
        awaitClose()
    }.flowOn(Dispatchers.IO)

    override fun loginWithFacebook(accessToken: AccessToken) = callbackFlow<Result<Unit>> {
        auth.signInWithCredential(FacebookAuthProvider.getCredential(accessToken.token))
            .addOnSuccessListener { userData ->
                val collectionRef = db.collection(CollectionNames.users)
                collectionRef.whereEqualTo(
                    "uid",
                    userData.user!!.uid
                ).get().addOnCompleteListener { it ->
                    it.addOnSuccessListener {
                        if (it.isEmpty) {
                            val user = User()
                            user.name = userData.user!!.displayName.toString()
                            user.email = userData.user!!.email.toString()
                            user.uid = userData.user!!.uid
                            user.addTime = System.currentTimeMillis()
                            db.collection(CollectionNames.users).add(user).addOnCompleteListener {
                                trySend(Result.success(Unit))
                            }.addOnFailureListener {
                                trySend(Result.failure(Exception("Xatolik sodir bo`ldi !")))
                            }
                        } else {
                            trySend(Result.success(Unit))
                        }
                    }
                }.addOnFailureListener { error ->
                    trySend(Result.failure(error))
                }

            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun loginWithTwitter(): Flow<Result<Unit>> = callbackFlow<Result<Unit>> {
        val provider = OAuthProvider.newBuilder("twitter.com")
        val pendingResultTask = auth.pendingAuthResult
        if (pendingResultTask != null) {
            pendingResultTask
                .addOnSuccessListener { userData ->
                    val collectionRef = db.collection(CollectionNames.users)
                    collectionRef.whereEqualTo(
                        "uid",
                        userData.user!!.uid
                    ).get().addOnCompleteListener {
                        it.addOnSuccessListener {
                            if (it.isEmpty) {
                                val user = User()
                                user.name = userData.user!!.displayName.toString()
                                user.email = userData.user!!.email.toString()
                                user.uid = userData.user!!.uid
                                user.addTime = System.currentTimeMillis()
                                db.collection(CollectionNames.users).add(user)
                                    .addOnCompleteListener {
                                        trySend(Result.success(Unit))
                                    }.addOnFailureListener {
                                        trySend(Result.failure(Exception("Xatolik sodir bo`ldi !")))
                                    }
                            } else {
                                trySend(Result.success(Unit))
                            }
                        }
                    }.addOnFailureListener { error ->
                        trySend(Result.failure(error))
                    }

                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }
        } else {
            auth
                .startActivityForSignInWithProvider(activity, provider.build())
                .addOnSuccessListener { userData ->
                    val collectionRef = db.collection(CollectionNames.users)
                    collectionRef.whereEqualTo(
                        "uid",
                        userData.user!!.uid
                    ).get().addOnCompleteListener {
                        it.addOnSuccessListener {
                            if (it.isEmpty) {
                                val user = User()
                                user.name = userData.user!!.displayName.toString()
                                user.email = userData.user!!.email.toString()
                                user.uid = userData.user!!.uid
                                user.addTime = System.currentTimeMillis()
                                db.collection(CollectionNames.users).add(user)
                                    .addOnCompleteListener {
                                        trySend(Result.success(Unit))
                                    }.addOnFailureListener {
                                        trySend(Result.failure(Exception("Xatolik sodir bo`ldi !")))
                                    }
                            } else {
                                trySend(Result.success(Unit))
                            }
                        }
                    }.addOnFailureListener { error ->
                        trySend(Result.failure(error))
                    }

                }
                .addOnFailureListener {
                    trySend(Result.failure(it))

                }

        }

        awaitClose()
    }

    override fun createAccount(user: User) = callbackFlow<Result<Unit>> {

        val collectionRef = db.collection(CollectionNames.users)
        collectionRef.whereEqualTo(
            "uid",
            auth.currentUser!!.uid
        ).get().addOnCompleteListener {
            it.addOnSuccessListener {
                if (it.isEmpty) {
                    db.collection(CollectionNames.users).add(user)
                        .addOnCompleteListener {
                            trySend(Result.success(Unit))
                        }.addOnFailureListener {
                            trySend(Result.failure(Exception("Xatolik sodir bo`ldi !")))
                        }
                } else {
                    user.uid = auth.currentUser!!.uid
                    db.collection(CollectionNames.users).add(user).addOnCompleteListener {
                        trySend(Result.success(Unit))
                    }.addOnFailureListener {
                        trySend(Result.failure(Exception("Xatolik sodir bo`ldi !")))
                    }
                }
            }
        }
    }
}