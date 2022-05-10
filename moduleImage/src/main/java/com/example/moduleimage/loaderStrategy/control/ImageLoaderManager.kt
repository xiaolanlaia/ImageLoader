package com.example.moduleimage.loaderStrategy.control

import android.graphics.Bitmap
import android.widget.ImageView
import com.example.moduleimage.loaderStrategy.glide.GlideRoundedCornersTransform

class ImageLoaderManager : ImageLoaderInterface{

    lateinit var imageLoaderInterface: ImageLoaderInterface

    fun ImageLoaderManager(imageLoaderInterface: ImageLoaderInterface){
        this.imageLoaderInterface = imageLoaderInterface
    }

    override fun loadImageUrl(url: String?, imageView: ImageView) {
        imageLoaderInterface.loadImageUrl(url,imageView)
    }

    override fun loadCircleImage(url: String, imageView: ImageView) {
        imageLoaderInterface.loadCircleImage(url,imageView)
    }

    override fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float) {
        imageLoaderInterface.loadRoundedCornersImage(url,imageView,radius)
    }

    override fun loadRoundedCornersImage(
        url: String,
        imageView: ImageView,
        radius: Float,
        cornerType: GlideRoundedCornersTransform.CornerType
    ) {
        imageLoaderInterface.loadRoundedCornersImage(url,imageView,radius,cornerType)
    }

    override fun loadRoundedCornersImage(
        bitmap: Bitmap,
        imageView: ImageView,
        radius: Float,
        cornerType: GlideRoundedCornersTransform.CornerType
    ) {
        imageLoaderInterface.loadRoundedCornersImage(bitmap,imageView,radius,cornerType)
    }
}