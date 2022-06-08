package com.renxing.moduleImageLoader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.renxing.moduleImageLoader.imageUtils.ImgLoadParams
import com.renxing.moduleImageLoader.imageUtils.enumUtils.CornerTypeEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.DecodeFormateEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.DiskCacheStrategyEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.PriorityEnum
import com.renxing.moduleImageLoader.loaderStrategy.control.ImageLoaderInterface
import com.renxing.moduleImageLoader.loaderStrategy.control.LoaderStrategyFactory
import com.renxing.moduleImageLoader.loaderStrategy.control.OnAnimationStatus
import com.renxing.moduleImageLoader.loaderStrategy.control.RXRequestListener
import com.renxing.moduleImageLoader.loaderStrategy.glide.config.ImgLoadConfigImpl
import com.renxing.moduleImageLoader.loaderStrategy.glide.target.RXCustomTarget

/**
 *@author  :  WuJianFeng
 */
object RXImageLoader : ImageLoaderInterface {
    private var imageLoaderStrategy = LoaderStrategyFactory.instance.getLoaderStrategy()
    override fun getBitmap(
        context: Context,
        url: String,
        decodeFormateEnum: DecodeFormateEnum,
        diskCacheStrategyEnum: DiskCacheStrategyEnum,
        function1: (Bitmap?) -> Unit
    ) {
        return imageLoaderStrategy.getBitmap(context,url,decodeFormateEnum,diskCacheStrategyEnum,function1)
    }


    override fun loadImage(imgLoadParams: ImgLoadParams) {
        imageLoaderStrategy.loadImage(imgLoadParams)

    }
    override fun loadGifImage(imgLoadParams: ImgLoadParams) {
        imageLoaderStrategy.loadGifImage(imgLoadParams)

    }
    override fun loadBitmapImage(imgLoadParams: ImgLoadParams) {
        imageLoaderStrategy.loadBitmapImage(imgLoadParams)

    }
    override fun loadCircleImage(imgLoadParams: ImgLoadParams) {
        imageLoaderStrategy.loadCircleImage(imgLoadParams)
    }

    override fun loadCornersImage(imgLoadParams: ImgLoadParams) {
        imageLoaderStrategy.loadCornersImage(imgLoadParams)
    }
    override fun loadBorderCircleImage(imgLoadParams: ImgLoadParams) {
        imageLoaderStrategy.loadBorderCircleImage(imgLoadParams)
    }
    override fun load9Png(imgLoadParams: ImgLoadParams) {
        imageLoaderStrategy.load9Png(imgLoadParams)
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