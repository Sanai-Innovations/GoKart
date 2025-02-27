package com.sanai.gokart.data.repository.datasource.datasourceImpl

import com.sanai.gokart.data.api.services.ProductService
import com.sanai.gokart.data.models.response.product_detail.ProductDetailResponse
import com.sanai.gokart.data.repository.datasource.ProductRemoteDataSource
import retrofit2.Response

class ProductRemoteDataSourceImpl(private val productService: ProductService) :
    ProductRemoteDataSource {

    override suspend fun addToWishList(userId: Int, productId: Int): Response<Boolean> {
        return productService.addToWishList(userId, productId)
    }

    override suspend fun getProductDetails(productId: Int): Response<ProductDetailResponse> {
        return productService.getProductDetails(productId)
    }

    override suspend fun removeFromWishList(userId: Int, productId: Int): Response<Boolean> {
        return productService.removeFromWishList(userId, productId)
    }
}