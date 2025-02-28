package com.sanai.gokart.data.repository.repositoryImpl

import com.sanai.gokart.data.models.response.product_detail.ProductDetailResponse
import com.sanai.gokart.data.repository.BaseApiResponse
import com.sanai.gokart.data.repository.datasource.ProductRemoteDataSource
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.domain.repository.ProductRepository
import com.sanai.gokart.presentation.util.AppPreferences

class ProductRepositoryImpl(
    private val sharedPrefs: AppPreferences,
    private val productRemoteDataSource: ProductRemoteDataSource
) : BaseApiResponse(), ProductRepository {

    override suspend fun getProductDetail(productId: Int): Resource<ProductDetailResponse> {
        return safeApiCall { productRemoteDataSource.getProductDetails(productId) }
    }

    override suspend fun addProductToCart(productId: Int): Resource<Boolean> {
        return safeApiCall { productRemoteDataSource.addToCart(sharedPrefs.getUserId(), productId) }
    }

    override suspend fun removeProductFromCart(productId: Int): Resource<Boolean> {
        return safeApiCall {
            productRemoteDataSource.removeFromCart(
                sharedPrefs.getUserId(),
                productId
            )
        }
    }

    override suspend fun addProductToWishList(productId: Int): Resource<Boolean> {
        return safeApiCall {
            productRemoteDataSource.addToWishList(
                sharedPrefs.getUserId(),
                productId
            )
        }
    }

    override suspend fun removeProductFromWishList(productId: Int): Resource<Boolean> {
        return safeApiCall {
            productRemoteDataSource.removeFromWishList(
                sharedPrefs.getUserId(),
                productId
            )
        }
    }
}