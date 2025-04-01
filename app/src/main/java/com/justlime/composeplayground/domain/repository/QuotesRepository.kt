package com.justlime.composeplayground.domain.repository

import com.justlime.composeplayground.data.network.api.QuotesApi
import com.justlime.composeplayground.domain.model.Quote
import com.justlime.composeplayground.utilities.toQuote
import com.justlime.composeplayground.utilities.toQuoteEntity
import javax.inject.Inject

class QuotesRepository @Inject constructor(val api: QuotesApi) {
    suspend fun fetchQuotes(api: QuotesApi): List<Quote> {
        return try {
            api.getQuotes()
                .map { it.toQuoteEntity().toQuote() } // ✅ No Call<> wrapper, direct response
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList() // Return empty list on failure
        }
    }
}