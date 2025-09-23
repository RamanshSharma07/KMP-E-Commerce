package com.ramanshsharma07.ecommerce.feature_home.data.network

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.feature_home.data.dtos.HomePageResponseDto
import com.ramanshsharma07.ecommerce.core.domain.Result

interface HomeRemoteDataSource {
    suspend fun getHomePageContent(): Result<HomePageResponseDto, DataError.Remote>
}