package com.ramanshsharma07.ecommerce.feature_products.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramanshsharma07.ecommerce.core.domain.map
import com.ramanshsharma07.ecommerce.core.domain.onError
import com.ramanshsharma07.ecommerce.core.domain.onSuccess
import com.ramanshsharma07.ecommerce.core.domain.use_cases.GetFavoritesIdsUseCase
import com.ramanshsharma07.ecommerce.core.domain.use_cases.ToggleFavoriteUseCase
import com.ramanshsharma07.ecommerce.core.presentation.toUiText
import com.ramanshsharma07.ecommerce.feature_cart.domain.use_cases.AddProductToCartUseCase
import com.ramanshsharma07.ecommerce.feature_products.domain.use_cases.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.ramanshsharma07.ecommerce.core.domain.Result

class ProductsViewModel(
    private val category: String, // Will be injected by Koin
    private val getProductsUseCase: GetProductsUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val getFavoritesIdsUseCase: GetFavoritesIdsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProductsState())
    val state = _state.asStateFlow()

    init {
//        loadProducts()
        observeFavorites()
    }

    fun onEvent(event: ProductsEvent) {
        when (event) {
            is ProductsEvent.AddToCart -> {
                viewModelScope.launch {
                    addProductToCartUseCase(event.productId)
                }
            }
            is ProductsEvent.OnFavoriteClick -> {
                viewModelScope.launch {
                    toggleFavoriteUseCase(event.productId)
                }
            }
        }
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            _state.update { it.copy(title = category) }

            getProductsUseCase(category)
                .onSuccess { products ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            products = products
                        )
                    }
                    println("Products: $products")
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.toUiText()
                        )
                    }
                    println("Error: $error")
                }
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch {

            _state.update { it.copy(isLoading = true) }

            _state.update { it.copy(title = category) }

            val productsResult = getProductsUseCase(category)

            val favoritesFlow = getFavoritesIdsUseCase()

            favoritesFlow.combine(flowOf(productsResult)) { favoriteIds, result ->
                result.map { products ->
                    products.map { product ->
                        product.copy(isFavorite = favoriteIds.contains(product.id))
                    }
                }
            }.collect { finalResult ->
                when (finalResult) {
                    is Result.Success -> {
                        _state.update {
                            it.copy(
                                products = finalResult.data,
                                isLoading = false
                            )
                        }
                    }
                    is Result.Error -> {
                        _state.update {
                            it.copy(
                                error = finalResult.error.toUiText(),
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}