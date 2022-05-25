package com.renxing.moduleImageLoader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.renxing.moduleImageLoader.imageUtils.enumUtils.CornerTypeEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.DiskCacheStrategyEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.PriorityEnum
import com.renxing.moduleImageLoader.loaderStrategy.control.ImageLoaderInterface
import com.renxing.moduleImageLoader.loaderStrategy.control.LoaderStrategyFactory
import com.renxing.moduleImageLoader.loaderStrategy.control.OnAnimationStatus
import com.renxing.moduleImageLoader.loaderStrategy.control.RXRequestListener
import com.renxing.moduleImageLoader.loaderStrategy.glide.easyglide.config.ImgConfigImpl
import com.renxing.moduleImageLoader.loaderStrategy.glide.target.RXCustomTarget

/**
 *@author  :  WuJianFeng
 */
object RXImageLoader : ImageLoaderInterface {
    private var imageLoaderStrategy = LoaderStrategyFactory.instance.getLoaderStrategy()




    override fun loadImage(context: Context, imgConfigImpl: ImgConfigImpl) {
        imageLoaderStrategy.loadImage(context,imgConfigImpl)
    }



    override fun loadImage(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        progressView: View,
        placeholderId: Int,
        errorHolder: Int
    ) {
        imageLoaderStrategy.loadImage(urlOrIdOrUri,imageView,progressView,placeholderId,errorHolder)
    }

    override fun loadGif(urlOrIdOrUri: Any, imageView: ImageView) {
        imageLoaderStrategy.loadGif(urlOrIdOrUri,imageView)
    }

    override fun loadGif(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadGif(urlOrIdOrUri,imageView,diskCacheStrategy)
    }

    override fun loadGif(urlOrIdOrUri: Any, imageView: ImageView, placeholderId: Int) {
        imageLoaderStrategy.loadGif(urlOrIdOrUri,imageView,placeholderId)
    }

    override fun loadGif(playTimes : Int, urlOrIdOrUri: Any, imageView: ImageView, ) {
        imageLoaderStrategy.loadGif(playTimes,urlOrIdOrUri,imageView)
    }

    override fun loadGif(
        playTimes: Int,
        urlOrIdOrUri: Any,
        imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum,
        onAnimationStatus: OnAnimationStatus
    ) {
        imageLoaderStrategy.loadGif(playTimes,urlOrIdOrUri,imageView,diskCacheStrategy,onAnimationStatus)
    }

    override fun loadGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView, placeholderId: Int) {
        imageLoaderStrategy.loadGif(playTimes, urlOrIdOrUri,imageView,placeholderId)
    }

    override fun loadCircleGif(urlOrIdOrUri: Any, imageView: ImageView) {
        imageLoaderStrategy.loadCircleGif(urlOrIdOrUri,imageView)
    }

    override fun loadCircleGif(urlOrIdOrUri: Any, imageView: ImageView, placeholderId: Int) {
        imageLoaderStrategy.loadCircleGif(urlOrIdOrUri,imageView, placeholderId)
    }
    override fun loadCircleGif(playTimes: Int,urlOrIdOrUri: Any, imageView: ImageView) {
        imageLoaderStrategy.loadCircleGif(playTimes,urlOrIdOrUri,imageView)
    }

    override fun loadCircleGif(
        playTimes: Int,
        urlOrIdOrUri: Any,
        imageView: ImageView,
        placeholderId: Int
    ) {
        imageLoaderStrategy.loadCircleGif(playTimes,urlOrIdOrUri,imageView, placeholderId)
    }

    override fun loadCornerGif(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float) {
        imageLoaderStrategy.loadCornerGif(urlOrIdOrUri,imageView, cornerRadius)
    }


    override fun loadCornerGif(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderId: Int
    ) {
        imageLoaderStrategy.loadCornerGif(urlOrIdOrUri,imageView, cornerRadius,placeholderId)
    }
    override fun loadCornerGif(
        playTimes: Int,
        urlOrIdOrUri: Any,
        imageView: ImageView,
        cornerRadius: Float,
    ) {
        imageLoaderStrategy.loadCornerGif(playTimes,urlOrIdOrUri,imageView, cornerRadius)
    }

    override fun loadCornerGif(
        playTimes: Int,
        urlOrIdOrUri: Any,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderId: Int
    ) {
        imageLoaderStrategy.loadCornerGif(playTimes,urlOrIdOrUri,imageView, cornerRadius, placeholderId)
    }

    override fun loadBorderCornerGif(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerRadius: Float
    ) {
        imageLoaderStrategy.loadBorderCornerGif(urlOrIdOrUri,imageView, borderColor, borderWidth, cornerRadius)
    }

    override fun loadBorderCornerGif(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerRadius: Float,
        placeholderId: Int
    ) {
        imageLoaderStrategy.loadBorderCornerGif(urlOrIdOrUri,imageView, borderColor, borderWidth, cornerRadius,placeholderId)
    }

    override fun loadBorderCornerGif(
        playTimes: Int,
        urlOrIdOrUri: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerRadius: Float
    ) {
        imageLoaderStrategy.loadBorderCornerGif(playTimes,urlOrIdOrUri,imageView, borderColor, borderWidth, cornerRadius)
    }

    override fun loadBorderCornerGif(
        playTimes: Int,
        urlOrIdOrUri: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerRadius: Float,
        placeholderId: Int
    ) {
        imageLoaderStrategy.loadBorderCornerGif(playTimes,urlOrIdOrUri,imageView, borderColor, borderWidth, cornerRadius, placeholderId)
    }

    override fun loadImageWithRXCustomTarget(urlOrIdOrUri: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>) {
        imageLoaderStrategy.loadImageWithRXCustomTarget(urlOrIdOrUri,context,rxCustomTarget)
    }

    override fun loadImageWithRXCustomTarget(
        urlOrIdOrUri: Any,
        context: Context,
        thumbnailWidth: Int,
        thumbnailHeight: Int,
        rxCustomTarget: RXCustomTarget<Bitmap>
    ) {
        imageLoaderStrategy.loadImageWithRXCustomTarget(urlOrIdOrUri,context,thumbnailWidth,thumbnailHeight,rxCustomTarget)
    }

    override fun loadImageWithRXCustomTarget(
        urlOrIdOrUri: Any,
        context: Context,
        rxCustomTarget: RXCustomTarget<Bitmap>,
        placeholderId: Int
    ) {
        imageLoaderStrategy.loadImageWithRXCustomTarget(urlOrIdOrUri,context,rxCustomTarget,placeholderId)
    }

    override fun loadImageWithRXCustomTarget(
        urlOrIdOrUri: Any,
        context: Context,
        rxCustomTarget: RXCustomTarget<Bitmap>,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadImageWithRXCustomTarget(urlOrIdOrUri,context,rxCustomTarget,diskCacheStrategy)
    }

    override fun load9Png(urlOrIdOrUri: Any, view: View) {
        imageLoaderStrategy.load9Png(urlOrIdOrUri, view)
    }

    override fun load9Png(urlOrIdOrUri: Any, view: View, placeholderId: Int) {
        imageLoaderStrategy.load9Png(urlOrIdOrUri, view,placeholderId)
    }

    override fun clearImageDiskCache(context: Context) {
        imageLoaderStrategy.clearImageDiskCache(context)
    }

    override fun clearImageMemoryCache(context: Context) {
        imageLoaderStrategy.clearImageMemoryCache(context)
    }

    override fun clear(context: Context, imageView: ImageView) {
        imageLoaderStrategy.clear(context,imageView)
    }

    override fun pauseRequests(context: Context) {
        imageLoaderStrategy.pauseRequests(context)
    }

    override fun resumeRequests(context: Context) {
        imageLoaderStrategy.resumeRequests(context)
    }

    override fun clearMemory(context: Context) {
        imageLoaderStrategy.clearMemory(context)
    }

    override fun trimMemory(context: Context, level: Int) {
        imageLoaderStrategy.trimMemory(context, level)
    }
}