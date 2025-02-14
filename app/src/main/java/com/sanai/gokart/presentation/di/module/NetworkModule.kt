package com.sanai.gokart.presentation.di.module

import com.sanai.gokart.BuildConfig
import com.sanai.gokart.data.api.interceptor.MockAPIInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        logging: HttpLoggingInterceptor,
        mockAPIInterceptor: MockAPIInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder().addInterceptor(logging)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(mockAPIInterceptor)
        }
        val client: OkHttpClient = builder.build()
        return client
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}