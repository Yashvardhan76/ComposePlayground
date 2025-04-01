package com.justlime.composeplayground.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuotesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val quote: String,
    val author: String
)
