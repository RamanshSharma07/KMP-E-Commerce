package com.ramanshsharma07.ecommerce.di

import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule() = module {
    single { OkHttp.create() }
}