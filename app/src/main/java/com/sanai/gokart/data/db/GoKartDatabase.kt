package com.sanai.gokart.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanai.gokart.data.db.dao.CartDAO
import com.sanai.gokart.data.models.response.cart.CartProductItem

@Database(entities = [CartProductItem::class], exportSchema = false, version = 1)
abstract class GoKartDatabase : RoomDatabase() {

    abstract fun getCartDao(): CartDAO
}