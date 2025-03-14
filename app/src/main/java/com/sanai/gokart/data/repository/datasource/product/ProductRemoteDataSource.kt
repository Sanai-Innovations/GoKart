package com.sanai.gokart.data.repository.datasource.product

import com.sanai.gokart.data.models.response.product_detail.ProductDetailResponse
import retrofit2.Response

interface ProductRemoteDataSource {

    suspend fun addToWishList(userId: Int, productId: Int): Response<Boolean>
    suspend fun getProductDetails(productId: Int): Response<ProductDetailResponse>
    suspend fun removeFromWishList(userId: Int, productId: Int): Response<Boolean>

    // cart
    suspend fun addToCart(userId: Int, productId: Int): Response<Boolean>
    suspend fun removeFromCart(userId: Int, productId: Int): Response<Boolean>
}