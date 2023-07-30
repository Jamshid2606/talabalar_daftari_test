package com.student.daftari.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.student.daftari.repository.AuthRepository
import com.student.daftari.repository.VerifyRepository
import com.student.daftari.repository.imp.AuthRepositoryImp
import com.student.daftari.repository.imp.VerifyRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun getAuthRepository(impl: AuthRepositoryImp): AuthRepository

    @Binds
    fun getVerifyRepo(impl: VerifyRepositoryImp): VerifyRepository



}