package com.ramanshsharma07.ecommerce.feature_products.data.network

import com.ramanshsharma07.ecommerce.core.data.dtos.ProductDto
import com.ramanshsharma07.ecommerce.core.domain.DataError
import kotlinx.coroutines.delay
import com.ramanshsharma07.ecommerce.core.domain.Result

class FakeProductsRemoteDataSource : ProductsRemoteDataSource {

    private val allProducts = listOf(
        // Featured Products (first 6 are from the home screen)
        ProductDto("p1", "Watch", 40.0, "watch", false),
        ProductDto("p2", "Nike Shoes", 430.0, "shoes", true),
        ProductDto("p3", "Airpods", 333.0, "airpods", false),
        ProductDto("p7", "Camera", 650.0, "camera", false),
        ProductDto("p8", "Headphones", 199.0, "headphones", true),
        ProductDto("p9", "Smartwatch", 250.0, "smartwatch", false),
        ProductDto("p13", "Gaming Mouse", 80.0, "gaming_mouse", false),
        ProductDto("p14", "Keyboard", 120.0, "keyboard", false),

        // Popular Products (first 6 are from the home screen)
        ProductDto("p4", "LG TV", 330.0, "tv", false),
        ProductDto("p5", "Hoodie", 50.0, "hoodie", false),
        ProductDto("p6", "Jacket", 400.0, "jacket", false),
        ProductDto("p10", "Laptop", 1200.0, "laptop", false),
        ProductDto("p11", "Backpack", 75.0, "backpack", true),
        ProductDto("p12", "Jeans", 90.0, "jeans", false),
        ProductDto("p15", "T-Shirt", 25.0, "tshirt", false),
        ProductDto("p16", "Sneakers", 150.0, "sneakers", false)
    )

    override suspend fun getProductsByCategory(category: String): Result<List<ProductDto>, DataError.Remote> {
        delay(1000L)
        val products = when (category) {
            "Featured" -> allProducts.take(8)
            "Most Popular" -> allProducts.drop(8)
            else -> emptyList()
        }
        return Result.Success(products)
    }
}