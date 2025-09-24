package com.ramanshsharma07.ecommerce.feature_selected_product.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramanshsharma07.ecommerce.core.domain.onError
import com.ramanshsharma07.ecommerce.core.domain.onSuccess
import com.ramanshsharma07.ecommerce.core.presentation.toUiText
import com.ramanshsharma07.ecommerce.feature_selected_product.domain.use_cases.GetProductDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val productId: String,
    private val getProductDetailsUseCase: GetProductDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProductDetailsState())
    val state = _state.asStateFlow()

    init {
        loadProductDetails()
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

            }
            is ProductDetailsEvent.AddToCartClicked -> {

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