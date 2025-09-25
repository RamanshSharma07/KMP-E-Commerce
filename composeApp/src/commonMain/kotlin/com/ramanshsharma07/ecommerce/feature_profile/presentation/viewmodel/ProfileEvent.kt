package com.ramanshsharma07.ecommerce.feature_profile.presentation.viewmodel

sealed interface ProfileEvent {
    data object SignOutClicked : ProfileEvent
}