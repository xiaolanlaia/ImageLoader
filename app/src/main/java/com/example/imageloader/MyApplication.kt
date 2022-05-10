package com.example.imageloader

import android.app.Application
import com.example.moduleimage.imageUtils.AppConfig

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppConfig.application = this
    }
}