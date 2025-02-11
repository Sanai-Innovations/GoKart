package com.sanai.gokart.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanai.gokart.data.db.dao.ProductDAO
import com.sanai.gokart.data.models.Product

@Database(entities = [Product::class], exportSchema = false, version = 1)
abstract class GoKartDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDAO
}