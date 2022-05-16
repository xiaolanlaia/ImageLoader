package com.renxing.moduleImageLoader

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import com.renxing.moduleImageLoader.imageUtils.ModuleImageConstant
import com.renxing.moduleImageLoader.loaderStrategy.control.ImageLoaderInterface
import com.renxing.moduleImageLoader.loaderStrategy.control.LoaderStrategyFactory
import com.renxing.moduleImageLoader.loaderStrategy.glide.target.RXCustomTarget

/**
 *@author  :  WuJianFeng
 */
object RXImageLoader : ImageLoaderInterface {
    private var imageLoaderStrategy = LoaderStrategyFactory.instance.getLoaderStrategy()


    override fun loadImage(urlOrId: Any, imageView: ImageView) {
    imageLoaderStrategy.loadImage(urlOrId,imageView)
    }

    override fun loadImage(urlOrId: Any, imageView: ImageView, defaultIv: Int) {
        imageLoaderStrategy.loadImage(urlOrId,imageView,defaultIv)
    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int) {
        imageLoaderStrategy.loadImage(url,imageView,width,height)
    }

    override fun loadImage(url: String, imageView: ImageView, defaultIv: Int, width: Int, height: Int) {
        imageLoaderStrategy.loadImage(url,imageView,defaultIv,width,height)
    }

    override fun loadImageWithFitCenter(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithFitCenter(urlOrId,imageView)
    }

    override fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrId,imageView)
    }

    override fun loadImageWithCenterInside(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrId,imageView)
    }

    override fun loadImageWithSkipCache(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithSkipCache(urlOrId,imageView)
    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int) {
        imageLoaderStrategy.loadImageWithSkipCache(url,imageView,width,height)
    }

    override fun loadCircleImage(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadCircleImage(urlOrId,imageView)
    }

    override fun loadRoundedCornersImage(urlOrId: Any, imageView: ImageView, radius: Float) {
        imageLoaderStrategy.loadRoundedCornersImage(urlOrId,imageView,radius)
    }

    override fun loadRoundedCornersImage(urlOrId: Any, imageView: ImageView, radius: Float, cornerType: ModuleImageConstant.CornerType) {
        imageLoaderStrategy.loadRoundedCornersImage(urlOrId,imageView,radius,cornerType)
    }

    override fun loadRoundedCornersImage(
        bitmap: Bitmap,
        imageView: ImageView,
        radius: Float,
        cornerType: ModuleImageConstant.CornerType
    ) {
        imageLoaderStrategy.loadRoundedCornersImage(bitmap,imageView,radius,cornerType)
    }

    override fun loadBorderCircleImage(urlOrId: Any, imageView: ImageView, borderColor : Int, borderWidth : Float) {
        imageLoaderStrategy.loadBorderCircleImage(urlOrId,imageView, borderColor, borderWidth)
    }

    override fun loadBorderCornerImage(
        urlOrId: Any,
        imageView: ImageView,
        borderWidth: Float,
        borderColor: Int,
        cornerWidth: Int
    ) {
        imageLoaderStrategy.loadBorderCornerImage(urlOrId,imageView, borderWidth, borderColor, cornerWidth)
    }


    override fun loadGif(urlOrId: Any, imageView: ImageView, playTimes : Int) {
        imageLoaderStrategy.loadGif(urlOrId,imageView,playTimes)
    }


    override fun loadCircleGif(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadCircleGif(urlOrId,imageView)
    }

    override fun loadCircleGif(urlOrId: Any, imageView: ImageView, playTimes: Int) {
        imageLoaderStrategy.loadCircleGif(urlOrId,imageView, playTimes)
    }

    override fun loadRoundedCornerGif(urlOrId: Any, imageView: ImageView, radius: Float) {
        imageLoaderStrategy.loadRoundedCornerGif(urlOrId,imageView, radius)
    }

    override fun loadRoundedCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        radius: Float,
        playTimes: Int
    ) {
        imageLoaderStrategy.loadRoundedCornerGif(urlOrId,imageView, radius, playTimes)
    }

    override fun loadBorderCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        borderWidth: Float,
        borderColor: Int,
        cornerWidth: Int
    ) {
        imageLoaderStrategy.loadBorderCornerGif(urlOrId,imageView, borderWidth, borderColor, cornerWidth)
    }

    override fun loadBorderCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        borderWidth: Float,
        borderColor: Int,
        cornerWidth: Int,
        playTimes: Int
    ) {
        imageLoaderStrategy.loadBorderCornerGif(urlOrId,imageView, borderWidth, borderColor, cornerWidth, playTimes)
    }

    override fun loadGif(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadGif(urlOrId,imageView)
    }

    override fun loadImageWithRXCustomTarget(urlOrId: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>) {
        imageLoaderStrategy.loadImageWithRXCustomTarget(urlOrId,context,rxCustomTarget)
    }

    override fun load9Png(urlOrId: Any, view: View) {
        imageLoaderStrategy.load9Png(urlOrId, view)
    }
}