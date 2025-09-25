package com.ramanshsharma07.ecommerce.feature_profile.presentation.viewmodel

import com.ramanshsharma07.ecommerce.core.presentation.UiText
import com.ramanshsharma07.ecommerce.feature_profile.domain.model.UserProfile

data class ProfileState(
    val isLoading: Boolean = false,
    val userProfile: UserProfile? = null,
    val error: UiText? = null
)
