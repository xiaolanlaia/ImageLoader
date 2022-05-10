package com.example.moduleimage.loaderStrategy.control

import android.graphics.Bitmap
import android.widget.ImageView
import com.example.moduleimage.loaderStrategy.glide.GlideRoundedCornersTransform

interface ImageLoaderInterface {
    /**
     * 加载图片
     * @url
     */
    fun loadImageUrl(url: String?, imageView: ImageView)

    /**
     * 圆形图
     * @url
     */
    fun loadCircleImage(url: String, imageView: ImageView)

    /**
     * 圆角图
     * @url
     */
    fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float)

    /**
     * 指定圆角边的圆角图
     * @url
     */
    fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float, cornerType: GlideRoundedCornersTransform.CornerType)
    /**
     * 指定圆角边的圆角图
     * @bm
     */
    fun loadRoundedCornersImage(bitmap: Bitmap, imageView: ImageView, radius: Float, cornerType: GlideRoundedCornersTransform.CornerType)
}