package com.ramanshsharma07.ecommerce.feature_home.data.repository

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.feature_home.data.network.HomeRemoteDataSource
import com.ramanshsharma07.ecommerce.feature_home.domain.model.ProductSection
import com.ramanshsharma07.ecommerce.feature_home.domain.model.PromotionBanner
import com.ramanshsharma07.ecommerce.feature_home.domain.repository.HomeRepository
import com.ramanshsharma07.ecommerce.core.domain.Result
import com.ramanshsharma07.ecommerce.core.domain.map
import com.ramanshsharma07.ecommerce.feature_home.data.mappers.toDomain

class HomeRepositoryImpl(
    private val remoteDataSource: HomeRemoteDataSource
) : HomeRepository {

    override suspend fun getHomePageContent():  Result<Pair<List<PromotionBanner>, List<ProductSection>>, DataError.Remote> {
        return remoteDataSource.getHomePageContent().map { homePageResponseDto ->
            homePageResponseDto.toDomain() // Map the successful result to our domain models
        }
    }
}