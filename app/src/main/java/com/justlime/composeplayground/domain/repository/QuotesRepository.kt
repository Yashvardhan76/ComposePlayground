package com.justlime.composeplayground.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.justlime.composeplayground.data.network.api.QuotesApi
import com.justlime.composeplayground.data.paging.QuotesPagingSource
import javax.inject.Inject

class QuotesRepository @Inject constructor(val api: QuotesApi) {

}