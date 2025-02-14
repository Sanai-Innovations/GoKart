package com.sanai.gokart.presentation.di.module

import com.sanai.gokart.data.api.services.LoginService
import com.sanai.gokart.data.repository.datasource.LoginRemoteDataSource
import com.sanai.gokart.data.repository.datasourceImpl.LoginRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Provides
    @Singleton
    fun providesLoginRemoteDataSource(loginService: LoginService): LoginRemoteDataSource {
        return LoginRemoteDataSourceImpl(loginService)
    }
}


