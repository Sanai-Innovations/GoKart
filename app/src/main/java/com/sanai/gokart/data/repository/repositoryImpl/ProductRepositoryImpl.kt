package com.sanai.gokart.data.repository.repositoryImpl

import com.sanai.gokart.data.models.response.cart.CartProductItem
import com.sanai.gokart.data.models.response.product_detail.ProductDetailResponse
import com.sanai.gokart.data.repository.BaseApiResponse
import com.sanai.gokart.data.repository.datasource.product.ProductLocalDataSource
import com.sanai.gokart.data.repository.datasource.product.ProductRemoteDataSource
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.domain.repository.ProductRepository
import com.sanai.gokart.presentation.util.AppPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class ProductRepositoryImpl(
    private val sharedPrefs: AppPreferences,
    private val productLocalDataSource: ProductLocalDataSource,
    private val productRemoteDataSource: ProductRemoteDataSource
) : BaseApiResponse(), ProductRepository {

    override suspend fun getProductDetail(productId: Int): Resource<ProductDetailResponse> {
        return safeApiCall { productRemoteDataSource.getProductDetails(productId) }
    }

    override suspend fun getProductsFromCart(): Flow<Resource<List<CartProductItem>>> {
        val data = productLocalDataSource.getProductsFromCart()
        return flow {
            emit(Resource.Success(data.first()))
        }
    }

    override suspend fun addProductToCart(product: ProductDetailResponse): Resource<Boolean> {
        val result =
            safeApiCall { productRemoteDataSource.addToCart(sharedPrefs.getUserId(), product.id) }

        // if the product is added to the cart successfully, then add it to the local database
        if (result.data == true) {
            productLocalDataSource.saveProductToCart(
                CartProductItem(
                    productId = product.id,
                    thumbnail = product.imageUrl,
                    quantity = 1,
                    marketPrice = product.marketPrice,
                    finalPrice = product.finalPrice,
                    title = product.title,
                    discount = product.discount
                )
            )
        }
        return result
    }

    override suspend fun removeProductFromCart(productId: Int): Resource<Boolean> {
        return safeApiCall {
            productRemoteDataSource.removeFromCart(sharedPrefs.getUserId(), productId)
        }
    }

    override suspend fun addProductToWishList(productId: Int): Resource<Boolean> {
        return safeApiCall {
            productRemoteDataSource.addToWishList(sharedPrefs.getUserId(), productId)
        }
    }

    override suspend fun removeProductFromWishList(productId: Int): Resource<Boolean> {
        return safeApiCall {
            productRemoteDataSource.removeFromWishList(sharedPrefs.getUserId(), productId)
        }
    }
}