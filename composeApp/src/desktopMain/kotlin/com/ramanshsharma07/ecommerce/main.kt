package com.ramanshsharma07.ecommerce

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ramanshsharma07.ecommerce.app.App
import com.ramanshsharma07.ecommerce.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "CMP-BookShelf",
        ) {
            App()
        }
    }
}