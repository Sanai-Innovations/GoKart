package com.sanai.gokart.presentation.di.module

import com.sanai.gokart.domain.repository.LoginRepository
import com.sanai.gokart.domain.usecase.LoginUserUseCase
import com.sanai.gokart.domain.usecase.RegisterUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideLoginUseCase(loginRepository: LoginRepository): LoginUserUseCase {
        return LoginUserUseCase(loginRepository)
    }

    @Singleton
    @Provides
    fun provideRegisterUseCase(loginRepository: LoginRepository): RegisterUserUseCase {
        return RegisterUserUseCase(loginRepository)
    }
}