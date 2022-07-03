package com.renxing.moduleImageLoader.loaderStrategy.control

import android.graphics.Bitmap

/**
 *@date    :  2022/6/8 8:03
 *@author  :  WuJianFeng
 */
interface IntoBitmapTarget {
    fun onResourceReady(resource: Bitmap)
    fun onLoadCleared()
}