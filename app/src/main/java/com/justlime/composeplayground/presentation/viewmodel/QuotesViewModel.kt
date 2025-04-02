package com.justlime.composeplayground.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.justlime.composeplayground.data.network.api.QuotesApi
import com.justlime.composeplayground.data.paging.QuotesPagingSource
import com.justlime.composeplayground.domain.repository.QuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val api: QuotesApi,
    private val repository: QuotesRepository
) : ViewModel() {

    // Using Paging 3 with Flow
    val pager = Pager(PagingConfig(pageSize = 20)) {
        QuotesPagingSource(api, 10)
    }.flow.cachedIn(viewModelScope)

}
