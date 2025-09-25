package com.ramanshsharma07.ecommerce.feature_home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramanshsharma07.ecommerce.core.domain.map
import com.ramanshsharma07.ecommerce.core.domain.onError
import com.ramanshsharma07.ecommerce.core.domain.onSuccess
import com.ramanshsharma07.ecommerce.core.domain.use_cases.GetFavoritesIdsUseCase
import com.ramanshsharma07.ecommerce.core.domain.use_cases.ToggleFavoriteUseCase
import com.ramanshsharma07.ecommerce.core.presentation.toUiText
import com.ramanshsharma07.ecommerce.feature_home.domain.use_cases.GetHomePageDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.ramanshsharma07.ecommerce.core.domain.Result

class HomeViewModel(
    private val getHomePageDataUseCase: GetHomePageDataUseCase,
    private val getFavoritesIdsUseCase: GetFavoritesIdsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
): ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
//        loadContent()
        observeHomeContentWithFavorites()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnFavoriteClick -> {
                viewModelScope.launch {
                    toggleFavoriteUseCase(event.productId)
                }
            }
        }
    }

    private fun observeHomeContentWithFavorites() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            // Fetch the home page content once
            val homeResult = getHomePageDataUseCase()
            // Get the continuous flow of favorite IDs
            val favoritesFlow = getFavoritesIdsUseCase()

            // Combine the single result with the favorites flow
            favoritesFlow.combine(flowOf(homeResult)) { favoritedIds, result ->
                // This mapping runs every time favorites change
                result.map { (banners, sections) ->
                    val updatedSections = sections.map { section ->
                        val updatedProducts = section.products.map { product ->
                            product.copy(isFavorite = favoritedIds.contains(product.id))
                        }
                        section.copy(products = updatedProducts)
                    }
                    banners to updatedSections // Return a new Pair
                }
            }.collect { finalResult ->
                // Collect the combined result and update the UI
                when (finalResult) {
                    is Result.Success -> {
                        val (banners, sections) = finalResult.data
                        _state.update { it.copy(isLoading = false, banners = banners, productSections = sections) }
                    }
                    is Result.Error -> {
                        _state.update { it.copy(isLoading = false, error = finalResult.error.toUiText()) }
                    }
                }
            }
        }
    }

    private fun loadContent() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            getHomePageDataUseCase()
                .onSuccess { (banners, sections) ->
                    _state.update { it.copy(
                        isLoading = false,
                        banners = banners,
                        productSections = sections
                    )}
                }
                .onError { error ->
                    _state.update { it.copy(
                        isLoading = false,
                        error = error.toUiText()
                    )}
                }
        }
    }
}