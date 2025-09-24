package com.ramanshsharma07.ecommerce.feature_selected_product.data.network

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.feature_selected_product.data.dtos.ProductDetailsDto
import kotlinx.coroutines.delay
import com.ramanshsharma07.ecommerce.core.domain.Result

class FakeProductDetailsRemoteDataSource : ProductDetailsRemoteDataSource {

    private val fakeProducts = listOf(
        // Detailed data for the Nike Shoes to match the screenshot
        ProductDetailsDto(
            id = "p1",
            name = "Watch",
            price = 40.0,
            imageUrls = listOf("watch", "watch_2"),
            isFavorite = false,
            rating = 4.8,
            reviewCount = 152,
            description = "A classic timepiece for any occasion. Features a stainless steel band and a minimalist watch face.",
            availableSizes = listOf("S", "M", "L")
        ),
        ProductDetailsDto(
            id = "p2",
            name = "Nike Shoes",
            price = 430.0,
            imageUrls = listOf("nike_shoes", "nike_shoes_2", "nike_shoes_3"),
            isFavorite = true,
            rating = 4.5,
            reviewCount = 20,
            description = "Culpa aliquam consequuntur veritatis at consequuntur praesentium beatae temporibus nobis. Velit dolorem facilis neque autem.",
            availableSizes = listOf("8", "10", "38", "40")
        ),
        ProductDetailsDto(
            id = "p3",
            name = "Airpods",
            price = 333.0,
            imageUrls = listOf("airpods", "airpods_2"),
            isFavorite = false,
            rating = 4.9,
            reviewCount = 1990,
            description = "High-fidelity audio and industry-leading Active Noise Cancellation. A magical listening experience.",
            availableSizes = listOf("Standard")
        ),
        ProductDetailsDto(
            id = "p7",
            name = "Camera",
            price = 650.0,
            imageUrls = listOf("camera", "camera_2"),
            isFavorite = false,
            rating = 4.7,
            reviewCount = 89,
            description = "Capture your life's moments in stunning detail with this 24MP mirrorless camera. Features 4K video and a versatile kit lens.",
            availableSizes = listOf("N/A")
        ),
        ProductDetailsDto(
            id = "p8",
            name = "Headphones",
            price = 199.0,
            imageUrls = listOf("headphones", "headphones_2"),
            isFavorite = true,
            rating = 4.6,
            reviewCount = 340,
            description = "Immerse yourself in sound with these over-ear noise-cancelling headphones. 30-hour battery life.",
            availableSizes = listOf("Adjustable")
        ),
        ProductDetailsDto(
            id = "p9",
            name = "Smartwatch",
            price = 250.0,
            imageUrls = listOf("smartwatch", "smartwatch_2"),
            isFavorite = false,
            rating = 4.5,
            reviewCount = 112,
            description = "Track your fitness, take calls, and get notifications on your wrist. A perfect companion for your active lifestyle.",
            availableSizes = listOf("40mm", "44mm")
        ),
        ProductDetailsDto(
            id = "p13",
            name = "Gaming Mouse",
            price = 80.0,
            imageUrls = listOf("gaming_mouse"),
            isFavorite = false,
            rating = 4.8,
            reviewCount = 550,
            description = "Ultra-lightweight gaming mouse with a high-precision 16,000 DPI optical sensor and customizable RGB lighting.",
            availableSizes = listOf("N/A")
        ),
        ProductDetailsDto(
            id = "p14",
            name = "Keyboard",
            price = 120.0,
            imageUrls = listOf("keyboard"),
            isFavorite = false,
            rating = 4.9,
            reviewCount = 730,
            description = "A mechanical keyboard with tactile switches for a satisfying typing experience. Full-size layout with a numeric keypad.",
            availableSizes = listOf("Full-size", "TKL")
        ),

        // --- Popular Products ---
        ProductDetailsDto(
            id = "p4",
            name = "LG TV",
            price = 330.0,
            imageUrls = listOf("lg_tv", "lg_tv_2"),
            isFavorite = false,
            rating = 4.7,
            reviewCount = 205,
            description = "Experience vibrant colors and deep blacks with this 55-inch 4K OLED TV. Perfect for movies and gaming.",
            availableSizes = listOf("55\"", "65\"")
        ),
        ProductDetailsDto(
            id = "p5",
            name = "Hoodie",
            price = 50.0,
            imageUrls = listOf("hoodie"),
            isFavorite = false,
            rating = 4.9,
            reviewCount = 880,
            description = "A comfortable and stylish hoodie made from 100% premium cotton. Features a soft fleece interior.",
            availableSizes = listOf("S", "M", "L", "XL")
        ),
        ProductDetailsDto(
            id = "p6",
            name = "Jacket",
            price = 400.0,
            imageUrls = listOf("jacket", "jacket_2"),
            isFavorite = false,
            rating = 4.6,
            reviewCount = 95,
            description = "Stay warm and dry with this insulated, waterproof jacket. Perfect for cold weather adventures.",
            availableSizes = listOf("M", "L", "XL")
        ),
        ProductDetailsDto(
            id = "p10",
            name = "Laptop",
            price = 1200.0,
            imageUrls = listOf("laptop"),
            isFavorite = false,
            rating = 4.8,
            reviewCount = 410,
            description = "A powerful and portable laptop with the latest gen processor, 16GB of RAM, and a 512GB SSD.",
            availableSizes = listOf("13\"", "15\"")
        ),
        ProductDetailsDto(
            id = "p11",
            name = "Backpack",
            price = 75.0,
            imageUrls = listOf("backpack"),
            isFavorite = true,
            rating = 4.9,
            reviewCount = 1200,
            description = "A durable and spacious backpack with a dedicated laptop compartment and multiple pockets for organization.",
            availableSizes = listOf("25L", "30L")
        ),
        ProductDetailsDto(
            id = "p12",
            name = "Jeans",
            price = 90.0,
            imageUrls = listOf("jeans"),
            isFavorite = false,
            rating = 4.4,
            reviewCount = 560,
            description = "Classic slim-fit jeans made from comfortable stretch denim. A wardrobe essential.",
            availableSizes = listOf("30x32", "32x32", "34x34")
        ),
        ProductDetailsDto(
            id = "p15",
            name = "T-Shirt",
            price = 25.0,
            imageUrls = listOf("tshirt"),
            isFavorite = false,
            rating = 4.7,
            reviewCount = 2500,
            description = "A soft, breathable t-shirt with a modern fit. Made from a high-quality cotton blend.",
            availableSizes = listOf("S", "M", "L", "XL", "XXL")
        ),
        ProductDetailsDto(
            id = "p16",
            name = "Sneakers",
            price = 150.0,
            imageUrls = listOf("sneakers"),
            isFavorite = false,
            rating = 4.6,
            reviewCount = 610,
            description = "Stylish and comfortable sneakers for everyday wear. Features a lightweight sole and a breathable upper.",
            availableSizes = listOf("9", "10", "11", "12")
        )
    )

    override suspend fun getProductDetails(productId: String): Result<ProductDetailsDto, DataError.Remote> {
        delay(1000L)
        val product = fakeProducts.find { it.id == productId }
        return if (product != null) {
            Result.Success(product)
        } else {
            Result.Error(DataError.Remote.SERVER)
        }
    }
}