package com.sanai.gokart.presentation.di.module

import android.app.Application
import androidx.room.Room
import com.sanai.gokart.data.db.GoKartDatabase
import com.sanai.gokart.data.db.dao.CartDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(application: Application): GoKartDatabase {
        return Room.databaseBuilder(application, GoKartDatabase::class.java, "go_kart_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesCartDao(goKartDatabase: GoKartDatabase): CartDAO {
        return goKartDatabase.getCartDao()
    }
}