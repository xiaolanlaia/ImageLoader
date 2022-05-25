package com.renxing.moduleImageLoader.loaderStrategy.control

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.renxing.moduleImageLoader.imageUtils.enumUtils.*
import com.renxing.moduleImageLoader.loaderStrategy.glide.easyglide.config.ImgConfigImpl
import com.renxing.moduleImageLoader.loaderStrategy.glide.target.RXCustomTarget

/**
 *@author  :  WuJianFeng
 */
interface ImageLoaderInterface {

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImage(context: Context, imgConfigImpl: ImgConfigImpl)

    /**
     * @url: String
     * @width: Int 指定宽度
     * @height: Int 指定高度
     */
    fun loadImage(urlOrIdOrUri: Any, imageView: ImageView, progressView : View,placeholderId: Int,errorHolder : Int)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadGif(urlOrIdOrUri: Any, imageView: ImageView)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadGif(urlOrIdOrUri: Any, imageView: ImageView,diskCacheStrategy: DiskCacheStrategyEnum)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadGif(urlOrIdOrUri: Any, imageView: ImageView, placeholderId: Int)

    /**
     * @urlOrIdOrUri: 加载类型
     * @playTimes : Int 播放次数
     */
    fun loadGif(playTimes : Int, urlOrIdOrUri: Any, imageView: ImageView)

    /**
     * @urlOrIdOrUri: 加载类型
     * @playTimes : Int 播放次数
     */
    fun loadGif(playTimes : Int, urlOrIdOrUri: Any, imageView: ImageView, diskCacheStrategy: DiskCacheStrategyEnum, onAnimationStatus : OnAnimationStatus)

    /**
     * @urlOrIdOrUri: 加载类型
     * @playTimes : Int 播放次数
     */
    fun loadGif(playTimes : Int, urlOrIdOrUri: Any, imageView: ImageView, placeholderId: Int)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadCircleGif(urlOrIdOrUri: Any, imageView: ImageView)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadCircleGif(urlOrIdOrUri: Any, imageView: ImageView, placeholderId: Int)

    /**
     * @uurlOrId: 加载类型
     * @playTimes : Int 循环次数
     */
    fun loadCircleGif(playTimes : Int, urlOrIdOrUri: Any, imageView: ImageView)

    /**
     * @uurlOrId: 加载类型
     * @playTimes : Int 循环次数
     */
    fun loadCircleGif(playTimes : Int, urlOrIdOrUri: Any, imageView: ImageView, placeholderId: Int)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadCornerGif(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadCornerGif(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float, placeholderId: Int)

    /**
     * @urlOrIdOrUri: 加载类型
     * @playTimes : Int 循环次数
     */
    fun loadCornerGif(playTimes : Int, urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float)

    /**
     * @urlOrIdOrUri: 加载类型
     * @playTimes : Int 循环次数
     */
    fun loadCornerGif(playTimes : Int, urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float, placeholderId: Int)

    /**
     * 带边框圆角gif
     * @urlOrIdOrUri: 加载类型
     */
    fun loadBorderCornerGif(urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius : Float)

    /**
     * 带边框圆角gif
     * @urlOrIdOrUri: 加载类型
     */
    fun loadBorderCornerGif(urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius : Float, placeholderId: Int)

    /**
     * 带边框圆角gif
     * @urlOrIdOrUri: 加载类型
     * @playTimes : Int 循环次数
     */
    fun loadBorderCornerGif(playTimes : Int, urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius : Float)

    /**
     * 带边框圆角gif
     * @urlOrIdOrUri: 加载类型
     * @playTimes : Int 循环次数
     */
    fun loadBorderCornerGif(playTimes : Int, urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius : Float, placeholderId: Int)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithRXCustomTarget(urlOrIdOrUri: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>)
    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithRXCustomTarget(urlOrIdOrUri: Any, context: Context, thumbnailWidth : Int, thumbnailHeight : Int, rxCustomTarget: RXCustomTarget<Bitmap>)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithRXCustomTarget(urlOrIdOrUri: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>, placeholderId: Int)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithRXCustomTarget(urlOrIdOrUri: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>,diskCacheStrategy: DiskCacheStrategyEnum)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun load9Png(urlOrIdOrUri: Any, view: View)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun load9Png(urlOrIdOrUri: Any, view: View, placeholderId: Int)

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