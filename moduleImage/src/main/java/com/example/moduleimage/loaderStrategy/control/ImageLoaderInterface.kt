package com.example.moduleimage.loaderStrategy.control

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.request.target.CustomTarget
import com.example.moduleimage.loaderStrategy.glide.GlideRoundedCornersTransform
import com.example.moduleimage.loaderStrategy.glide.ImageLoaderGlide

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
     * @url: String
     */
    fun loadCircleImage(url: String, imageView: ImageView)

    /**
     * 圆角图
     * @url: String
     */
    fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float)

    /**
     * 指定圆角边的圆角图
     * @url: String
     */
    fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float, cornerType: GlideRoundedCornersTransform.CornerType)
    /**
     * 指定圆角边的圆角图
     * @bitmap: Bitmap
     */
    fun loadRoundedCornersImage(bitmap: Bitmap, imageView: ImageView, radius: Float, cornerType: GlideRoundedCornersTransform.CornerType)

    /**
     * @url: String
     */
    fun loadGif(url: String, imageView: ImageView)

    /**
     * @id: Int
     */
    fun loadGif(id: Int, imageView: ImageView)

    /**
     * @url: String
     */
    fun loadGifWithLoop(url: String, imageView: ImageView)

    /**
     * @id: Int
     */
    fun loadGifWithLoop(id: Int, imageView: ImageView)

    /**
     * @url: String
     * @customTarget: CustomTarget<Bitmap>
     */
    fun loadImageWithCustomTarget(url: String, customTarget: CustomTarget<Bitmap>)

    fun load9Png(url: String, imageView: ImageView, resId: Int)


}