package com.sanai.gokart.data.repository.datasource.product

import com.sanai.gokart.data.models.response.product_detail.ProductDetailResponse
import retrofit2.Response

interface ProductLocalDataSource {

    // wishlist
    suspend fun addProductToWishList(userId: Int, productId: Int): Response<Boolean>
    suspend fun getProductsFromWishList(): Response<ProductDetailResponse>
    suspend fun removeProductFromWishList(userId: Int, productId: Int): Response<Boolean>

    // cart
    suspend fun addProductToCart(productId: Int): Response<Boolean>
    suspend fun getProductsFromCart(): Response<Boolean>
    suspend fun removeProductFromCart(productId: Int): Response<Boolean>
}