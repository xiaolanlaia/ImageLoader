package com.renxing.moduleImageLoader.loaderStrategy.control

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.renxing.moduleImageLoader.imageUtils.enumUtils.*
import com.renxing.moduleImageLoader.loaderStrategy.glide.target.RXCustomTarget

/**
 *@author  :  WuJianFeng
 */
interface ImageLoaderInterface {
    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImage(urlOrIdOrUri: Any, imageView: ImageView)
    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImage(urlOrIdOrUri: Any, imageView: ImageView,rxRequestListener: RXRequestListener<Drawable>)

    /**
     * @urlOrIdOrUri: 加载类型
     * @placeholderImg: Int 默认图
     */
    fun loadImage(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int)

    /**
     * @url: String
     * @width: Int 指定宽度
     * @height: Int 指定高度
     */
    fun loadImage(url: String, imageView: ImageView, width: Int, height: Int)

    /**
     * @url: String
     * @width: Int 指定宽度
     * @height: Int 指定高度
     */
    fun loadImage(urlOrIdOrUri: Any, imageView: ImageView, progressView : View,placeholderImg: Int,errorHolder : Int)

    /**
     * @url: String
     * @placeholderImg: Int 默认图
     * @width: Int 指定宽度
     * @height: Int 指定高度
     */
    fun loadImage(url: String, imageView: ImageView, width: Int, height: Int, placeholderImg: Int)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithFitCenter(urlOrIdOrUri: Any, imageView: ImageView)
    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithFitCenter(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int)
    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithFitCenter(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int, diskCacheStrategyEnum: DiskCacheStrategyEnum, priorityEnum : PriorityEnum)
    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithCenterCrop(urlOrIdOrUri: Any, imageView: ImageView)
    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithCenterCrop(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int)
    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithCenterCrop(urlOrIdOrUri: Any, imageView: ImageView, diskCacheStrategy : DiskCacheStrategyEnum)
    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithCenterCrop(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int,
                                diskCacheStrategy : DiskCacheStrategyEnum,
                                transition : Boolean)
    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithCenterCrop(urlOrIdOrUri: Any, view: View, thumbnail : Boolean, thumbnailWidth : Int, thumbnailHeight : Int)
    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithCenterCrop(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int,
                                diskCacheStrategy : DiskCacheStrategyEnum
    )
    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithCenterCrop(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int, transition : Boolean)
    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithCenterCrop(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy : DiskCacheStrategyEnum,
        transition : Boolean,
        thumbnail : Boolean,
        thumbnailWidth : Int,
        thumbnailHeight : Int
    )


    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithCenterInside(urlOrIdOrUri: Any, imageView: ImageView)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithCenterInside(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithCenterInside(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int, diskCacheStrategy: DiskCacheStrategyEnum, transition : Boolean)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithCenterInside(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int,diskCacheStrategy : DiskCacheStrategyEnum)
    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithCenterInside(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int,transition : Boolean)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithSkipCache(urlOrIdOrUri: Any, imageView: ImageView)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadImageWithSkipCache(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int)

    /**
     * @url: String
     */
    fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int)

    /**
     * @url: String
     */
    fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int, placeholderImg: Int)

    /**
     * 圆形图
     * @urlOrIdOrUri: 加载类型
     */
    fun loadCircleImage(urlOrIdOrUri: Any, imageView: ImageView)

    /**
     * 圆形图
     * @urlOrIdOrUri: 加载类型
     */
    fun loadCircleImage(urlOrIdOrUri: Any, imageView: ImageView,diskCacheStrategy: DiskCacheStrategyEnum)

    /**
     * 圆形图
     * @urlOrIdOrUri: 加载类型
     */
    fun loadCircleImage(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int)

    /**
     * 圆形图
     * @urlOrIdOrUri: 加载类型
     */
    fun loadCircleImage(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int,diskCacheStrategy: DiskCacheStrategyEnum)

    /**
     * 圆角图
     * @urlOrIdOrUri: 加载类型
     * @cornerRadius: Float 圆角半径
     */
    fun loadCornersImage(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius : Float)

    /**
     * 圆角图
     * @urlOrIdOrUri: 加载类型
     * @cornerRadius: Float 圆角半径
     */
    fun loadCornersImage(url: String, imageView: ImageView, cornerRadius : Float, placeholderImg: Int)

    /**
     * 圆角图
     * @urlOrIdOrUri: 加载类型
     * @cornerRadius: Float 圆角半径
     */
    fun loadCornersImage(url: String, imageView: ImageView, cornerRadius : Float, placeholderImg: Int,transition : Boolean)

    /**
     * 圆角图
     * @urlOrIdOrUri: 加载类型
     * @cornerRadius: Float 圆角半径
     */
    fun loadCornersImageCenterCrop(url: String, imageView: ImageView, cornerRadius : Float, placeholderImg: Int,transition : Boolean)

    /**
     * 圆角图
     * @urlOrIdOrUri: 加载类型
     * @cornerRadius: Float 圆角半径
     */
    fun loadCornersImage(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius : Float, placeholderImg: Int, diskCacheStrategy: DiskCacheStrategyEnum)

    /**
     * 指定圆角边的圆角图
     * @urlOrIdOrUri: 加载类型
     */
    fun loadCornersImage(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum)

    /**
     * 指定圆角边的圆角图
     * @urlOrIdOrUri: 加载类型
     */
    fun loadCornersImage(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum, placeholderImg: Int)
    /**
     * 指定圆角边的圆角图
     * @bitmap: Bitmap
     */
    fun loadCornersImage(bitmap: Bitmap, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum)
    /**
     * 指定圆角边的圆角图
     * @bitmap: Bitmap
     */
    fun loadCornersImage(bitmap: Bitmap, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum, placeholderImg: Int)
    /**
     * 指定圆角边的圆角图
     * @bitmap: Bitmap
     */
    fun loadCornersImageWithCenterCrop(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int, cornerRadius : Float, diskCacheStrategy: DiskCacheStrategyEnum,
                                       transition : Boolean, thumbnail : Boolean, thumbnailWidth : Int, thumbnailHeight : Int)

    /**
     * 带边框圆形图
     * urlOrIdOrUri: 加载类型
     */
    fun loadBorderCircleImage(urlOrIdOrUri: Any, imageView: ImageView, borderColor : Int, borderWidth : Float)

    /**
     * 带边框圆形图
     * urlOrIdOrUri: 加载类型
     */
    fun loadBorderCircleImage(url: String, imageView: ImageView, imageWidth : Int, imageHeight : Int, borderColor : Int, borderWidth : Float, placeholderImg: Int)

    /**
     * 带边框圆形图
     * urlOrIdOrUri: 加载类型
     */
    fun loadBorderCircleImage(url: String, imageView: ImageView, borderColor : Int, borderWidth : Float, placeholderImg: Int)

    /**
     * 带边框圆角图
     * urlOrIdOrUri: 加载类型
     */
    fun loadBorderCornerImage(urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius : Float)

    /**
     * 带边框圆角图
     * urlOrIdOrUri: 加载类型
     */
    fun loadBorderCornerImage(urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius : Float, placeholderImg: Int)

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
    fun loadGif(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int)

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
    fun loadGif(playTimes : Int, urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadCircleGif(urlOrIdOrUri: Any, imageView: ImageView)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadCircleGif(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int)

    /**
     * @uurlOrId: 加载类型
     * @playTimes : Int 循环次数
     */
    fun loadCircleGif(playTimes : Int, urlOrIdOrUri: Any, imageView: ImageView)

    /**
     * @uurlOrId: 加载类型
     * @playTimes : Int 循环次数
     */
    fun loadCircleGif(playTimes : Int, urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadCornerGif(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float)

    /**
     * @urlOrIdOrUri: 加载类型
     */
    fun loadCornerGif(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float, placeholderImg: Int)

    /**
     * @urlOrIdOrUri: 加载类型
     * @playTimes : Int 循环次数
     */
    fun loadCornerGif(playTimes : Int, urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float)

    /**
     * @urlOrIdOrUri: 加载类型
     * @playTimes : Int 循环次数
     */
    fun loadCornerGif(playTimes : Int, urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float, placeholderImg: Int)

    /**
     * 带边框圆角gif
     * @urlOrIdOrUri: 加载类型
     */
    fun loadBorderCornerGif(urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius : Float)

    /**
     * 带边框圆角gif
     * @urlOrIdOrUri: 加载类型
     */
    fun loadBorderCornerGif(urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius : Float, placeholderImg: Int)

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
    fun loadBorderCornerGif(playTimes : Int, urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius : Float, placeholderImg: Int)

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
    fun loadImageWithRXCustomTarget(urlOrIdOrUri: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>, placeholderImg: Int)

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