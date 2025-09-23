package com.ramanshsharma07.ecommerce.feature_home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramanshsharma07.ecommerce.core.domain.onError
import com.ramanshsharma07.ecommerce.core.domain.onSuccess
import com.ramanshsharma07.ecommerce.core.presentation.toUiText
import com.ramanshsharma07.ecommerce.feature_home.domain.use_cases.GetHomePageDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getHomePageDataUseCase: GetHomePageDataUseCase
): ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        loadContent()
    }

    fun onEvent(event: HomeEvent) {

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