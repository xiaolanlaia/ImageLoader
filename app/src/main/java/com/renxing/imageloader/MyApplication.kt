package com.renxing.imageloader

import android.app.Application
import com.renxing.moduleImageLoader.imageUtils.ModuleImageConstant

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ModuleImageConstant.moduleImageApplication = this
    }
}