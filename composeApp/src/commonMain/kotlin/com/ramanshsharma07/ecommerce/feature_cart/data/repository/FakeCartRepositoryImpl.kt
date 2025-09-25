package com.ramanshsharma07.ecommerce.feature_cart.data.repository

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.core.domain.Result
import com.ramanshsharma07.ecommerce.core.domain.model.Product
import com.ramanshsharma07.ecommerce.feature_cart.domain.model.Cart
import com.ramanshsharma07.ecommerce.feature_cart.domain.model.CartItem
import com.ramanshsharma07.ecommerce.feature_cart.domain.model.OrderSummary
import com.ramanshsharma07.ecommerce.feature_cart.domain.repository.CartRepository

class FakeCartRepositoryImpl : CartRepository {

    // A private list to hold the state of the items in the cart
    private val cartItems = mutableListOf<CartItem>()

    // We need a list of all available products to add them to the cart
    private val allProducts = getFullProductList()

    init {
        // Pre-populate the cart with a few items for a realistic demo
        cartItems.add(CartItem(product = allProducts[0], quantity = 2))
        cartItems.add(CartItem(product = allProducts[2], quantity = 1))
        cartItems.add(CartItem(product = allProducts[6], quantity = 3))
    }

    override suspend fun getCart(): Result<Cart, DataError> {
        return Result.Success(recalculateCart())
    }

    override suspend fun addProductToCart(productId: String): Result<Cart, DataError> {
        val existingItem = cartItems.find { it.product.id == productId }

        if (existingItem != null) {
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + 1)
            cartItems[cartItems.indexOf(existingItem)] = updatedItem
        } else {
            val productToAdd = allProducts.find { it.id == productId }
            if (productToAdd != null) {
                cartItems.add(CartItem(product = productToAdd, quantity = 1))
            } else {
                return Result.Error(DataError.Remote.SERVER) // Product not found
            }
        }
        return Result.Success(recalculateCart())
    }

    override suspend fun removeProductFromCart(productId: String): Result<Cart, DataError> {
        val existingItem = cartItems.find { it.product.id == productId }

        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                val updatedItem = existingItem.copy(quantity = existingItem.quantity - 1)
                cartItems[cartItems.indexOf(existingItem)] = updatedItem
            } else {
                // If quantity is 1, remove it completely
                cartItems.remove(existingItem)
            }
        }
        return Result.Success(recalculateCart())
    }

    override suspend fun deleteProductFromCart(productId: String): Result<Cart, DataError> {
        cartItems.removeAll { it.product.id == productId }
        return Result.Success(recalculateCart())
    }

    /**
     * A helper function to calculate the order summary based on the current items.
     */
    private fun recalculateCart(): Cart {
        val subtotal = cartItems.sumOf { it.product.price * it.quantity }
        val deliveryCharges = if (subtotal > 0) 2.0 else 0.0
        val discount = if (subtotal > 100) 4.0 else 0.0 // Example discount logic
        val total = subtotal + deliveryCharges - discount

        val summary = OrderSummary(
            subtotal = subtotal,
            deliveryCharges = deliveryCharges,
            discount = discount,
            total = total
        )

        return Cart(items = cartItems.toList(), summary = summary)
    }

    /**
     * A helper to provide the full list of products available in the store.
     * In a real app, this would come from a different repository.
     */
    private fun getFullProductList(): List<Product> {
        return listOf(
            Product("p1", "Watch", 40.0, "watch", false),
            Product("p2", "Nike Shoes", 430.0, "shoes", true),
            Product("p3", "Airpods", 333.0, "airpods", false),
            Product("p4", "LG TV", 330.0, "tv", false),
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