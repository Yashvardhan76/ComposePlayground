package com.justlime.composeplayground.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.justlime.composeplayground.domain.model.Quote
import com.justlime.composeplayground.presentation.viewmodel.QuotesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: QuotesViewModel = hiltViewModel()
) {
    val quotes = viewModel.pager.collectAsLazyPagingItems()
    val refreshScope = rememberCoroutineScope()
    val isFirstTimeLoad = rememberSaveable { mutableStateOf(true) }
    val isRefreshing = quotes.loadState.refresh is LoadState.Loading && !isFirstTimeLoad.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                Text("Quote Screen", fontSize = 24.sp)
                Spacer(modifier = Modifier.height(12.dp))
            }


        }
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PullToRefreshBox(
                isRefreshing = isRefreshing,
                onRefresh = {
                    refreshScope.launch {
                        quotes.refresh()
                    }
                }
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    if (quotes.loadState.refresh is LoadState.Loading) {
                        item {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                CircularProgressIndicator()
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Loading...")
                            }
                        }
                    } else {
                        isFirstTimeLoad.value = false
                        items(quotes.itemCount) { index ->
                            QuoteCard(quote = quotes[index]!!)
                        }
                    }
                }
            }
        }


    }
}


@Composable
fun QuoteCard(quote: Quote) {
    Card(
        modifier = Modifier.padding(16.dp, 6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Row {
                Text(text = quote.quote)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "~${quote.author}")
            }
        }
    }
}


@Composable
@Preview
fun QuoteScreenPreview() {
    QuoteScreen(
        navController = NavController(LocalContext.current),
        paddingValues = PaddingValues()
    )
}