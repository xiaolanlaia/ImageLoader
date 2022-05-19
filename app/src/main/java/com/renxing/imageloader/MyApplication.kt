package com.renxing.imageloader

import android.app.Application
import com.renxing.moduleImageLoader.imageUtils.RXImageLoaderConstant

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RXImageLoaderConstant.moduleImageApplication = this
    }
}