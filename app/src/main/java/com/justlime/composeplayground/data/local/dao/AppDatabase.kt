package com.justlime.composeplayground.data.local.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.justlime.composeplayground.data.local.entity.QuotesEntity

@Database(
    entities = [QuotesEntity::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()
        }
    }
    abstract fun quotesDao(): QuotesDao

}