package com.sanai.gokart.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sanai.gokart.data.models.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(product: Product)

    @Query("SELECT * FROM product_table")
    fun getProducts(): Flow<List<Product>>
}