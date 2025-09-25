package com.ramanshsharma07.ecommerce.feature_search.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramanshsharma07.ecommerce.feature_products.presentation.view.ProductGridItem
import com.ramanshsharma07.ecommerce.feature_search.presentation.viewmodel.SearchEvent
import com.ramanshsharma07.ecommerce.feature_search.presentation.viewmodel.SearchViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen(
    onNavigateToDetails: (productId: String) -> Unit,
    padding: PaddingValues
) {
    val viewModel: SearchViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }

    // Request focus when the screen is first composed
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        SearchBar(
            query = state.searchQuery,
            onQueryChange = { viewModel.onEvent(SearchEvent.OnQueryChange(it)) },
            onClearQuery = { viewModel.onEvent(SearchEvent.OnClearQuery) },
            focusRequester = focusRequester
        )

        Spacer(modifier = Modifier.height(16.dp))

        when {
            state.searchQuery.isBlank() -> {
                EmptySearchState()
            }

            state.isSearching -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.products.isEmpty() -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No results found for \"${state.searchQuery}\"")
                }
            }

            else -> {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Results for \"${state.searchQuery}\"",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            "${state.products.size} Results Found",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.products) { product ->
                            ProductGridItem(
                                product = product,
                                onNavigateToDetails = { onNavigateToDetails(product.id) },
                                onAddToCartClick = {
                                    viewModel.onEvent(
                                        SearchEvent.OnAddToCartClick(
                                            product.id
                                        )
                                    )
                                },
                                onFavoriteClick = {
                                    viewModel.onEvent(
                                        SearchEvent.OnFavoriteClick(
                                            product.id
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}