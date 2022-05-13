package com.renxing.moduleImageLoader

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.request.target.CustomTarget
import com.renxing.moduleImageLoader.imageUtils.CornerType
import com.renxing.moduleImageLoader.loaderStrategy.control.LoaderStrategyFactory
import com.renxing.moduleImageLoader.loaderStrategy.glide.GlideRoundedCornersTransform

object ImageLoader : ImageLoaderInterface {
    private var imageLoaderStrategy = LoaderStrategyFactory.instance.getLoaderStrategy()

    /**
     * 用来改变图片加载框架策略
     */
    fun changeLoaderStrategy(strategyTypeEnum: LoaderStrategyFactory.StrategyTypeEnum){
        imageLoaderStrategy = LoaderStrategyFactory.instance.getLoaderStrategy(strategyTypeEnum)
    }
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
        imageLoaderStrategy.loadImageWithSkipCache(url,imageView)
    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int) {
        imageLoaderStrategy.loadImageWithSkipCache(url,imageView,width,height)
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
        cornerType: CornerType
    ) {
        imageLoaderStrategy.loadRoundedCornersImage(url,imageView,radius,cornerType)
    }

    override fun loadRoundedCornersImage(
        bitmap: Bitmap,
        imageView: ImageView,
        radius: Float,
        cornerType: CornerType
    ) {
        imageLoaderStrategy.loadRoundedCornersImage(bitmap,imageView,radius,cornerType)
    }

    override fun loadCircleImageWithBorder(url: String, imageView: ImageView, borderColor : Int, borderWidth : Float) {
        imageLoaderStrategy.loadCircleImageWithBorder(url,imageView, borderColor, borderWidth)
    }

    override fun loadCircleImageWithBorder(id: Int, imageView: ImageView, borderColor : Int, borderWidth : Float) {
        imageLoaderStrategy.loadCircleImageWithBorder(id,imageView, borderColor, borderWidth)
    }


    override fun loadGif(url: String, imageView: ImageView) {
        imageLoaderStrategy.loadGif(url,imageView)
    }

    override fun loadGif(id: Int, imageView: ImageView) {
        imageLoaderStrategy.loadGif(id,imageView)
    }

    override fun loadGifWithLoop(url: String, imageView: ImageView) {
        imageLoaderStrategy.loadGifWithLoop(url,imageView)
    }

    override fun loadGifWithLoop(id: Int, imageView: ImageView) {
        imageLoaderStrategy.loadGifWithLoop(id,imageView)
    }

    override fun loadImageWithCustomTarget(context: Context, url: String, imageView: ImageView, width : Int, height : Int) {
        imageLoaderStrategy.loadImageWithCustomTarget(context,url,imageView, width, height)
    }

    override fun load9Png(url: String, imageView: ImageView) {
        imageLoaderStrategy.load9Png(url,imageView)
    }
    override fun load9Png(id: Int, imageView: ImageView) {
        imageLoaderStrategy.load9Png(id,imageView)
    }
}