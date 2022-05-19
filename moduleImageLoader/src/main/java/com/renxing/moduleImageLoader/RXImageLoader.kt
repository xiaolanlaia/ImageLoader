package com.renxing.moduleImageLoader

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import com.renxing.moduleImageLoader.imageUtils.enumUtils.CornerTypeEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.DiskCacheStrategyEnum
import com.renxing.moduleImageLoader.loaderStrategy.control.ImageLoaderInterface
import com.renxing.moduleImageLoader.loaderStrategy.control.LoaderStrategyFactory
import com.renxing.moduleImageLoader.loaderStrategy.control.OnAnimationStatus
import com.renxing.moduleImageLoader.loaderStrategy.glide.target.RXCustomTarget

/**
 *@author  :  WuJianFeng
 */
object RXImageLoader : ImageLoaderInterface {
    private var imageLoaderStrategy = LoaderStrategyFactory.instance.getLoaderStrategy()


    override fun loadImage(urlOrId: Any, imageView: ImageView) {
    imageLoaderStrategy.loadImage(urlOrId,imageView)
    }

    override fun loadImage(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadImage(urlOrId,imageView,placeholderImg)
    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int) {
        imageLoaderStrategy.loadImage(url,imageView,width,height)
    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int, placeholderImg: Int) {
        imageLoaderStrategy.loadImage(url,imageView,width,height, placeholderImg)
    }

    override fun loadImageWithFitCenter(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithFitCenter(urlOrId,imageView)
    }

    override fun loadImageWithFitCenter(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadImageWithFitCenter(urlOrId,imageView, placeholderImg)
    }

    override fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrId,imageView)
    }

    override fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrId,imageView, placeholderImg)
    }

    override fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView,diskCacheStrategy: DiskCacheStrategyEnum) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrId,imageView,diskCacheStrategy)
    }

    override fun loadImageWithCenterCrop(
        urlOrId: Any,
        imageView: ImageView,
        placeholderImg: Int,
        transition: Boolean
    ) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrId,imageView,placeholderImg,transition)

    }

    override fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView, placeholderImg: Int,
                                         diskCacheStrategy : DiskCacheStrategyEnum,
                                         transition : Boolean) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrId,imageView, placeholderImg,diskCacheStrategy,transition)
    }

    override fun loadImageWithCenterCrop(
        urlOrId: Any,
        view: View,
        thumbnail: Boolean,
        thumbnailWidth: Int,
        thumbnailHeight: Int
    ) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrId,view, thumbnail,thumbnailWidth,thumbnailHeight)
    }

    override fun loadImageWithCenterCrop(
        urlOrId: Any,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrId,imageView, placeholderImg,diskCacheStrategy)
    }

    override fun loadImageWithCenterCrop(
        urlOrId: Any,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum,
        thumbnail: Boolean,
        transition: Boolean,
        thumbnailWidth : Int,
        thumbnailHeight : Int
    ) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrId,imageView, placeholderImg,diskCacheStrategy,thumbnail,transition,thumbnailWidth,thumbnailHeight)
    }

    override fun loadImageWithCenterInside(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithCenterInside(urlOrId,imageView)
    }

    override fun loadImageWithCenterInside(
        urlOrId: Any,
        imageView: ImageView,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadImageWithCenterInside(urlOrId,imageView, placeholderImg)
    }

    override fun loadImageWithCenterInside(
        urlOrId: Any,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum,
        transition: Boolean
    ) {
        imageLoaderStrategy.loadImageWithCenterInside(urlOrId,imageView, placeholderImg,diskCacheStrategy,transition)
    }

    override fun loadImageWithCenterInside(
        urlOrId: Any,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadImageWithCenterInside(urlOrId,imageView, placeholderImg,diskCacheStrategy)
    }

    override fun loadImageWithCenterInside(
        urlOrId: Any,
        imageView: ImageView,
        placeholderImg: Int,
        transition: Boolean
    ) {
        imageLoaderStrategy.loadImageWithCenterInside(urlOrId,imageView, placeholderImg,transition)
    }

    override fun loadImageWithSkipCache(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithSkipCache(urlOrId,imageView)
    }

    override fun loadImageWithSkipCache(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadImageWithSkipCache(urlOrId,imageView, placeholderImg)
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

    override fun loadCircleImage(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadCircleImage(urlOrId,imageView)
    }

    override fun loadCircleImage(
        urlOrId: Any,
        imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadCircleImage(urlOrId,imageView,diskCacheStrategy)
    }

    override fun loadCircleImage(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadCircleImage(urlOrId,imageView, placeholderImg)
    }

    override fun loadCircleImage(
        urlOrId: Any,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadCircleImage(urlOrId,imageView, placeholderImg,diskCacheStrategy)
    }

    override fun loadCornersImage(urlOrId: Any, imageView: ImageView, cornerRadius: Float) {
        imageLoaderStrategy.loadCornersImage(urlOrId,imageView,cornerRadius)
    }

    override fun loadCornersImage(
        urlOrId: Any,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadCornersImage(urlOrId,imageView,cornerRadius,placeholderImg)
    }

    override fun loadCornersImage(urlOrId: Any, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum) {
        imageLoaderStrategy.loadCornersImage(urlOrId,imageView,cornerRadius,cornerTypeEnum)
    }

    override fun loadCornersImage(
        urlOrId: Any,
        imageView: ImageView,
        cornerRadius: Float,
        cornerTypeEnum: CornerTypeEnum,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadCornersImage(urlOrId,imageView,cornerRadius,cornerTypeEnum,placeholderImg)
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

    override fun loadCornersImageWithCenterCrop(
        urlOrId: Any,
        imageView: ImageView,
        placeholderImg: Int,
        cornerRadius: Float,
        diskCacheStrategy: DiskCacheStrategyEnum,
        transition: Boolean,
        thumbnail: Boolean,
        thumbnailWidth: Int,
        thumbnailHeight: Int
    ) {
        imageLoaderStrategy.loadCornersImageWithCenterCrop(urlOrId,imageView,placeholderImg,cornerRadius,diskCacheStrategy,
            transition,thumbnail,thumbnailWidth,thumbnailHeight)
    }

    override fun loadBorderCircleImage(urlOrId: Any, imageView: ImageView, borderColor : Int, borderWidth : Float) {
        imageLoaderStrategy.loadBorderCircleImage(urlOrId,imageView, borderColor, borderWidth)
    }

    override fun loadBorderCircleImage(
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadBorderCircleImage(urlOrId,imageView, borderColor, borderWidth,placeholderImg)
    }

    override fun loadBorderCornerImage(
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerRadius: Float
    ) {
        imageLoaderStrategy.loadBorderCornerImage(urlOrId,imageView, borderColor, borderWidth, cornerRadius)
    }

    override fun loadBorderCornerImage(
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerRadius: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadBorderCornerImage(urlOrId,imageView, borderColor, borderWidth, cornerRadius,placeholderImg)
    }

    override fun loadGif(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadGif(urlOrId,imageView)
    }

    override fun loadGif(
        urlOrId: Any,
        imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadGif(urlOrId,imageView,diskCacheStrategy)
    }

    override fun loadGif(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadGif(urlOrId,imageView,placeholderImg)
    }

    override fun loadGif(playTimes : Int, urlOrId: Any, imageView: ImageView, ) {
        imageLoaderStrategy.loadGif(playTimes,urlOrId,imageView)
    }

    override fun loadGif(
        playTimes: Int,
        urlOrId: Any,
        imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum,
        onAnimationStatus: OnAnimationStatus
    ) {
        imageLoaderStrategy.loadGif(playTimes,urlOrId,imageView,diskCacheStrategy,onAnimationStatus)
    }

    override fun loadGif(playTimes: Int, urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadGif(playTimes, urlOrId,imageView,placeholderImg)
    }

    override fun loadCircleGif(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadCircleGif(urlOrId,imageView)
    }

    override fun loadCircleGif(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadCircleGif(urlOrId,imageView, placeholderImg)
    }
    override fun loadCircleGif(playTimes: Int,urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadCircleGif(playTimes,urlOrId,imageView)
    }

    override fun loadCircleGif(
        playTimes: Int,
        urlOrId: Any,
        imageView: ImageView,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadCircleGif(playTimes,urlOrId,imageView, placeholderImg)
    }

    override fun loadCornerGif(urlOrId: Any, imageView: ImageView, cornerRadius: Float) {
        imageLoaderStrategy.loadCornerGif(urlOrId,imageView, cornerRadius)
    }


    override fun loadCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadCornerGif(urlOrId,imageView, cornerRadius,placeholderImg)
    }
    override fun loadCornerGif(
        playTimes: Int,
        urlOrId: Any,
        imageView: ImageView,
        cornerRadius: Float,
    ) {
        imageLoaderStrategy.loadCornerGif(playTimes,urlOrId,imageView, cornerRadius)
    }

    override fun loadCornerGif(
        playTimes: Int,
        urlOrId: Any,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadCornerGif(playTimes,urlOrId,imageView, cornerRadius, placeholderImg)
    }

    override fun loadBorderCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerRadius: Float
    ) {
        imageLoaderStrategy.loadBorderCornerGif(urlOrId,imageView, borderColor, borderWidth, cornerRadius)
    }

    override fun loadBorderCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerRadius: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadBorderCornerGif(urlOrId,imageView, borderColor, borderWidth, cornerRadius,placeholderImg)
    }

    override fun loadBorderCornerGif(
        playTimes: Int,
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerRadius: Float
    ) {
        imageLoaderStrategy.loadBorderCornerGif(playTimes,urlOrId,imageView, borderColor, borderWidth, cornerRadius)
    }

    override fun loadBorderCornerGif(
        playTimes: Int,
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerRadius: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadBorderCornerGif(playTimes,urlOrId,imageView, borderColor, borderWidth, cornerRadius, placeholderImg)
    }

    override fun loadImageWithRXCustomTarget(urlOrId: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>) {
        imageLoaderStrategy.loadImageWithRXCustomTarget(urlOrId,context,rxCustomTarget)
    }

    override fun loadImageWithRXCustomTarget(
        urlOrId: Any,
        context: Context,
        rxCustomTarget: RXCustomTarget<Bitmap>,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadImageWithRXCustomTarget(urlOrId,context,rxCustomTarget,placeholderImg)
    }

    override fun loadImageWithRXCustomTarget(
        urlOrId: Any,
        context: Context,
        rxCustomTarget: RXCustomTarget<Bitmap>,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        imageLoaderStrategy.loadImageWithRXCustomTarget(urlOrId,context,rxCustomTarget,diskCacheStrategy)
    }

    override fun load9Png(urlOrId: Any, view: View) {
        imageLoaderStrategy.load9Png(urlOrId, view)
    }

    override fun load9Png(urlOrId: Any, view: View, placeholderImg: Int) {
        imageLoaderStrategy.load9Png(urlOrId, view,placeholderImg)
    }

    override fun clearImageDiskCache(context: Context) {
        imageLoaderStrategy.clearImageDiskCache(context)
    }

    override fun clearImageMemoryCache(context: Context) {
        imageLoaderStrategy.clearImageMemoryCache(context)
    }
}