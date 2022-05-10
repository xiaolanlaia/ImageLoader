package com.renxing.imageloader

import android.app.Application
import com.renxing.moduleImage.imageUtils.AppConfig

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppConfig.application = this
    }
}