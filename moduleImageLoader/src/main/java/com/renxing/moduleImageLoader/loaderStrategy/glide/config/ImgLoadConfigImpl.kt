package com.renxing.moduleImageLoader.loaderStrategy.glide.config

import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.renxing.moduleImageLoader.imageUtils.enumUtils.DiskCacheStrategyEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.PriorityEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.RequestBuilderTypeEnum
import com.renxing.moduleImageLoader.loaderStrategy.control.RXRequestListener
import com.renxing.moduleImageLoader.loaderStrategy.glide.target.RXCustomTarget

/**
 * Glide配置类
 * @author : BaoZhou
 * @date : 2020/5/9 2:49 PM
 */

class ImgLoadConfigImpl private constructor(builder: Builder) : ImageConfig() {
    /**
     * 0 对应DiskCacheStrategy.all
     * 1 对应DiskCacheStrategy.NONE
     * 2 对应DiskCacheStrategy.SOURCE
     * 3 对应DiskCacheStrategy.RESULT
     */
    val cacheStrategy: Int
    val diskCacheStrategyEnum : DiskCacheStrategyEnum?
    val fallback: Int
    val transformation: Array<out BitmapTransformation>?
    val isClearMemory: Boolean
    val isClearDiskCache: Boolean
    val requestBuilderTypeEnum: RequestBuilderTypeEnum?
    val placeHolderDrawable: Drawable?
    val errorDrawable: Drawable?
    val resizeX: Int
    val isCropCenter: Boolean
    val isCenterInside: Boolean
    val isCropCircle: Boolean
    val isFitCenter: Boolean
    val formatType: DecodeFormat?
    val resizeY: Int
    val imageRadius: Int
    val isCrossFade: Boolean
    val dontAnimate: Boolean
    var requestListenerDrawable: RXRequestListener<Drawable>?
    var requestListenerBitmap: RXRequestListener<Bitmap>?
    var requestListenerGifDrawable: RXRequestListener<GifDrawable>?
    var rxCustomTargetDrawable : RXCustomTarget<Drawable>?
    var rxCustomTargetBitmap : RXCustomTarget<Bitmap>?
    var rxCustomTargetGifDrawable : RXCustomTarget<GifDrawable>?
    var priorityEnum : PriorityEnum?


    fun isImageRadius(): Boolean {
        return imageRadius > 0
    }

    class Builder {
        var resizeX = 0
        var url: String? = null
        var uri : Uri? = null
        var drawable : Drawable? = null
        var colorDrawable : ColorDrawable? = null
        var drawableId = 0
        var imageView: ImageView? = null
        var placeholder = 0
        var placeholderDrawable: Drawable? = null
        var errorDrawable: Drawable? = null
        var errorPic = 0
        var fallback = 0
        var cacheStrategy = 0
        var diskCacheStrategyEnum : DiskCacheStrategyEnum? = null
        var imageRadius = 0
        var transformation: Array<out BitmapTransformation>? = null
        var isClearMemory = false
        var requestBuilderTypeEnum : RequestBuilderTypeEnum? = null
        var isClearDiskCache = false
        var isCropCenter = false
        var isCenterInside = false
        var isCropCircle = false
        var isCrossFade = false
        var dontAnimate = false
        var formatType: DecodeFormat? = null
        var isFitCenter = false
        var resizeY = 0
        var requestListenerDrawable: RXRequestListener<Drawable>? = null
        var requestListenerBitmap: RXRequestListener<Bitmap>? = null
        var requestListenerGifDrawable: RXRequestListener<GifDrawable>? = null
        var rxCustomTarget: RXCustomTarget<Drawable>? = null
        var rxCustomTargetBitmap: RXCustomTarget<Bitmap>? = null
        var rxCustomTargetGifDrawable: RXCustomTarget<GifDrawable>? = null
        var priorityEnum: PriorityEnum? = null
        fun load(url: String?): Builder {
            this.url = url
            return this
        }
        fun load(uri : Uri?): Builder {
            this.uri = uri
            return this
        }
        fun load(drawable: Drawable?): Builder {
            this.drawable = drawable
            return this
        }
        fun load(colorDrawable: ColorDrawable?): Builder {
            this.colorDrawable = colorDrawable
            return this
        }

        fun load(drawableId: Int): Builder {
            this.drawableId = drawableId
            return this
        }

        fun placeholder(placeholder: Int): Builder {
            this.placeholder = placeholder
            return this
        }

        fun error(errorPic: Int): Builder {
            this.errorPic = errorPic
            return this
        }

        fun fallback(fallback: Int): Builder {
            this.fallback = fallback
            return this
        }

        fun imageView(imageView: ImageView?): Builder {
            this.imageView = imageView
            return this
        }

        fun cacheStrategy(cacheStrategy: Int): Builder {
            this.cacheStrategy = cacheStrategy
            return this
        }

        fun cacheStrategy(diskCacheStrategyEnum: DiskCacheStrategyEnum): Builder {
            this.diskCacheStrategyEnum = diskCacheStrategyEnum
            return this
        }

        fun imageRadius(imageRadius: Int): Builder {
            this.imageRadius = imageRadius
            return this
        }


        fun isCrossFade(isCrossFade: Boolean): Builder {
            this.isCrossFade = isCrossFade
            return this
        }


        fun dontAnimate(dontAnimation: Boolean): Builder {
            this.dontAnimate = dontAnimation

            return this
        }

        fun transformation(vararg transformation: BitmapTransformation): Builder {
            this.transformation = transformation
            return this
        }

        fun isClearMemory(isClearMemory: Boolean): Builder {
            this.isClearMemory = isClearMemory
            return this
        }

        fun requestBuilderTypeEnum(requestBuilderTypeEnum: RequestBuilderTypeEnum): Builder {
            this.requestBuilderTypeEnum = requestBuilderTypeEnum
            return this
        }

        fun isClearDiskCache(isClearDiskCache: Boolean): Builder {
            this.isClearDiskCache = isClearDiskCache
            return this
        }

        fun placeholder(placeholderDrawable: Drawable?): Builder {
            this.placeholderDrawable = placeholderDrawable
            return this
        }

        fun error(errorDrawable: Drawable?): Builder {
            this.errorDrawable = errorDrawable
            return this
        }

        fun resize(resizeX: Int, resizeY: Int): Builder {
            this.resizeX = resizeX
            this.resizeY = resizeY
            return this
        }

        fun isCropCenter(isCropCenter: Boolean): Builder {
            this.isCropCenter = isCropCenter
            return this
        }

        fun isCenterInside(isCenterInside: Boolean): Builder {
            this.isCenterInside = isCenterInside
            return this
        }

        fun isCropCircle(isCropCircle: Boolean): Builder {
            this.isCropCircle = isCropCircle
            return this
        }

        fun setDecodeFormat(decodeFormat: DecodeFormat?): Builder {
            formatType = decodeFormat
            return this
        }

        fun isFitCenter(isFitCenter: Boolean): Builder {
            this.isFitCenter = isFitCenter
            return this
        }

        fun requestListenerDrawable(requestListener: RXRequestListener<Drawable>?): Builder {
            this.requestListenerDrawable = requestListener
            return this
        }
        fun requestListenerBitmap(requestListenerBitmap: RXRequestListener<Bitmap>?): Builder {
            this.requestListenerBitmap = requestListenerBitmap
            return this
        }
        fun requestListenerGifDrawable(requestListenerGifDrawable: RXRequestListener<GifDrawable>?): Builder {
            this.requestListenerGifDrawable = requestListenerGifDrawable
            return this
        }
        fun rxDrawableTarget(rxCustomTarget: RXCustomTarget<Drawable>?): Builder {
            this.rxCustomTarget = rxCustomTarget
            return this
        }
        fun rxBitmapTarget(rxCustomTargetBitmap: RXCustomTarget<Bitmap>?): Builder {
            this.rxCustomTargetBitmap = rxCustomTargetBitmap
            return this
        }
        fun rxGifDrawableTarget(rxCustomTargetGifDrawable: RXCustomTarget<GifDrawable>?): Builder {
            this.rxCustomTargetGifDrawable = rxCustomTargetGifDrawable
            return this
        }
        fun priorityEnum(priorityEnum: PriorityEnum?): Builder {
            this.priorityEnum = priorityEnum
            return this
        }

        fun build(): ImgLoadConfigImpl {
            return ImgLoadConfigImpl(this)
        }
    }

    companion object {
        fun builder(): Builder {
            return Builder()
        }
    }

    init {
        url = builder.url
        uri = builder.uri
        drawable = builder.drawable
        colorDrawable = builder.colorDrawable
        drawableId = builder.drawableId
        imageView = builder.imageView
        placeholder = builder.placeholder
        placeHolderDrawable = builder.placeholderDrawable
        errorPic = builder.errorPic
        errorDrawable = builder.errorDrawable
        fallback = builder.fallback
        cacheStrategy = builder.cacheStrategy
        diskCacheStrategyEnum = builder.diskCacheStrategyEnum
        transformation = builder.transformation
        isClearMemory = builder.isClearMemory
        isClearDiskCache = builder.isClearDiskCache
        requestBuilderTypeEnum = builder.requestBuilderTypeEnum
        resizeX = builder.resizeX
        resizeY = builder.resizeY
        isCropCenter = builder.isCropCenter
        isCenterInside = builder.isCenterInside
        isCropCircle = builder.isCropCircle
        formatType = builder.formatType
        isFitCenter = builder.isFitCenter
        isCrossFade = builder.isCrossFade
        dontAnimate = builder.dontAnimate
        imageRadius = builder.imageRadius
        requestListenerDrawable = builder.requestListenerDrawable
        requestListenerBitmap = builder.requestListenerBitmap
        rxCustomTargetDrawable = builder.rxCustomTarget
        requestListenerGifDrawable = builder.requestListenerGifDrawable
        rxCustomTargetBitmap = builder.rxCustomTargetBitmap
        rxCustomTargetGifDrawable = builder.rxCustomTargetGifDrawable
        priorityEnum = builder.priorityEnum
    }
}