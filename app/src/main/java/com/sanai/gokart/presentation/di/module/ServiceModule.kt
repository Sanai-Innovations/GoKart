package com.sanai.gokart.presentation.di.module

import com.sanai.gokart.data.api.services.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Singleton
    @Provides
    fun providesNewsService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }
}