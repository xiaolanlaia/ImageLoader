package com.example.moduleimage

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.request.target.CustomTarget
import com.example.moduleimage.loaderStrategy.control.ImageLoaderInterface
import com.example.moduleimage.loaderStrategy.control.LoaderStrategyFactory
import com.example.moduleimage.loaderStrategy.glide.GlideRoundedCornersTransform

object ImageLoader : ImageLoaderInterface {
    private var imageLoaderStrategy = LoaderStrategyFactory.instance.getLoaderStrategy()
    override fun loadImage(url: String, imageView: ImageView) {
    imageLoaderStrategy.loadImage(url,imageView)
    }

    override fun loadImage(id: Int, imageView: ImageView) {
        imageLoaderStrategy.loadImage(id,imageView)
    }

    override fun loadImage(url: String, imageView: ImageView, defaultIv: Int) {
        imageLoaderStrategy.loadImage(url,imageView,defaultIv)
    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int) {
        imageLoaderStrategy.loadImage(url,imageView,width,height)
    }

    override fun loadImage(
        url: String,
        imageView: ImageView,
        defaultIv: Int,
        width: Int,
        height: Int
    ) {
        imageLoaderStrategy.loadImage(url,imageView,defaultIv,width,height)
    }

    override fun loadImageWithFitCenter(url: String, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithFitCenter(url,imageView)
    }

    override fun loadImageWithFitCenter(id: Int, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithFitCenter(id,imageView)
    }

    override fun loadImageWithCenterCrop(url: String, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithCenterCrop(url,imageView)
    }

    override fun loadImageWithCenterCrop(id: Int, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithCenterCrop(id,imageView)
    }

    override fun loadImageWithCenterInside(url: String, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithCenterCrop(url,imageView)
    }

    override fun loadImageWithCenterInside(id: Int, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithCenterCrop(id,imageView)
    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView) {
        TODO("Not yet implemented")
    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int) {
        TODO("Not yet implemented")
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

    override fun loadImageWithCustomTarget(url: String, customTarget: CustomTarget<Bitmap>) {
        TODO("Not yet implemented")
    }

    override fun load9Png(url: String, imageView: ImageView, resId: Int) {
        TODO("Not yet implemented")
    }
}