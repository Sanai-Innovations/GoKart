package com.sanai.gokart.presentation.di.module

import com.sanai.gokart.data.db.dao.CartDAO
import com.sanai.gokart.data.repository.datasource.datasourceImpl.product.ProductLocalDataSourceImpl
import com.sanai.gokart.data.repository.datasource.product.ProductLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun providesProductLocalDataSource(dao: CartDAO): ProductLocalDataSource {
        return ProductLocalDataSourceImpl(dao)
    }
}


