package com.renxing.moduleImageLoader.loaderStrategy.glide

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.renxing.moduleImageLoader.imageUtils.ImageLoaderUtils
import com.renxing.moduleImageLoader.imageUtils.enumUtils.CornerTypeEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.DiskCacheStrategyEnum
import com.renxing.moduleImageLoader.loaderStrategy.control.NewInterface
import com.renxing.moduleImageLoader.loaderStrategy.glide.transformation.RoundedCornersTransform

/**
 *@date    :  2022/5/20 15:01
 *@author  :  WuJianFeng
 */
@SuppressLint("CheckResult")
class NewImpl : NewInterface {

    private lateinit var drawableBuilder: RequestBuilder<Drawable>
    private lateinit var bitmapBuilder: RequestBuilder<Bitmap>
    private lateinit var requestOptions : RequestOptions

    override fun builder(context:Context,urlOrIdOrUri : Any): NewInterface {
        ImageLoaderUtils.checkUrlOrId(urlOrIdOrUri)
        drawableBuilder = Glide.with(context).load(urlOrIdOrUri)
        requestOptions = RequestOptions()
        return this
    }

    override fun builder(context:Context,byteArray: ByteArray): NewInterface {

        bitmapBuilder = Glide.with(context).asBitmap().load(byteArray)
        requestOptions = RequestOptions()
        return this
    }

    override fun builder(context: Context, url: String, width: Int, height: Int): NewInterface {
        //todo
        // 检查urlOrIdOrUri正确性
        // 拼接Url
        drawableBuilder = Glide.with(context).load(ImageLoaderUtils.appendUrl(url, width, height, false))
        return this
    }

    override fun fitCenter(): NewInterface {
        drawableBuilder.fitCenter()
        return this
    }

    override fun centerCrop(): NewInterface {
        drawableBuilder.centerCrop()
        return this
    }

    override fun centerInside(): NewInterface {
        drawableBuilder.centerInside()
        return this
    }

    override fun placeholderAndErrImg(placeholder : Int): NewInterface {
        drawableBuilder.placeholder(placeholder)
        return this
    }

    override fun placeholderAndErrImg(placeholder : Int, error : Int): NewInterface {
        drawableBuilder.placeholder(placeholder).error(error)
        return this
    }

    override fun error(id : Int): NewInterface {
        drawableBuilder.error(id)
        return this
    }

    override fun diskCacheStrategy(diskCacheStrategyEnum: DiskCacheStrategyEnum): NewInterface {
        when(diskCacheStrategyEnum){
            DiskCacheStrategyEnum.RESOURCE -> {
                requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE).skipMemoryCache(true)
            }
            DiskCacheStrategyEnum.NONE -> {
                drawableBuilder.diskCacheStrategy(DiskCacheStrategy.NONE)

            }
            DiskCacheStrategyEnum.ALL -> {
                drawableBuilder.diskCacheStrategy(DiskCacheStrategy.ALL)

            }
            DiskCacheStrategyEnum.AUTOMATIC -> {
                drawableBuilder.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

            }
        }
        return this
    }

    override fun transition(transition: Boolean): NewInterface {
        //todo 可优化：传入具体的变换参数
        drawableBuilder.transition(DrawableTransitionOptions.withCrossFade())
        return this
    }

    override fun thumbnail(context: Context, urlOrIdOrUri : Int, thumbnailWidth: Int, thumbnailHeight: Int): NewInterface {
        drawableBuilder.thumbnail(Glide.with(context)
            .load(urlOrIdOrUri)
            .override(thumbnailWidth, thumbnailHeight))
        return this
    }

    override fun circleCrop(): NewInterface {
        drawableBuilder.circleCrop()
        return this
    }

    override fun cornersCrop(cornerRadius: Float): NewInterface {
        requestOptions.optionalTransform(RoundedCornersTransform(ImageLoaderUtils.dp2px(cornerRadius) + 0.5f, CornerTypeEnum.ALL))
        return this
    }

    override fun cornersCrop(cornerRadius: Float, cornerTypeEnum : CornerTypeEnum): NewInterface {
        requestOptions.optionalTransform(RoundedCornersTransform(ImageLoaderUtils.dp2px(cornerRadius) + 0.5f, cornerTypeEnum))
        return this
    }

    override fun into(imageView: ImageView): NewInterface {
        drawableBuilder.apply(requestOptions).into(imageView)
        return this
    }
}