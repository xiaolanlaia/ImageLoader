package com.example.moduleimage

import android.graphics.Bitmap
import android.widget.ImageView
import com.example.moduleimage.loaderStrategy.glide.GlideRoundedCornersTransform

interface ImageLoaderInterface {
    /**
     * 加载图片
     * @url
     */
    fun loadImageUrl(url: String?, image: ImageView)

    /**
     * 圆形图
     * @url
     */
    fun loadCircleImage(url: String, image: ImageView)

    /**
     * 圆角图
     * @url
     */
    fun loadRoundedCornersImage(url: String, image: ImageView, radius: Float)

    /**
     * 指定圆角边的圆角图
     * @url
     */
    fun loadRoundedCornersImage(url: String, image: ImageView, radius: Float, cornerType: GlideRoundedCornersTransform.CornerType)
    /**
     * 指定圆角边的圆角图
     * @bm
     */
    fun loadRoundedCornersImage(bm: Bitmap, image: ImageView, radius: Float, cornerType: GlideRoundedCornersTransform.CornerType)
}