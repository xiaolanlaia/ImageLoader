package com.renxing.moduleImageLoader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.renxing.moduleImageLoader.imageUtils.enumUtils.CornerTypeEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.DiskCacheStrategyEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.PriorityEnum
import com.renxing.moduleImageLoader.loaderStrategy.control.ImageLoaderInterface
import com.renxing.moduleImageLoader.loaderStrategy.control.LoaderStrategyFactory
import com.renxing.moduleImageLoader.loaderStrategy.control.OnAnimationStatus
import com.renxing.moduleImageLoader.loaderStrategy.control.RXRequestListener
import com.renxing.moduleImageLoader.loaderStrategy.glide.target.RXCustomTarget

/**
 *@author  :  WuJianFeng
 */
object RXImageLoader : ImageLoaderInterface {
    private var imageLoaderStrategy = LoaderStrategyFactory.instance.getLoaderStrategy()


    override fun loadImage(urlOrIdOrUri: Any, imageView: ImageView) {
    imageLoaderStrategy.loadImage(urlOrIdOrUri,imageView)
    }

    override fun loadImage(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        rxRequestListener: RXRequestListener<Drawable>
    ) {
        imageLoaderStrategy.loadImage(urlOrIdOrUri,imageView,rxRequestListener)
    }

    override fun loadImage(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadImage(urlOrIdOrUri,imageView,placeholderImg)
    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int) {
        imageLoaderStrategy.loadImage(url,imageView,width,height)
    }

    override fun loadImage(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        progressView: View,
        placeholderImg: Int,
        errorHolder: Int
    ) {
        imageLoaderStrategy.loadImage(urlOrIdOrUri,imageView,progressView,placeholderImg,errorHolder)
    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int, placeholderImg: Int) {
        imageLoaderStrategy.loadImage(url,imageView,width,height, placeholderImg)
    }

    override fun loadImageWithFitCenter(urlOrIdOrUri: Any, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithFitCenter(urlOrIdOrUri,imageView)
    }

    override fun loadImageWithFitCenter(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadImageWithFitCenter(urlOrIdOrUri,imageView, placeholderImg)
    }

    override fun loadImageWithFitCenter(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategyEnum: DiskCacheStrategyEnum,
        priorityEnum: PriorityEnum
    ) {
        imageLoaderStrategy.loadImageWithFitCenter(urlOrIdOrUri,imageView, placeholderImg,diskCacheStrategyEnum,priorityEnum)
    }

    override fun loadImageWithCenterCrop(urlOrIdOrUri: Any, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrIdOrUri,imageView)
    }

    override fun loadImageWithCenterCrop(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrIdOrUri,imageView, placeholderImg)
    }

    override fun loadImageWithCenterCrop(urlOrIdOrUri: Any, imageView: ImageView,diskCacheStrategy: DiskCacheStrategyEnum) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrIdOrUri,imageView,diskCacheStrategy)
    }

    override fun loadImageWithCenterCrop(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        placeholderImg: Int,
        transition: Boolean
    ) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrIdOrUri,imageView,placeholderImg,transition)

    }

    override fun loadImageWithCenterCrop(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int,
                                         diskCacheStrategy : DiskCacheStrategyEnum,
                                         transition : Boolean) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrIdOrUri,imageView, placeholderImg,diskCacheStrategy,transition)
    }

    override fun loadImageWithCenterCrop(
        urlOrIdOrUri: Any,
        view: View,
        thumbnail: Boolean,
        thumbnailWidth: Int,
        thumbnailHeight: Int
    ) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrIdOrUri,view, thumbnail,thumbnailWidth,thumbnailHeight)
    }

    override fun loadImageWithCenterCrop(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrIdOrUri,imageView, placeholderImg,diskCacheStrategy)
    }

    override fun loadImageWithCenterCrop(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum,
        thumbnail: Boolean,
        transition: Boolean,
        thumbnailWidth : Int,
        thumbnailHeight : Int
    ) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrIdOrUri,imageView, placeholderImg,diskCacheStrategy,thumbnail,transition,thumbnailWidth,thumbnailHeight)
    }

    override fun loadImageWithCenterInside(urlOrIdOrUri: Any, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithCenterInside(urlOrIdOrUri,imageView)
    }

    override fun loadImageWithCenterInside(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadImageWithCenterInside(urlOrIdOrUri,imageView, placeholderImg)
    }

    override fun loadImageWithCenterInside(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum,
        transition: Boolean
    ) {
        imageLoaderStrategy.loadImageWithCenterInside(urlOrIdOrUri,imageView, placeholderImg,diskCacheStrategy,transition)
    }

    override fun loadImageWithCenterInside(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadImageWithCenterInside(urlOrIdOrUri,imageView, placeholderImg,diskCacheStrategy)
    }

    override fun loadImageWithCenterInside(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        placeholderImg: Int,
        transition: Boolean
    ) {
        imageLoaderStrategy.loadImageWithCenterInside(urlOrIdOrUri,imageView, placeholderImg,transition)
    }

    override fun loadImageWithSkipCache(urlOrIdOrUri: Any, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithSkipCache(urlOrIdOrUri,imageView)
    }

    override fun loadImageWithSkipCache(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadImageWithSkipCache(urlOrIdOrUri,imageView, placeholderImg)
    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int) {
        imageLoaderStrategy.loadImageWithSkipCache(url,imageView,width,height)
    }

    override fun loadImageWithSkipCache(
        url: String,
        imageView: ImageView,
        width: Int,
        height: Int,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadImageWithSkipCache(url,imageView,width,height,placeholderImg)
    }

    override fun loadCircleImage(urlOrIdOrUri: Any, imageView: ImageView) {
        imageLoaderStrategy.loadCircleImage(urlOrIdOrUri,imageView)
    }

    override fun loadCircleImage(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadCircleImage(urlOrIdOrUri,imageView,diskCacheStrategy)
    }

    override fun loadCircleImage(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadCircleImage(urlOrIdOrUri,imageView, placeholderImg)
    }

    override fun loadCircleImage(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadCircleImage(urlOrIdOrUri,imageView, placeholderImg,diskCacheStrategy)
    }

    override fun loadCircleImageCenterCrop(
        url: String,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadCircleImageCenterCrop(url,imageView, placeholderImg,diskCacheStrategy)
    }

    override fun loadCircleImageCenterCrop(
        placeholderImg: Int,
        imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadCircleImageCenterCrop(placeholderImg,imageView,diskCacheStrategy)
    }

    override fun loadCircleImageCenterCrop(
        uri: Uri,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadCircleImageCenterCrop(uri,imageView,placeholderImg,diskCacheStrategy)
    }

    override fun loadCircleImageCenterCrop(
        uri: Uri,
        imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadCircleImageCenterCrop(uri,imageView,diskCacheStrategy)
    }

    override fun loadCircleImageCenterCrop(
        colorDrawable: ColorDrawable,
        imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadCircleImageCenterCrop(colorDrawable,imageView,diskCacheStrategy)
    }

    override fun loadCircleImageCenterInside(
        url: String,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadCircleImageCenterInside(url,imageView,placeholderImg,diskCacheStrategy)
    }

    override fun loadCornersImage(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float) {
        imageLoaderStrategy.loadCornersImage(urlOrIdOrUri,imageView,cornerRadius)
    }

    override fun loadCornersImage(
        url: String,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadCornersImage(url,imageView,cornerRadius,placeholderImg)
    }

    override fun loadCornersImage(
        url: String,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int,
        transition: Boolean
    ) {
        imageLoaderStrategy.loadCornersImage(url,imageView,cornerRadius,placeholderImg,transition)
    }

    override fun loadCornersImage(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadCornersImage(urlOrIdOrUri,imageView,cornerRadius,placeholderImg,diskCacheStrategy)
    }

    override fun loadCornersImage(
        context: Context,
        urlOrIdOrUri: Any,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadCornersImage(context,urlOrIdOrUri,imageView,cornerRadius,placeholderImg,diskCacheStrategy)
    }

    override fun loadCornersImage(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum) {
        imageLoaderStrategy.loadCornersImage(urlOrIdOrUri,imageView,cornerRadius,cornerTypeEnum)
    }

    override fun loadCornersImage(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        cornerRadius: Float,
        cornerTypeEnum: CornerTypeEnum,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadCornersImage(urlOrIdOrUri,imageView,cornerRadius,cornerTypeEnum,placeholderImg)
    }

    override fun loadCornersImage(
        bitmap: Bitmap,
        imageView: ImageView,
        cornerRadius: Float,
        cornerTypeEnum: CornerTypeEnum
    ) {
        imageLoaderStrategy.loadCornersImage(bitmap,imageView,cornerRadius,cornerTypeEnum)
    }

    override fun loadCornersImage(
        bitmap: Bitmap,
        imageView: ImageView,
        cornerRadius: Float,
        cornerTypeEnum: CornerTypeEnum,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadCornersImage(bitmap,imageView,cornerRadius,cornerTypeEnum,placeholderImg)
    }

    override fun loadCornersImageCenterCrop(
        url: String,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadCornersImageCenterCrop(url,imageView,cornerRadius,placeholderImg)
    }

    override fun loadCornersImageCenterCrop(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        cornerRadius: Float,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadCornersImageCenterCrop(urlOrIdOrUri,imageView,cornerRadius,diskCacheStrategy)
    }

    override fun loadCornersImageCenterCrop(
        colorDrawable: ColorDrawable,
        imageView: ImageView,
        cornerRadius: Float
    ) {
        imageLoaderStrategy.loadCornersImageCenterCrop(colorDrawable,imageView,cornerRadius)
    }

    override fun loadCornersImageCenterCrop(
        url: String,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int,
        transition: Boolean
    ) {
        imageLoaderStrategy.loadCornersImageCenterCrop(url,imageView,cornerRadius,placeholderImg,transition)
    }

    override fun loadCornersImageCenterCrop(
        url: String,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadCornersImageCenterCrop(url,imageView,cornerRadius,placeholderImg,diskCacheStrategy)
    }

    override fun loadCornersImageWithCenterCrop(
        url: String,
        imageView: ImageView,
        placeholderImg: Int,
        cornerRadius: Float,
        diskCacheStrategy: DiskCacheStrategyEnum,
        transition: Boolean,
        thumbnail: Boolean,
        thumbnailWidth: Int,
        thumbnailHeight: Int
    ) {
        imageLoaderStrategy.loadCornersImageWithCenterCrop(url,imageView,placeholderImg,cornerRadius,diskCacheStrategy,
            transition,thumbnail,thumbnailWidth,thumbnailHeight)
    }

    override fun loadBorderCircleImage(urlOrIdOrUri: Any, imageView: ImageView, borderColor : Int, borderWidth : Float) {
        imageLoaderStrategy.loadBorderCircleImage(urlOrIdOrUri,imageView, borderColor, borderWidth)
    }

    override fun loadBorderCircleImage(
        url: String,
        imageView: ImageView,
        imageWidth : Int,
        imageHeight : Int,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadBorderCircleImage(url,imageView,imageWidth, imageHeight, borderColor, borderWidth,placeholderImg)
    }

    override fun loadBorderCircleImage(
        url: String,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadBorderCircleImage(url,imageView, borderColor, borderWidth,placeholderImg)
    }

    override fun loadBorderCircleImageCenterCrop(
        url: String,
        imageView: ImageView,
        imageWidth: Int,
        imageHeight: Int,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadBorderCircleImageCenterCrop(url,imageView, imageWidth, imageHeight, borderColor, borderWidth,placeholderImg)
    }

    override fun loadBorderCircleImageCenterCrop(
        url: String,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadBorderCircleImageCenterCrop(url,imageView, borderColor, borderWidth,placeholderImg)
    }

    override fun loadBorderCircleImageCenterCrop(
        url: String,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int,
        diskCacheStrategyEnum: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadBorderCircleImageCenterCrop(url,imageView, borderColor, borderWidth,placeholderImg,diskCacheStrategyEnum)
    }

    override fun loadBorderCircleImageCenterCrop(
        imgId : Int,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int,
        diskCacheStrategyEnum: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadBorderCircleImageCenterCrop(imgId,imageView, borderColor, borderWidth,placeholderImg,diskCacheStrategyEnum)
    }

    override fun loadBorderCircleImageCenterCrop(
        uri: Uri,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int,
        diskCacheStrategyEnum: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadBorderCircleImageCenterCrop(uri,imageView, borderColor, borderWidth,placeholderImg,diskCacheStrategyEnum)
    }


    override fun loadBorderCircleImageCenterCrop(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float
    ) {
        imageLoaderStrategy.loadBorderCircleImageCenterCrop(urlOrIdOrUri,imageView, borderColor, borderWidth)
    }

    override fun loadBorderCircleImageCenterCrop(
        colorDrawable: ColorDrawable,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float
    ) {
        imageLoaderStrategy.loadBorderCircleImageCenterCrop(colorDrawable,imageView, borderColor, borderWidth)
    }

    override fun loadBorderCornerImage(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerRadius: Float
    ) {
        imageLoaderStrategy.loadBorderCornerImage(urlOrIdOrUri,imageView, borderColor, borderWidth, cornerRadius)
    }

    override fun loadBorderCornerImage(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerRadius: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadBorderCornerImage(urlOrIdOrUri,imageView, borderColor, borderWidth, cornerRadius,placeholderImg)
    }

    override fun loadGif(context: Context,urlOrIdOrUri: Any, imageView: ImageView) {
        imageLoaderStrategy.loadGif(context,urlOrIdOrUri,imageView)
    }

    override fun loadGif(
        context: Context,
        urlOrIdOrUri: Any,
        imageView: ImageView,
        imgWidth: Int,
        imgHeight: Int
    ) {
        imageLoaderStrategy.loadGif(context,urlOrIdOrUri,imageView,imgWidth,imgHeight)
    }

    override fun loadGif(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadGif(urlOrIdOrUri,imageView,diskCacheStrategy)
    }

    override fun loadGif(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadGif(urlOrIdOrUri,imageView,placeholderImg)
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

    override fun loadGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadGif(playTimes, urlOrIdOrUri,imageView,placeholderImg)
    }

    override fun loadCircleGif(urlOrIdOrUri: Any, imageView: ImageView) {
        imageLoaderStrategy.loadCircleGif(urlOrIdOrUri,imageView)
    }

    override fun loadCircleGif(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadCircleGif(urlOrIdOrUri,imageView, placeholderImg)
    }
    override fun loadCircleGif(playTimes: Int,urlOrIdOrUri: Any, imageView: ImageView) {
        imageLoaderStrategy.loadCircleGif(playTimes,urlOrIdOrUri,imageView)
    }

    override fun loadCircleGif(
        playTimes: Int,
        urlOrIdOrUri: Any,
        imageView: ImageView,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadCircleGif(playTimes,urlOrIdOrUri,imageView, placeholderImg)
    }

    override fun loadCornerGif(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float) {
        imageLoaderStrategy.loadCornerGif(urlOrIdOrUri,imageView, cornerRadius)
    }


    override fun loadCornerGif(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadCornerGif(urlOrIdOrUri,imageView, cornerRadius,placeholderImg)
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
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadCornerGif(playTimes,urlOrIdOrUri,imageView, cornerRadius, placeholderImg)
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
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadBorderCornerGif(urlOrIdOrUri,imageView, borderColor, borderWidth, cornerRadius,placeholderImg)
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
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadBorderCornerGif(playTimes,urlOrIdOrUri,imageView, borderColor, borderWidth, cornerRadius, placeholderImg)
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
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadImageWithRXCustomTarget(urlOrIdOrUri,context,rxCustomTarget,placeholderImg)
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

    override fun load9Png(urlOrIdOrUri: Any, view: View, placeholderImg: Int) {
        imageLoaderStrategy.load9Png(urlOrIdOrUri, view,placeholderImg)
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