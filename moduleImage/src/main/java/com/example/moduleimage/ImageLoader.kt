package com.example.moduleimage

import android.graphics.Bitmap
import android.widget.ImageView
import com.example.moduleimage.loaderStrategy.control.ImageLoaderInterface
import com.example.moduleimage.loaderStrategy.control.LoaderStrategyFactory
import com.example.moduleimage.loaderStrategy.glide.GlideRoundedCornersTransform

object ImageLoader : ImageLoaderInterface {
    private var imageLoaderStrategy = LoaderStrategyFactory.instance.getLoaderStrategy()
    override fun loadImageUrl(url: String?, imageView: ImageView) {
    imageLoaderStrategy.loadImageUrl(url,imageView)
    }

    override fun loadCircleImage(url: String, imageView: ImageView) {
        imageLoaderStrategy.loadCircleImage(url,imageView)
    }

    override fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float) {
        imageLoaderStrategy.loadRoundedCornersImage(url,imageView,radius)
    }

    override fun loadRoundedCornersImage(
        url: String,
        imageView: ImageView,
        radius: Float,
        cornerType: GlideRoundedCornersTransform.CornerType
    ) {
        imageLoaderStrategy.loadRoundedCornersImage(url,imageView,radius,cornerType)
    }

    override fun loadRoundedCornersImage(
        bitmap: Bitmap,
        imageView: ImageView,
        radius: Float,
        cornerType: GlideRoundedCornersTransform.CornerType
    ) {
        imageLoaderStrategy.loadRoundedCornersImage(bitmap,imageView,radius,cornerType)
    }
}