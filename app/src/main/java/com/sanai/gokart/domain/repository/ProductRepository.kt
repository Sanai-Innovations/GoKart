package com.sanai.gokart.domain.repository

import com.sanai.gokart.data.models.response.cart.CartProductItem
import com.sanai.gokart.data.models.response.product_detail.ProductDetailResponse
import com.sanai.gokart.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun addProductToWishList(productId: Int): Resource<Boolean>
    suspend fun removeProductFromWishList(productId: Int): Resource<Boolean>
    suspend fun getProductDetail(productId: Int): Resource<ProductDetailResponse>

    // cart
    suspend fun removeProductFromCart(productId: Int): Resource<Boolean>
    suspend fun getProductsFromCart(): Flow<Resource<List<CartProductItem>>>
    suspend fun addProductToCart(product: ProductDetailResponse): Resource<Boolean>
}
