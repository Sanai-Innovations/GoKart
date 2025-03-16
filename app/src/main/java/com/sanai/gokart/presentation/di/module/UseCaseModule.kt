package com.sanai.gokart.presentation.di.module

import com.sanai.gokart.domain.repository.DashboardRepository
import com.sanai.gokart.domain.repository.LoginRepository
import com.sanai.gokart.domain.repository.ProductRepository
import com.sanai.gokart.domain.usecase.DashboardUseCase
import com.sanai.gokart.domain.usecase.LoginUserUseCase
import com.sanai.gokart.domain.usecase.RegisterUserUseCase
import com.sanai.gokart.domain.usecase.product.AddToWishlistUseCase
import com.sanai.gokart.domain.usecase.product.GetCartItemsUseCase
import com.sanai.gokart.domain.usecase.product.GetProductDetailUseCase
import com.sanai.gokart.domain.usecase.product.RemoveFromWishlistUseCase
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

    @Singleton
    @Provides
    fun provideDashboardUseCase(dashboardRepository: DashboardRepository): DashboardUseCase {
        return DashboardUseCase(dashboardRepository)
    }

    @Singleton
    @Provides
    fun provideGetProductDetailUseCase(productRepository: ProductRepository): GetProductDetailUseCase {
        return GetProductDetailUseCase(productRepository)
    }

    @Singleton
    @Provides
    fun provideAddToWishListUseCase(productRepository: ProductRepository): AddToWishlistUseCase {
        return AddToWishlistUseCase(productRepository)
    }

    @Singleton
    @Provides
    fun provideRemoveFromWishListUseCase(productRepository: ProductRepository): RemoveFromWishlistUseCase {
        return RemoveFromWishlistUseCase(productRepository)
    }

    @Singleton
    @Provides
    fun provideGetCartItemsUseCase(productRepository: ProductRepository): GetCartItemsUseCase {
        return GetCartItemsUseCase(productRepository)
    }
}