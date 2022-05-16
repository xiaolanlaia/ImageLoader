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
     * @url: String
     */
    fun loadImage(url: String, imageView: ImageView)
    /**
     * @id: Int 图片id
     */
    fun loadImage(id: Int, imageView: ImageView)

    /**
     * @url: String
     * @defaultIv: Int 默认图
     */
    fun loadImage(url: String, imageView: ImageView, defaultIv: Int)

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
     * @url: String
     */
    fun loadImageWithFitCenter(url: String, imageView: ImageView)

    /**
     * @id: Int 图片id
     */
    fun loadImageWithFitCenter(id: Int, imageView: ImageView)
    /**
     * @url: String
     */
    fun loadImageWithCenterCrop(url: String, imageView: ImageView)
    /**
     * @id: Int 图片id
     */
    fun loadImageWithCenterCrop(id: Int, imageView: ImageView)

    /**
     * @url: String
     */
    fun loadImageWithCenterInside(url: String, imageView: ImageView)
    /**
     * @id: Int 图片id
     */
    fun loadImageWithCenterInside(id: Int, imageView: ImageView)

    /**
     * @url: String
     */
    fun loadImageWithSkipCache(url: String, imageView: ImageView)

    /**
     * @url: String
     */
    fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int)

    /**
     * 圆形图
     * @id: Int
     */
    fun loadCircleImage(url: String, imageView: ImageView)
    /**
     * 圆形图
     * @url: String
     */
    fun loadCircleImage(id: Int, imageView: ImageView)

    /**
     * 圆角图
     * @url: String
     */
    fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float)

    /**
     * 圆角图
     * @id: Int
     */
    fun loadRoundedCornersImage(id: Int, imageView: ImageView, radius: Float)

    /**
     * 指定圆角边的圆角图
     * @url: String
     */
    fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float, cornerType: ModuleImageConstant.CornerType)
    /**
     * 指定圆角边的圆角图
     * @id: Int
     */
    fun loadRoundedCornersImage(id: Int, imageView: ImageView, radius: Float, cornerType: ModuleImageConstant.CornerType)
    /**
     * 指定圆角边的圆角图
     * @bitmap: Bitmap
     */
    fun loadRoundedCornersImage(bitmap: Bitmap, imageView: ImageView, radius: Float, cornerType: ModuleImageConstant.CornerType)

    /**
     * 带边框圆形图
     * url: String
     */
    fun loadBorderCircleImage(url: String, imageView: ImageView, borderColor : Int, borderWidth : Float)
    /**
     * 带边框圆形图
     * id: Int
     */
    fun loadBorderCircleImage(id: Int, imageView: ImageView, borderColor : Int, borderWidth : Float)

    /**
     * 带边框圆角图
     * id: Int
     */
    fun loadBorderRoundImage(id: Int, imageView: ImageView, borderWidth: Float, borderColor: Int, cornerWidth : Int)

    /**
     * 带边框圆角图
     * url: String
     */
    fun loadBorderRoundImage(url: String, imageView: ImageView, borderWidth: Float, borderColor: Int, cornerWidth : Int)

    /**
     * @url: String
     */
    fun loadGif(url: String, imageView: ImageView)

    /**
     * @url: String
     */
    fun loadGif(url: String, imageView: ImageView, playTimes : Int)

    /**
     * @id: Int
     */
    fun loadGif(id: Int, imageView: ImageView)

    /**
     * @id: Int
     */
    fun loadGif(id: Int, imageView: ImageView, playTimes : Int)

    /**
     * @url: String
     */
    fun loadCircleGif(url: String, imageView: ImageView)

    /**
     * @url: String
     */
    fun loadCircleGif(url: String, imageView: ImageView, playTimes : Int)

    /**
     * @id: Int
     */
    fun loadCircleGif(id: Int, imageView: ImageView)

    /**
     * @id: Int
     */
    fun loadCircleGif(id: Int, imageView: ImageView, playTimes : Int)

    /**
     * @url: String
     */
    fun loadRoundedCornerGif(url: String, imageView: ImageView, radius: Float)

    /**
     * @url: String
     */
    fun loadRoundedCornerGif(url: String, imageView: ImageView, radius: Float, playTimes : Int)

    /**
     * @id: Int
     */
    fun loadRoundedCornerGif(id: Int, imageView: ImageView, radius: Float)

    /**
     * @id: Int
     */
    fun loadRoundedCornerGif(id: Int, imageView: ImageView, radius: Float, playTimes : Int)

    /**
     * @url: String
     */
    fun loadImageWithRXCustomTarget(url: String, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>)

    /**
     * @id: Int
     */
    fun loadImageWithRXCustomTarget(id: Int, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>)

    /**
     * @url: String
     */
    fun load9Png(url: String, view: View)

    /**
     * @id: Int
     */
    fun load9Png(id: Int, view: View)


}