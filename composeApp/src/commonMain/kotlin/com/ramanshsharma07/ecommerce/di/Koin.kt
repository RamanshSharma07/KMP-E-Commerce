package com.ramanshsharma07.ecommerce.di

import com.ramanshsharma07.ecommerce.core.data.HttpClientFactory
import org.koin.core.context.startKoin
import org.koin.dsl.module

// A module for app-wide singletons like HttpClient
val appModule = module {
    single { HttpClientFactory.create(get()) }
}

fun initKoin() {
    startKoin {
        modules(
            appModule,
            homeModule,
            productsModule,
            productDetailsModule,
            cartModule,
            favoritesModule,
            checkoutModule,
            profileModule,
            platformModule()
        )
    }
}