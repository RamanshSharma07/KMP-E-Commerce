package com.ramanshsharma07.ecommerce.feature_selected_product.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramanshsharma07.ecommerce.core.domain.map
import com.ramanshsharma07.ecommerce.core.domain.onError
import com.ramanshsharma07.ecommerce.core.domain.onSuccess
import com.ramanshsharma07.ecommerce.core.domain.use_cases.GetFavoritesIdsUseCase
import com.ramanshsharma07.ecommerce.core.domain.use_cases.ToggleFavoriteUseCase
import com.ramanshsharma07.ecommerce.core.presentation.toUiText
import com.ramanshsharma07.ecommerce.feature_cart.domain.use_cases.AddProductToCartUseCase
import com.ramanshsharma07.ecommerce.feature_selected_product.domain.use_cases.GetProductDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.ramanshsharma07.ecommerce.core.domain.Result

class ProductDetailsViewModel(
    private val productId: String,
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val getFavoritesIdsUseCase: GetFavoritesIdsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProductDetailsState())
    val state = _state.asStateFlow()

    init {
//        loadProductDetails()
        observeProductDetailsWithFavorites()
    }

    fun onEvent(event: ProductDetailsEvent) {
        when (event) {
            is ProductDetailsEvent.SizeSelected -> {
                _state.update {
                    it.copy(
                        selectedSize = event.size
                    )
                }
            }
            is ProductDetailsEvent.FavoriteClicked -> {
                viewModelScope.launch {
                    toggleFavoriteUseCase(productId)
                }
            }
            is ProductDetailsEvent.AddToCartClicked -> {
                viewModelScope.launch {
                    addProductToCartUseCase(productId)
                }
            }
        }
    }

    private fun observeProductDetailsWithFavorites() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val detailsResult = getProductDetailsUseCase(productId)
            val favoritesFlow = getFavoritesIdsUseCase()

            favoritesFlow.combine(flowOf(detailsResult)) { favoritedIds, result ->
                result.map { product ->
                    product.copy(isFavorite = favoritedIds.contains(product.id))
                }
            }.collect { finalResult ->
                when (finalResult) {
                    is Result.Success -> {
                        _state.update { it.copy(isLoading = false, product = finalResult.data) }
                    }
                    is Result.Error -> {
                        _state.update { it.copy(isLoading = false, error = finalResult.error.toUiText()) }
                    }
                }
            }
        }
    }

    private fun loadProductDetails() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getProductDetailsUseCase(productId)
                .onSuccess { productDetails ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            product = productDetails
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.toUiText()
                        )
                    }
                }
        }
    }
}