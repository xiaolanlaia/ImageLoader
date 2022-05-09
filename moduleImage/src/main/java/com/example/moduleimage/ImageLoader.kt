package com.example.moduleimage

import android.graphics.Bitmap
import android.widget.ImageView
import com.example.moduleimage.loaderStrategy.glide.GlideRoundedCornersTransform
import com.example.moduleimage.loaderStrategy.glide.ImageLoaderGlide

class ImageLoader : ImageLoaderInterface{
    private val imageLoaderInterface = ImageLoaderGlide
    override fun loadImageUrl(url: String?, image: ImageView) {
        imageLoaderInterface.loadImageUrl(url,image)
    }

    override fun loadCircleImage(url: String, image: ImageView) {
        imageLoaderInterface.loadCircleImage(url,image)
    }

    override fun loadRoundedCornersImage(url: String, image: ImageView, radius: Float) {
        imageLoaderInterface.loadRoundedCornersImage(url,image,radius)
    }

    override fun loadRoundedCornersImage(
        url: String,
        image: ImageView,
        radius: Float,
        cornerType: GlideRoundedCornersTransform.CornerType
    ) {
        imageLoaderInterface.loadRoundedCornersImage(url,image,radius,cornerType)
    }

    override fun loadRoundedCornersImage(
        bm: Bitmap,
        image: ImageView,
        radius: Float,
        cornerType: GlideRoundedCornersTransform.CornerType
    ) {
        imageLoaderInterface.loadRoundedCornersImage(bm,image,radius,cornerType)
    }
}