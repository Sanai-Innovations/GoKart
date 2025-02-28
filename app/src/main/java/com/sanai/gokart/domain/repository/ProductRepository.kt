package com.sanai.gokart.domain.repository

import com.sanai.gokart.data.models.response.product_detail.ProductDetailResponse
import com.sanai.gokart.data.util.Resource

interface ProductRepository {

    suspend fun addProductToWishList(productId: Int): Resource<Boolean>
    suspend fun removeProductFromWishList(productId: Int): Resource<Boolean>
    suspend fun getProductDetail(productId: Int): Resource<ProductDetailResponse>

    // cart
    suspend fun addProductToCart(productId: Int): Resource<Boolean>
    suspend fun removeProductFromCart(productId: Int): Resource<Boolean>
}
