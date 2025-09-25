package com.ramanshsharma07.ecommerce.feature_profile.domain.repository

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.core.domain.Result
import com.ramanshsharma07.ecommerce.core.domain.EmptyResult
import com.ramanshsharma07.ecommerce.feature_profile.domain.model.UserProfile

interface ProfileRepository {

    /** Fetches the current user's profile information. */
    suspend fun getUserProfile(): Result<UserProfile, DataError>

    /** Signs the current user out of the application. */
    suspend fun signOut(): EmptyResult<DataError>
}