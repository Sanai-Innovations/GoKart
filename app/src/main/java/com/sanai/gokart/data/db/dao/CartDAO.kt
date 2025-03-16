package com.sanai.gokart.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sanai.gokart.data.models.response.cart.CartProductItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductInCart(product: CartProductItem)

    @Query("SELECT * FROM cart_table")
    fun getProductsFromCart(): Flow<List<CartProductItem>>

    @Delete
    fun deleteProductFromCart(product: CartProductItem)
}