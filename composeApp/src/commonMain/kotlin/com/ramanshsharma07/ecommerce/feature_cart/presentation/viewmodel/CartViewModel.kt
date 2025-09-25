package com.ramanshsharma07.ecommerce.feature_cart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.core.domain.Result
import com.ramanshsharma07.ecommerce.core.presentation.toUiText
import com.ramanshsharma07.ecommerce.feature_cart.domain.model.Cart
import com.ramanshsharma07.ecommerce.feature_cart.domain.use_cases.AddProductToCartUseCase
import com.ramanshsharma07.ecommerce.feature_cart.domain.use_cases.DeleteProductFromCartUseCase
import com.ramanshsharma07.ecommerce.feature_cart.domain.use_cases.GetCartUseCase
import com.ramanshsharma07.ecommerce.feature_cart.domain.use_cases.RemoveProductFromCartUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartUseCase: GetCartUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val removeProductFromCartUseCase: RemoveProductFromCartUseCase,
    private val deleteProductFromCartUseCase: DeleteProductFromCartUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CartState())
    val state = _state.asStateFlow()

    init {
        loadCart()
    }

    fun onEvent(event: CartEvent) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result = when (event) {
                is CartEvent.IncreaseQuantity -> addProductToCartUseCase(event.productId)
                is CartEvent.DecreaseQuantity -> removeProductFromCartUseCase(event.productId)
                is CartEvent.DeleteItem -> deleteProductFromCartUseCase(event.productId)
                CartEvent.CheckoutClicked -> {
                    // Handle checkout logic later
                    getCartUseCase()
                }
            }
            handleCartResult(result)
        }
    }

    private fun loadCart() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result = getCartUseCase()
            handleCartResult(result)
        }
    }

    private fun handleCartResult(result: Result<Cart, DataError>) {
        when (result) {
            is Result.Success -> {
                _state.update { it.copy(isLoading = false, cart = result.data, error = null) }
            }
            is Result.Error -> {
                _state.update { it.copy(isLoading = false, error = result.error.toUiText()) }
            }
        }
    }
}