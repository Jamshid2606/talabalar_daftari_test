package com.student.daftari.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @[Provides Singleton]
    fun getFirebaseAuth() = FirebaseAuth.getInstance()

    @[Provides Singleton]
    fun getFirebaseDatabase() = FirebaseDatabase.getInstance()

    @[Provides Singleton]
    fun getFirebaseFirestore() = FirebaseFirestore.getInstance()

}