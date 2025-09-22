package com.ramanshsharma07.ecommerce

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform