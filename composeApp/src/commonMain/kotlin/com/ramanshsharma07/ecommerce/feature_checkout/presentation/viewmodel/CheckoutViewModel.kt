package com.ramanshsharma07.ecommerce.feature_checkout.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramanshsharma07.ecommerce.core.domain.onError
import com.ramanshsharma07.ecommerce.core.domain.onSuccess
import com.ramanshsharma07.ecommerce.core.presentation.toUiText
import com.ramanshsharma07.ecommerce.feature_checkout.domain.use_cases.GetCheckoutInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CheckoutViewModel(
    private val getCheckoutInfoUseCase: GetCheckoutInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CheckoutState())
    val state = _state.asStateFlow()

    init {
        loadCheckoutInfo()
    }

    fun onEvent(event: CheckoutEvent) {
        when (event) {
            is CheckoutEvent.SelectPaymentMethod -> {
                _state.update { it.copy(selectedPaymentMethod = event.method) }
            }
            CheckoutEvent.PlaceOrder -> {

            }
        }
    }

    private fun loadCheckoutInfo() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getCheckoutInfoUseCase()
                .onSuccess { info ->
                    _state.update { it.copy(isLoading = false, checkoutInfo = info) }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false, error = error.toUiText()) }
                }
        }
    }
}