package com.justlime.composeplayground.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justlime.composeplayground.data.network.api.QuotesApi
import com.justlime.composeplayground.domain.model.Quote
import com.justlime.composeplayground.domain.repository.QuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    val repository: QuotesRepository,
    val api: QuotesApi
) : ViewModel() {
    private val _quotes = MutableStateFlow<List<Quote>>(emptyList())
    val quotes: StateFlow<List<Quote>> = _quotes

    init {
        viewModelScope.launch {
            _quotes.value = repository.fetchQuotes(api) // ✅ Fetch inside coroutine
        }
    }

}