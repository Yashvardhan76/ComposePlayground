package com.justlime.composeplayground.data.network.api

import com.justlime.composeplayground.data.network.dto.zenquotes.api.quotes.QuotesDto
import retrofit2.Call
import retrofit2.http.GET

interface QuotesApi {

    companion object {
        val baseUrl: String = "https://zenquotes.io/api/"
    }

    @GET("quotes")
    suspend fun getQuotes(): List<QuotesDto>

}