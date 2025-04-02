package com.justlime.composeplayground.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.justlime.composeplayground.data.network.api.QuotesApi
import com.justlime.composeplayground.domain.model.Quote
import com.justlime.composeplayground.utilities.toQuote
import com.justlime.composeplayground.utilities.toQuoteEntity
import retrofit2.HttpException
import java.io.IOException

class QuotesPagingSource(
    private val api: QuotesApi,
    private val pageSize: Int
) : PagingSource<Int, Quote>() {

    private var cachedQuotes: List<Quote>? = null  // Cache data in memory

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {
        return try {
            val page = params.key ?: 1

            // Fetch all quotes only if not cached
            if (cachedQuotes == null) {
                cachedQuotes = api.getQuotes().map { it.toQuoteEntity().toQuote() }
            }

            val quotes = cachedQuotes ?: emptyList()
            val fromIndex = (page - 1) * pageSize
            val toIndex = (fromIndex + pageSize).coerceAtMost(quotes.size)

            if (fromIndex >= quotes.size) {
                return LoadResult.Page(emptyList(), null, null)
            }

            LoadResult.Page(
                data = quotes.subList(fromIndex, toIndex),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (toIndex >= quotes.size) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            if (e.code() == 429) {
                return LoadResult.Error(Exception("Rate limit exceeded. Please try again later."))
            }
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Quote>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
