package com.ramanshsharma07.ecommerce

import androidx.compose.ui.window.ComposeUIViewController
import com.ramanshsharma07.ecommerce.app.App
import com.ramanshsharma07.ecommerce.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}