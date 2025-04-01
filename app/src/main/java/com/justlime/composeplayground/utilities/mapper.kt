package com.justlime.composeplayground.utilities

import com.justlime.composeplayground.data.local.entity.QuotesEntity
import com.justlime.composeplayground.data.network.dto.zenquotes.api.quotes.QuotesDto
import com.justlime.composeplayground.domain.model.Quote

fun QuotesDto.toQuoteEntity(): QuotesEntity {
    return QuotesEntity(
        quote = this.q,
        author = this.a
    )
}

fun QuotesEntity.toQuote(): Quote {
    return Quote(
        id = this.id,
        quote = this.quote,
        author = this.author
    )
}