package com.ramanshsharma07.ecommerce.di

import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule() = module {
    single { Darwin.create() }
}