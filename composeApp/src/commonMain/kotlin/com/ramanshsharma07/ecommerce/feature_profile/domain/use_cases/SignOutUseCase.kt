package com.ramanshsharma07.ecommerce.feature_profile.domain.use_cases

import com.ramanshsharma07.ecommerce.feature_profile.domain.repository.ProfileRepository

class SignOutUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke() = repository.signOut()
}