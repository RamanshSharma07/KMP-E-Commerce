package com.ramanshsharma07.ecommerce

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ramanshsharma07.ecommerce.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP - E-Commerce",
    ) {
        App()
    }
}