package com.renxing.imageloader

import android.app.Application
import com.renxing.moduleImage.imageUtils.ModuleImageConstant

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ModuleImageConstant.application = this
    }
}