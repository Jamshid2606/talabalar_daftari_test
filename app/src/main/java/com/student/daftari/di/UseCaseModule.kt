package com.student.daftari.di

import com.student.daftari.repository.AuthRepository
import com.student.daftari.repository.imp.AuthRepositoryImp
import com.student.daftari.usecase.AuthUseCase
import com.student.daftari.usecase.imp.AuthUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindLoginScreenUseCase(impl: AuthUseCaseImp): AuthUseCase

}