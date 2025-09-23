package com.ramanshsharma07.ecommerce.feature_home.data.network

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.feature_home.data.dtos.HomePageResponseDto
import com.ramanshsharma07.ecommerce.feature_home.data.dtos.ProductDto
import com.ramanshsharma07.ecommerce.feature_home.data.dtos.ProductSectionDto
import com.ramanshsharma07.ecommerce.feature_home.data.dtos.PromotionBannerDto
import kotlinx.coroutines.delay
import com.ramanshsharma07.ecommerce.core.domain.Result

class FakeHomeRemoteDataSource : HomeRemoteDataSource {

    override suspend fun getHomePageContent(): Result<HomePageResponseDto, DataError.Remote> {
        // Simulate a network delay to make the loading state visible in the UI
        delay(1000L) // 1 second delay

        // Create fake data that matches the UI screenshot
        val banners = listOf(
            PromotionBannerDto(
                id = "banner_1",
                title = "Get Winter Discount",
                subtitle = "20% Off For Children",
                imageUrl = "https://picsum.photos/200"
            ),
            PromotionBannerDto(
                id = "banner_2",
                title = "Get Summer Discount",
                subtitle = "20% Off For Women",
                imageUrl = "https://picsum.photos/200"
            )
        )

        val featuredProducts = listOf(
            ProductDto("p1", "Watch", 40.0, "https://picsum.photos/200", false),
            ProductDto("p2", "Nike Shoes", 430.0, "https://picsum.photos/200", true),
            ProductDto("p3", "Airpods", 333.0, "https://picsum.photos/200", false)
        )

        val popularProducts = listOf(
            ProductDto("p4", "LG TV", 330.0, "https://picsum.photos/200", false),
            ProductDto("p5", "Hoodie", 50.0, "https://picsum.photos/200", false),
            ProductDto("p6", "Jacket", 400.0, "https://picsum.photos/200", false)
        )

        val sections = listOf(
            ProductSectionDto("Featured", featuredProducts),
            ProductSectionDto("Most Popular", popularProducts)
        )

        val response = HomePageResponseDto(banners = banners, sections = sections)

        // Wrap the fake data in a Success result
        return Result.Success(response)

        // To test your error UI, you could uncomment this line:
        // return Result.Error(DataError.Remote.NO_INTERNET)
    }
}