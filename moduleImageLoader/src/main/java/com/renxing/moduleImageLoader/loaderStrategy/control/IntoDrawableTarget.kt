package com.renxing.moduleImageLoader.loaderStrategy.control

import android.graphics.drawable.Drawable

/**
 *@date    :  2022/6/8 8:18
 *@author  :  WuJianFeng
 */
interface IntoDrawableTarget {
    fun onResourceReady(resource: Drawable)
    fun onLoadCleared()
}