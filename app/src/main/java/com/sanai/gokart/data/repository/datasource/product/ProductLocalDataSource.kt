package com.sanai.gokart.data.repository.datasource.product

import com.sanai.gokart.data.models.response.cart.CartProductItem
import com.sanai.gokart.data.models.response.wishlist.WishListProductItem
import kotlinx.coroutines.flow.Flow

interface ProductLocalDataSource {

    // wishlist
    suspend fun saveProductToWishList(wishListItem: WishListProductItem)
    suspend fun getProductsFromWishList(): Flow<List<WishListProductItem>>
    suspend fun removeProductFromWishList(wishListProductItem: WishListProductItem)

    // cart
    suspend fun saveProductToCart(product: CartProductItem)
    suspend fun removeProductFromCart(product: CartProductItem)
    suspend fun getProductsFromCart(): Flow<List<CartProductItem>>
}