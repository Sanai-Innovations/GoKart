package com.sanai.gokart.data.api.services

import com.sanai.gokart.data.models.response.product_detail.ProductDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("/v1/product/wishlist/add")
    suspend fun addToWishList(
        @Query("userId") userId: Int,
        @Query("productId") productId: Int
    ): Response<Boolean>

    @GET("/v1/product/wishlist/remove")
    suspend fun removeFromWishList(
        @Query("userId") userId: Int,
        @Query("productId") productId: Int
    ): Response<Boolean>

    @GET("/v1/product/cart/add")
    suspend fun addToCart(
        @Query("userId") userId: Int,
        @Query("productId") productId: Int
    ): Response<Boolean>

    @GET("/v1/product/cart/remove")
    suspend fun removeFromCart(
        @Query("userId") userId: Int,
        @Query("productId") productId: Int
    ): Response<Boolean>

    @GET("/v1/product/details")
    suspend fun getProductDetails(@Query("productId") productId: Int): Response<ProductDetailResponse>
}