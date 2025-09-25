package com.ramanshsharma07.ecommerce.feature_search.presentation.viewmodel

import com.ramanshsharma07.ecommerce.core.domain.model.Product
import com.ramanshsharma07.ecommerce.core.presentation.UiText

data class SearchState(
    val searchQuery: String = "",
    val isSearching: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: UiText? = null
)
