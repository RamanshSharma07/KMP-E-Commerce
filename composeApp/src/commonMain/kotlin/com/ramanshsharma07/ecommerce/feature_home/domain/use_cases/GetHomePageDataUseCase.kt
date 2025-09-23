package com.ramanshsharma07.ecommerce.feature_home.domain.use_cases

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.core.domain.Result
import com.ramanshsharma07.ecommerce.feature_home.domain.model.ProductSection
import com.ramanshsharma07.ecommerce.feature_home.domain.model.PromotionBanner
import com.ramanshsharma07.ecommerce.feature_home.domain.repository.HomeRepository

class GetHomePageDataUseCase(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Result<Pair<List<PromotionBanner>, List<ProductSection>>, DataError.Remote> {
        return homeRepository.getHomePageContent()
    }
}