package com.sanai.gokart.data.repository.datasource.datasourceImpl.product

import com.sanai.gokart.data.models.response.product_detail.ProductDetailResponse
import com.sanai.gokart.data.repository.datasource.product.ProductLocalDataSource
import retrofit2.Response

class ProductLocalDataSourceImpl() : ProductLocalDataSource {

    override suspend fun addProductToWishList(userId: Int, productId: Int): Response<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductsFromWishList(): Response<ProductDetailResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun removeProductFromWishList(userId: Int, productId: Int): Response<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun addProductToCart(productId: Int): Response<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductsFromCart(): Response<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun removeProductFromCart(productId: Int): Response<Boolean> {
        TODO("Not yet implemented")
    }
}