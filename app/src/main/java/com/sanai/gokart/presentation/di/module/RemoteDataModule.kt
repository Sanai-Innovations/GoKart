package com.sanai.gokart.presentation.di.module

import com.sanai.gokart.data.api.services.DashboardService
import com.sanai.gokart.data.api.services.LoginService
import com.sanai.gokart.data.api.services.ProductService
import com.sanai.gokart.data.repository.datasource.DashboardRemoteDataSource
import com.sanai.gokart.data.repository.datasource.LoginRemoteDataSource
import com.sanai.gokart.data.repository.datasource.ProductRemoteDataSource
import com.sanai.gokart.data.repository.datasource.datasourceImpl.DashboardRemoteDataSourceImpl
import com.sanai.gokart.data.repository.datasource.datasourceImpl.LoginRemoteDataSourceImpl
import com.sanai.gokart.data.repository.datasource.datasourceImpl.ProductRemoteDataSourceImpl
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

    @Provides
    @Singleton
    fun providesDashboardRemoteDataSource(dashboardService: DashboardService): DashboardRemoteDataSource {
        return DashboardRemoteDataSourceImpl(dashboardService)
    }

    @Provides
    @Singleton
    fun providesProductRemoteDataSource(productService: ProductService): ProductRemoteDataSource {
        return ProductRemoteDataSourceImpl(productService)
    }
}


