package com.renxing.moduleImageLoader.loaderStrategy.control

/**
 *@date    :  2022/6/8 7:19
 *@author  :  WuJianFeng
 */
interface RXListener {
    fun onResourceReady()
    fun onLoadFailed()
}