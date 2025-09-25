package com.ramanshsharma07.ecommerce.feature_search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramanshsharma07.ecommerce.core.domain.Result
import com.ramanshsharma07.ecommerce.core.domain.map
import com.ramanshsharma07.ecommerce.core.domain.onSuccess
import com.ramanshsharma07.ecommerce.core.domain.use_cases.GetFavoritesIdsUseCase
import com.ramanshsharma07.ecommerce.core.domain.use_cases.ToggleFavoriteUseCase
import com.ramanshsharma07.ecommerce.feature_cart.domain.use_cases.AddProductToCartUseCase
import com.ramanshsharma07.ecommerce.feature_search.domain.use_cases.SearchProductsUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchViewModel(
    private val searchProductsUseCase: SearchProductsUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val getFavoritesIdsUseCase: GetFavoritesIdsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    // 1. A private flow to only hold the user's raw query
    private val _searchQuery = MutableStateFlow("")

    // 2. A flow that debounces the query and triggers the search
    private val _searchResults = _searchQuery
        .debounce(500L)
        .flatMapLatest { query ->
            if (query.isBlank()) {
                flowOf(Result.Success(emptyList()))
            } else {
                flowOf(searchProductsUseCase(query))
            }
        }

    // 3. The final public state, which combines search results and favorites
    val state = combine(
        _searchQuery,
        _searchResults,
        getFavoritesIdsUseCase()
    ) { query, result, favoriteIds ->
        result.map { products ->
            products.map { product ->
                product.copy(isFavorite = favoriteIds.contains(product.id))
            }
        }.let { finalResult ->
            SearchState(
                searchQuery = query,
                products = if (finalResult is Result.Success) finalResult.data else emptyList(),
                isSearching = false // We don't need a separate searching flag with this pattern
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SearchState())

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnQueryChange -> {
                _searchQuery.value = event.query
            }
            SearchEvent.OnClearQuery -> {
                _searchQuery.value = ""
            }
            is SearchEvent.OnAddToCartClick -> {
                viewModelScope.launch { addProductToCartUseCase(event.productId) }
            }
            is SearchEvent.OnFavoriteClick -> {
                viewModelScope.launch { toggleFavoriteUseCase(event.productId) }
            }
        }
    }
}