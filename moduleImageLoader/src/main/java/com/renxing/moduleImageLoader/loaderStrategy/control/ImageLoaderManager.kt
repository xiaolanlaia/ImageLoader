package com.renxing.moduleImageLoader.loaderStrategy.control

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.target.CustomTarget
import com.renxing.moduleImageLoader.ImageLoaderInterface
import com.renxing.moduleImageLoader.loaderStrategy.glide.GlideRoundedCornersTransform

class ImageLoaderManager : ImageLoaderInterface {

    lateinit var imageLoaderInterface: ImageLoaderInterface

    fun ImageLoaderManager(imageLoaderInterface: ImageLoaderInterface){
        this.imageLoaderInterface = imageLoaderInterface
    }

    override fun loadImage(url: String, imageView: ImageView) {
        imageLoaderInterface.loadImage(url,imageView)
    }

    override fun loadImage(id: Int, imageView: ImageView) {
        TODO("Not yet implemented")
    }

    override fun loadImage(url: String, imageView: ImageView, defaultIv: Int) {
        TODO("Not yet implemented")
    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun loadImage(
        url: String,
        imageView: ImageView,
        defaultIv: Int,
        width: Int,
        height: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun loadImageWithFitCenter(url: String, imageView: ImageView) {
        TODO("Not yet implemented")
    }

    override fun loadImageWithFitCenter(id: Int, imageView: ImageView) {
        TODO("Not yet implemented")
    }

    override fun loadImageWithCenterCrop(url: String, imageView: ImageView) {
        TODO("Not yet implemented")
    }

    override fun loadImageWithCenterCrop(id: Int, imageView: ImageView) {
        TODO("Not yet implemented")
    }

    override fun loadImageWithCenterInside(url: String, imageView: ImageView) {
        TODO("Not yet implemented")
    }

    override fun loadImageWithCenterInside(id: Int, imageView: ImageView) {
        TODO("Not yet implemented")
    }


    override fun loadImageWithSkipCache(url: String, imageView: ImageView) {
        TODO("Not yet implemented")
    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int) {
        TODO("Not yet implemented")
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

    override fun loadGif(url: String, imageView: ImageView) {
        TODO("Not yet implemented")
    }

    override fun loadGif(id: Int, imageView: ImageView) {
        TODO("Not yet implemented")
    }

    override fun loadGifWithLoop(url: String, imageView: ImageView) {
        TODO("Not yet implemented")
    }

    override fun loadGifWithLoop(id: Int, imageView: ImageView) {
        TODO("Not yet implemented")
    }

    override fun loadImageWithCustomTarget(context : Context, url: String, customTarget: CustomTarget<Bitmap>) {
        TODO("Not yet implemented")
    }

    override fun load9Png(url: String, imageView: ImageView) {
        TODO("Not yet implemented")
    }

    override fun load9Png(context: Context, id: Int, imageView: ImageView) {
        TODO("Not yet implemented")
    }
}