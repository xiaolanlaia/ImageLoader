package com.renxing.moduleImageLoader.loaderStrategy.control

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import com.renxing.moduleImageLoader.imageUtils.ImgLoadParams
import com.renxing.moduleImageLoader.imageUtils.enumUtils.*

/**
 *@author  :  WuJianFeng
 */
interface ImageLoaderInterface {


    fun loadFutureTarget(context: Context, url : String,
                  decodeFormateEnum: DecodeFormateEnum,diskCacheStrategyEnum: DiskCacheStrategyEnum,function1: Function1<Bitmap?, Unit>)
    fun loadImage(imgLoadParams: ImgLoadParams)
    fun loadGifImage(imgLoadParams: ImgLoadParams)
    fun loadBitmapImage(imgLoadParams: ImgLoadParams)
    fun loadCircleImage(imgLoadParams: ImgLoadParams)
    /**
     * 重要参数：cornerRadius
     */
    fun loadCornersImage(imgLoadParams: ImgLoadParams)
    /**
     * 重要参数：
     * 1、borderColor
     * 2、borderWidth
     */
    fun loadBorderCircleImage(imgLoadParams: ImgLoadParams)
    fun load9Png(imgLoadParams: ImgLoadParams)


    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun load9Png(urlOrIdOrUri: Any, view: View)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun load9Png(urlOrIdOrUri: Any, view: View, placeholderImg: Int)

    /**
     * 清理磁盘缓存
     */
    fun clearImageDiskCache(context: Context)

    /**
     * 清理内存缓存
     */
    fun clearImageMemoryCache(context: Context)

    /**
     * 清除控件加载
     */
    fun clear(context: Context, imageView: ImageView)
    /**
     * 停止请求
     */
    fun pauseRequests(context: Context)
    /**
     * 继续请求
     */
    fun resumeRequests(context: Context)
    /**
     * 清除缓存
     */
    fun clearMemory(context: Context)
    /**
     * 清除缓存
     */
    fun trimMemory(context: Context, level : Int)



}