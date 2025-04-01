package com.justlime.composeplayground.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.justlime.composeplayground.data.local.entity.QuotesEntity

@Dao
interface QuotesDao {
    @Query("SELECT * FROM quotesentity")
    suspend fun getQuotes(): List<QuotesEntity>

    @Insert
    suspend fun upsertQuotes(quotes: List<QuotesEntity>)

    @Query("DELETE FROM quotesentity WHERE id IN (:id)")
    suspend fun deleteQuotes(id: List<Int>)

}