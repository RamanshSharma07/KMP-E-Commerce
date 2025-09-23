package com.ramanshsharma07.ecommerce

import android.app.Application
import com.ramanshsharma07.ecommerce.di.initKoin

class ECommerceApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}