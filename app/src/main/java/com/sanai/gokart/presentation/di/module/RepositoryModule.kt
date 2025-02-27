package com.sanai.gokart.presentation.di.module

import com.sanai.gokart.data.repository.datasource.DashboardRemoteDataSource
import com.sanai.gokart.data.repository.datasource.LoginRemoteDataSource
import com.sanai.gokart.data.repository.datasource.ProductRemoteDataSource
import com.sanai.gokart.data.repository.repositoryImpl.DashboardRepositoryImpl
import com.sanai.gokart.data.repository.repositoryImpl.LoginRepositoryImpl
import com.sanai.gokart.data.repository.repositoryImpl.ProductRepositoryImpl
import com.sanai.gokart.domain.repository.DashboardRepository
import com.sanai.gokart.domain.repository.LoginRepository
import com.sanai.gokart.domain.repository.ProductRepository
import com.sanai.gokart.presentation.util.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesLoginRepository(
        appPreferences: AppPreferences,
        loginRemoteDataSource: LoginRemoteDataSource
    ): LoginRepository {
        return LoginRepositoryImpl(appPreferences, loginRemoteDataSource)
    }

    @Singleton
    @Provides
    fun providesDashboardRepository(dashboardRemoteDataSource: DashboardRemoteDataSource): DashboardRepository {
        return DashboardRepositoryImpl(dashboardRemoteDataSource)
    }

    @Singleton
    @Provides
    fun providesProductRepository(
        appPreferences: AppPreferences,
        productRemoteDataSource: ProductRemoteDataSource
    ): ProductRepository {
        return ProductRepositoryImpl(appPreferences, productRemoteDataSource)
    }
}