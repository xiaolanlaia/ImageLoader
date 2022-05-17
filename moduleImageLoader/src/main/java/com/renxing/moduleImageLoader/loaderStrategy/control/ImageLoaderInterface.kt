package com.renxing.moduleImageLoader.loaderStrategy.control

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import com.renxing.moduleImageLoader.imageUtils.ModuleImageConstant
import com.renxing.moduleImageLoader.loaderStrategy.glide.target.RXCustomTarget

/**
 *@author  :  WuJianFeng
 */
interface ImageLoaderInterface {
    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadImage(urlOrId: Any, imageView: ImageView)

    /**
     * @urlOrId: String类型或Int类型
     * @placeholderImg: Int 默认图
     */
    fun loadImage(urlOrId: Any, imageView: ImageView, placeholderImg: Int)

    /**
     * @url: String
     * @width: Int 指定宽度
     * @height: Int 指定高度
     */
    fun loadImage(url: String, imageView: ImageView, width: Int, height: Int)

    /**
     * @url: String
     * @placeholderImg: Int 默认图
     * @width: Int 指定宽度
     * @height: Int 指定高度
     */
    fun loadImage(url: String, imageView: ImageView, width: Int, height: Int, placeholderImg: Int)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadImageWithFitCenter(urlOrId: Any, imageView: ImageView)
    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadImageWithFitCenter(urlOrId: Any, imageView: ImageView, placeholderImg: Int)
    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView)
    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView, placeholderImg: Int)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadImageWithCenterInside(urlOrId: Any, imageView: ImageView)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadImageWithCenterInside(urlOrId: Any, imageView: ImageView, placeholderImg: Int)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadImageWithSkipCache(urlOrId: Any, imageView: ImageView)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadImageWithSkipCache(urlOrId: Any, imageView: ImageView, placeholderImg: Int)

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
     * @urlOrId: String类型或Int类型
     */
    fun loadCircleImage(urlOrId: Any, imageView: ImageView)

    /**
     * 圆形图
     * @urlOrId: String类型或Int类型
     */
    fun loadCircleImage(urlOrId: Any, imageView: ImageView, placeholderImg: Int)

    /**
     * 圆角图
     * @urlOrId: String类型或Int类型
     * @cornerRadius: Float 圆角半径
     */
    fun loadCornersImage(urlOrId: Any, imageView: ImageView, cornerRadius : Float)

    /**
     * 圆角图
     * @urlOrId: String类型或Int类型
     * @cornerRadius: Float 圆角半径
     */
    fun loadCornersImage(urlOrId: Any, imageView: ImageView, cornerRadius : Float, placeholderImg: Int)

    /**
     * 指定圆角边的圆角图
     * @urlOrId: String类型或Int类型
     */
    fun loadCornersImage(urlOrId: Any, imageView: ImageView, cornerRadius: Float, cornerType: ModuleImageConstant.CornerType)

    /**
     * 指定圆角边的圆角图
     * @urlOrId: String类型或Int类型
     */
    fun loadCornersImage(urlOrId: Any, imageView: ImageView, cornerRadius: Float, cornerType: ModuleImageConstant.CornerType, placeholderImg: Int)
    /**
     * 指定圆角边的圆角图
     * @bitmap: Bitmap
     */
    fun loadCornersImage(bitmap: Bitmap, imageView: ImageView, cornerRadius: Float, cornerType: ModuleImageConstant.CornerType)
    /**
     * 指定圆角边的圆角图
     * @bitmap: Bitmap
     */
    fun loadCornersImage(bitmap: Bitmap, imageView: ImageView, cornerRadius: Float, cornerType: ModuleImageConstant.CornerType, placeholderImg: Int)

    /**
     * 带边框圆形图
     * urlOrId: String类型或Int类型
     */
    fun loadBorderCircleImage(urlOrId: Any, imageView: ImageView, borderColor : Int, borderWidth : Float)

    /**
     * 带边框圆形图
     * urlOrId: String类型或Int类型
     */
    fun loadBorderCircleImage(urlOrId: Any, imageView: ImageView, borderColor : Int, borderWidth : Float, placeholderImg: Int)

    /**
     * 带边框圆角图
     * urlOrId: String类型或Int类型
     */
    fun loadBorderCornerImage(urlOrId: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius : Float)

    /**
     * 带边框圆角图
     * urlOrId: String类型或Int类型
     */
    fun loadBorderCornerImage(urlOrId: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius : Float, placeholderImg: Int)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadGif(urlOrId: Any, imageView: ImageView)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadGif(urlOrId: Any, imageView: ImageView, placeholderImg: Int)

    /**
     * @urlOrId: String类型或Int类型
     * @playTimes : Int 播放次数
     */
    fun loadGif(playTimes : Int, urlOrId: Any, imageView: ImageView)

    /**
     * @urlOrId: String类型或Int类型
     * @playTimes : Int 播放次数
     */
    fun loadGif(playTimes : Int, urlOrId: Any, imageView: ImageView, placeholderImg: Int)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadCircleGif(urlOrId: Any, imageView: ImageView)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadCircleGif(urlOrId: Any, imageView: ImageView, placeholderImg: Int)

    /**
     * @uurlOrId: String类型或Int类型
     * @playTimes : Int 循环次数
     */
    fun loadCircleGif(playTimes : Int, urlOrId: Any, imageView: ImageView)

    /**
     * @uurlOrId: String类型或Int类型
     * @playTimes : Int 循环次数
     */
    fun loadCircleGif(playTimes : Int, urlOrId: Any, imageView: ImageView, placeholderImg: Int)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadCornerGif(urlOrId: Any, imageView: ImageView, cornerRadius: Float)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadCornerGif(urlOrId: Any, imageView: ImageView, cornerRadius: Float, placeholderImg: Int)

    /**
     * @urlOrId: String类型或Int类型
     * @playTimes : Int 循环次数
     */
    fun loadCornerGif(playTimes : Int, urlOrId: Any, imageView: ImageView, cornerRadius: Float)

    /**
     * @urlOrId: String类型或Int类型
     * @playTimes : Int 循环次数
     */
    fun loadCornerGif(playTimes : Int, urlOrId: Any, imageView: ImageView, cornerRadius: Float, placeholderImg: Int)

    /**
     * 带边框圆角gif
     * @urlOrId: String类型或Int类型
     */
    fun loadBorderCornerGif(urlOrId: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius : Float)

    /**
     * 带边框圆角gif
     * @urlOrId: String类型或Int类型
     */
    fun loadBorderCornerGif(urlOrId: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius : Float, placeholderImg: Int)

    /**
     * 带边框圆角gif
     * @urlOrId: String类型或Int类型
     * @playTimes : Int 循环次数
     */
    fun loadBorderCornerGif(playTimes : Int, urlOrId: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius : Float)

    /**
     * 带边框圆角gif
     * @urlOrId: String类型或Int类型
     * @playTimes : Int 循环次数
     */
    fun loadBorderCornerGif(playTimes : Int, urlOrId: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius : Float, placeholderImg: Int)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadImageWithRXCustomTarget(urlOrId: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadImageWithRXCustomTarget(urlOrId: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>, placeholderImg: Int)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun load9Png(urlOrId: Any, view: View)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun load9Png(urlOrId: Any, view: View, placeholderImg: Int)


}