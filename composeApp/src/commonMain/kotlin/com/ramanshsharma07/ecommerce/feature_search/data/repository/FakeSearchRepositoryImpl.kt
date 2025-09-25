package com.ramanshsharma07.ecommerce.feature_search.data.repository

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.core.domain.Result
import com.ramanshsharma07.ecommerce.core.domain.model.Product
import com.ramanshsharma07.ecommerce.feature_search.domain.repository.SearchRepository
import kotlinx.coroutines.delay

class FakeSearchRepositoryImpl : SearchRepository {

    // A complete list of all products in our store to search from.
    private val allProducts = getFullProductList()

    override suspend fun searchProducts(query: String): Result<List<Product>, DataError> {
        delay(300L) // Simulate search processing time

        // Perform a simple, case-insensitive search on the product names.
        val results = allProducts.filter { product ->
            product.name.contains(query, ignoreCase = true)
        }

        return Result.Success(results)
    }

    private fun getFullProductList(): List<Product> {
        return listOf(
            Product("p1", "Watch", 40.0, "watch", false),
            Product("p2", "Nike Shoes", 430.0, "nike_shoes", true),
            Product("p3", "Airpods", 333.0, "airpods", false),
            Product("p4", "LG TV", 330.0, "lg_tv", false),
            Product("p5", "Hoodie", 50.0, "hoodie", false),
            Product("p6", "Jacket", 400.0, "jacket", false),
            Product("p7", "Camera", 650.0, "camera", false),
            Product("p8", "Headphones", 199.0, "headphones", true),
            Product("p9", "Smartwatch", 250.0, "smartwatch", false),
            Product("p10", "Laptop", 1200.0, "laptop", false),
            Product("p11", "Backpack", 75.0, "backpack", true),
            Product("p12", "Jeans", 90.0, "jeans", false),
            Product("p13", "Gaming Mouse", 80.0, "gaming_mouse", false),
            Product("p14", "Keyboard", 120.0, "keyboard", false),
            Product("p15", "T-Shirt", 25.0, "tshirt", false),
            Product("p16", "Sneakers", 150.0, "sneakers", false)
        )
    }
}