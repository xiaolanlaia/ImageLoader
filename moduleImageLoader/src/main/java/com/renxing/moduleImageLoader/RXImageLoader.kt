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

    override fun loadImage(url: String, imageView: ImageView, defaultIv: Int, width: Int, height: Int) {
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

    override fun loadCircleImage(id: Int, imageView: ImageView) {
        imageLoaderStrategy.loadCircleImage(id,imageView)
    }

    override fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float) {
        imageLoaderStrategy.loadRoundedCornersImage(url,imageView,radius)
    }

    override fun loadRoundedCornersImage(id: Int, imageView: ImageView, radius: Float) {
        imageLoaderStrategy.loadRoundedCornersImage(id,imageView,radius)
    }

    override fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float, cornerType: ModuleImageConstant.CornerType) {
        imageLoaderStrategy.loadRoundedCornersImage(url,imageView,radius,cornerType)
    }

    override fun loadRoundedCornersImage(
        id: Int,
        imageView: ImageView,
        radius: Float,
        cornerType: ModuleImageConstant.CornerType
    ) {
        imageLoaderStrategy.loadRoundedCornersImage(id,imageView,radius,cornerType)
    }

    override fun loadRoundedCornersImage(
        bitmap: Bitmap,
        imageView: ImageView,
        radius: Float,
        cornerType: ModuleImageConstant.CornerType
    ) {
        imageLoaderStrategy.loadRoundedCornersImage(bitmap,imageView,radius,cornerType)
    }

    override fun loadBorderCircleImage(url: String, imageView: ImageView, borderColor : Int, borderWidth : Float) {
        imageLoaderStrategy.loadBorderCircleImage(url,imageView, borderColor, borderWidth)
    }

    override fun loadBorderCircleImage(id: Int, imageView: ImageView, borderColor : Int, borderWidth : Float) {
        imageLoaderStrategy.loadBorderCircleImage(id,imageView, borderColor, borderWidth)
    }

    override fun loadBorderRoundImage(
        id: Int,
        imageView: ImageView,
        borderWidth: Float,
        borderColor: Int,
        cornerWidth: Int
    ) {
        imageLoaderStrategy.loadBorderRoundImage(id,imageView, borderWidth, borderColor, cornerWidth)
    }

    override fun loadBorderRoundImage(url: String, imageView: ImageView, borderWidth: Float, borderColor: Int, cornerWidth : Int) {
        imageLoaderStrategy.loadBorderRoundImage(url,imageView, borderWidth, borderColor, cornerWidth)
    }

    override fun loadGif(url: String, imageView: ImageView, playTimes : Int) {
        imageLoaderStrategy.loadGif(url,imageView,playTimes)
    }

    override fun loadGif(id: Int, imageView: ImageView, playTimes : Int) {
        imageLoaderStrategy.loadGif(id,imageView)
    }

    override fun loadCircleGif(url: String, imageView: ImageView) {
        imageLoaderStrategy.loadCircleGif(url,imageView)
    }

    override fun loadCircleGif(url: String, imageView: ImageView, playTimes: Int) {
        imageLoaderStrategy.loadCircleGif(url,imageView, playTimes)
    }

    override fun loadCircleGif(id: Int, imageView: ImageView) {
        imageLoaderStrategy.loadCircleGif(id,imageView)
    }

    override fun loadCircleGif(id: Int, imageView: ImageView, playTimes: Int) {
        imageLoaderStrategy.loadCircleGif(id,imageView, playTimes)
    }

    override fun loadRoundedCornerGif(url: String, imageView: ImageView, radius: Float) {
        imageLoaderStrategy.loadRoundedCornerGif(url,imageView, radius)
    }

    override fun loadRoundedCornerGif(
        url: String,
        imageView: ImageView,
        radius: Float,
        playTimes: Int
    ) {
        imageLoaderStrategy.loadRoundedCornerGif(url,imageView, radius, playTimes)
    }

    override fun loadRoundedCornerGif(id: Int, imageView: ImageView, radius: Float) {
        imageLoaderStrategy.loadRoundedCornerGif(id,imageView, radius)
    }

    override fun loadRoundedCornerGif(
        id: Int,
        imageView: ImageView,
        radius: Float,
        playTimes: Int
    ) {
        imageLoaderStrategy.loadRoundedCornerGif(id,imageView, radius, playTimes)
    }

    override fun loadGif(url: String, imageView: ImageView) {
        imageLoaderStrategy.loadGif(url,imageView)
    }

    override fun loadGif(id: Int, imageView: ImageView) {
        imageLoaderStrategy.loadGif(id,imageView)
    }

    override fun loadImageWithRXCustomTarget(url: String, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>) {
        imageLoaderStrategy.loadImageWithRXCustomTarget(url,context,rxCustomTarget)
    }

    override fun loadImageWithRXCustomTarget(
        id: Int,
        context: Context,
        rxCustomTarget: RXCustomTarget<Bitmap>
    ) {
        imageLoaderStrategy.loadImageWithRXCustomTarget(id,context,rxCustomTarget)
    }

    override fun load9Png(url: String, view: View) {
        imageLoaderStrategy.load9Png(url, view)
    }

    override fun load9Png(id: Int, view: View) {
        imageLoaderStrategy.load9Png(id, view)
    }
}