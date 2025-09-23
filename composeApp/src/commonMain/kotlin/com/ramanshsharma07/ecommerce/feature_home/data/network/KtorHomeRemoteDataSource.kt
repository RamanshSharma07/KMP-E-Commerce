package com.ramanshsharma07.ecommerce.feature_home.data.network

import com.ramanshsharma07.ecommerce.core.data.safeCall
import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.feature_home.data.dtos.HomePageResponseDto
import com.ramanshsharma07.ecommerce.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://my-api-url.com/api/home"

class KtorHomeRemoteDataSource(
    private val httpClient: HttpClient
) : HomeRemoteDataSource {

    override suspend fun getHomePageContent(): Result<HomePageResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "$BASE_URL/search.json"
            )
        }
    }
}