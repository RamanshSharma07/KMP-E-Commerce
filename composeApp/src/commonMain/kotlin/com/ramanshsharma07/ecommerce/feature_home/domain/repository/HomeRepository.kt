package com.ramanshsharma07.ecommerce.feature_home.domain.repository

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.core.domain.Result
import com.ramanshsharma07.ecommerce.feature_home.domain.model.ProductSection
import com.ramanshsharma07.ecommerce.feature_home.domain.model.PromotionBanner

interface HomeRepository {
    /**
     * Fetches all the content required for the home screen.
     * Returns a pair containing a list of banners and a list of product sections.
     */
    suspend fun getHomePageContent(): Result<Pair<List<PromotionBanner>, List<ProductSection>>, DataError.Remote>
}