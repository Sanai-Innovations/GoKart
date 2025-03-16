package com.sanai.gokart.data.repository.datasource.datasourceImpl.product

import com.sanai.gokart.data.db.dao.CartDAO
import com.sanai.gokart.data.models.response.cart.CartProductItem
import com.sanai.gokart.data.models.response.wishlist.WishListProductItem
import com.sanai.gokart.data.repository.datasource.product.ProductLocalDataSource
import com.sanai.gokart.presentation.util.Logger
import kotlinx.coroutines.flow.Flow

class ProductLocalDataSourceImpl(private val cartDAO: CartDAO) : ProductLocalDataSource {

    override suspend fun saveProductToWishList(wishListItem: WishListProductItem) {
        TODO("Not yet implemented")
    }

    override suspend fun getProductsFromWishList(): Flow<List<WishListProductItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun removeProductFromWishList(wishListProductItem: WishListProductItem) {
        TODO("Not yet implemented")
    }

    override suspend fun saveProductToCart(product: CartProductItem) {
        Logger.d("ProductLocalDataSourceImpl saveProductToCart: $product")
        cartDAO.insertProductInCart(product)
    }

    override suspend fun removeProductFromCart(product: CartProductItem) {
        cartDAO.deleteProductFromCart(product)
    }

    override suspend fun getProductsFromCart(): Flow<List<CartProductItem>> {
        return cartDAO.getProductsFromCart()
    }

}