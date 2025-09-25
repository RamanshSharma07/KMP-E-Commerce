package com.ramanshsharma07.ecommerce.feature_profile.data.repository

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.core.domain.EmptyResult
import com.ramanshsharma07.ecommerce.core.domain.Result
import com.ramanshsharma07.ecommerce.feature_profile.domain.model.UserProfile
import com.ramanshsharma07.ecommerce.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.delay

class FakeProfileRepositoryImpl : ProfileRepository {

    override suspend fun getUserProfile(): Result<UserProfile, DataError> {
        delay(800L)


        val fakeProfile = UserProfile(
            name = "Mark Adam",
            email = "Sunny_Koelpin45@hotmail.com",
            profilePictureUrl = "https://picsum.photos/200"
        )

        return Result.Success(fakeProfile)
    }

    override suspend fun signOut(): EmptyResult<DataError> {
        delay(500L)

        // In a real app, we will clear user tokens here.
        // For the fake implementation, we just return success.
        return Result.Success(Unit)
    }
}