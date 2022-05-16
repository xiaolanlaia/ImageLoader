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
     * @defaultIv: Int 默认图
     */
    fun loadImage(urlOrId: Any, imageView: ImageView, defaultIv: Int)

    /**
     * @url: String
     * @width: Int 指定宽度
     * @height: Int 指定高度
     */


    fun loadImage(url: String, imageView: ImageView, width: Int, height: Int)

    /**
     * @url: String
     * @defaultIv: Int 默认图
     * @width: Int 指定宽度
     * @height: Int 指定高度
     */
    fun loadImage(url: String, imageView: ImageView, defaultIv: Int, width: Int, height: Int)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadImageWithFitCenter(urlOrId: Any, imageView: ImageView)
    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadImageWithCenterInside(urlOrId: Any, imageView: ImageView)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadImageWithSkipCache(urlOrId: Any, imageView: ImageView)

    /**
     * @url: String
     */
    fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int)

    /**
     * 圆形图
     * @urlOrId: String类型或Int类型
     */
    fun loadCircleImage(urlOrId: Any, imageView: ImageView)

    /**
     * 圆角图
     * @urlOrId: String类型或Int类型
     */
    fun loadRoundedCornersImage(urlOrId: Any, imageView: ImageView, radius: Float)

    /**
     * 指定圆角边的圆角图
     * @urlOrId: String类型或Int类型
     */
    fun loadRoundedCornersImage(urlOrId: Any, imageView: ImageView, radius: Float, cornerType: ModuleImageConstant.CornerType)
    /**
     * 指定圆角边的圆角图
     * @bitmap: Bitmap
     */
    fun loadRoundedCornersImage(bitmap: Bitmap, imageView: ImageView, radius: Float, cornerType: ModuleImageConstant.CornerType)

    /**
     * 带边框圆形图
     * urlOrId: String类型或Int类型
     */
    fun loadBorderCircleImage(urlOrId: Any, imageView: ImageView, borderColor : Int, borderWidth : Float)

    /**
     * 带边框圆角图
     * urlOrId: String类型或Int类型
     */
    fun loadBorderCornerImage(urlOrId: Any, imageView: ImageView, borderWidth: Float, borderColor: Int, cornerWidth : Int)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadGif(urlOrId: Any, imageView: ImageView)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadGif(urlOrId: Any, imageView: ImageView, playTimes : Int)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadCircleGif(urlOrId: Any, imageView: ImageView)

    /**
     * @uurlOrId: String类型或Int类型
     */
    fun loadCircleGif(urlOrId: Any, imageView: ImageView, playTimes : Int)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadRoundedCornerGif(urlOrId: Any, imageView: ImageView, radius: Float)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadRoundedCornerGif(urlOrId: Any, imageView: ImageView, radius: Float, playTimes : Int)

    /**
     * 带边框圆角gif
     * @urlOrId: String类型或Int类型
     */
    fun loadBorderCornerGif(urlOrId: Any, imageView: ImageView, borderWidth: Float, borderColor: Int, cornerWidth : Int)

    /**
     * 带边框圆角gif
     * @urlOrId: String类型或Int类型
     */
    fun loadBorderCornerGif(urlOrId: Any, imageView: ImageView, borderWidth: Float, borderColor: Int, cornerWidth : Int, playTimes: Int)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun loadImageWithRXCustomTarget(urlOrId: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>)

    /**
     * @urlOrId: String类型或Int类型
     */
    fun load9Png(urlOrId: Any, view: View)


}