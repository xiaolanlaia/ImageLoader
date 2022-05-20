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
import com.renxing.moduleImageLoader.loaderStrategy.glide.target.RXCustomTarget
import com.renxing.moduleImageLoader.loaderStrategy.glide.transformation.RoundedCornersTransform

/**
 *@date    :  2022/5/20 15:01
 *@author  :  WuJianFeng
 */
@SuppressLint("CheckResult")
class NewImpl : NewInterface {

    private lateinit var requestBuilder: RequestBuilder<Drawable>
    lateinit var requestOptions : RequestOptions

    override fun builder(context:Context,urlOrIdOrUri : Any): NewInterface {
        //todo 检查urlOrIdOrUri正确性
        requestBuilder = Glide.with(context).load(urlOrIdOrUri)
        requestOptions = RequestOptions()
        return this
    }

    override fun builder(context: Context, url: String, width: Int, height: Int): NewInterface {
        //todo
        // 检查urlOrIdOrUri正确性
        // 拼接Url
        requestBuilder = Glide.with(context).load(ImageLoaderUtils.appendUrl(url, width, height, false))
        return this
    }

    override fun fitCenter(): NewInterface {
        requestBuilder.fitCenter()
        return this
    }

    override fun centerCrop(): NewInterface {
        requestBuilder.centerCrop()
        return this
    }

    override fun centerInside(): NewInterface {
        requestBuilder.centerInside()
        return this
    }

    override fun placeholderAndErrImg(placeholder : Int): NewInterface {
        requestBuilder.placeholder(placeholder)
        return this
    }

    override fun placeholderAndErrImg(placeholder : Int, error : Int): NewInterface {
        requestBuilder.placeholder(placeholder).error(error)
        return this
    }

    override fun error(id : Int): NewInterface {
        requestBuilder.error(id)
        return this
    }

    override fun diskCacheStrategy(diskCacheStrategyEnum: DiskCacheStrategyEnum): NewInterface {
        when(diskCacheStrategyEnum){
            DiskCacheStrategyEnum.NONE -> {
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.NONE)

            }
            DiskCacheStrategyEnum.ALL -> {
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.ALL)

            }
            DiskCacheStrategyEnum.AUTOMATIC -> {
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

            }
        }
        return this
    }

    override fun transition(transition: Boolean): NewInterface {
        //todo 可优化：传入具体的变换参数
        requestBuilder.transition(DrawableTransitionOptions.withCrossFade())
        return this
    }

    override fun thumbnail(context: Context, urlOrIdOrUri : Int, thumbnailWidth: Int, thumbnailHeight: Int): NewInterface {
        requestBuilder.thumbnail(Glide.with(context)
            .load(urlOrIdOrUri)
            .override(thumbnailWidth, thumbnailHeight))
        return this
    }

    override fun circleCrop(): NewInterface {
        requestBuilder.circleCrop()
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
        requestBuilder.apply(requestOptions).into(imageView)
        return this
    }

    override fun into(rxCustomTarget: RXCustomTarget<Bitmap>): NewInterface {
        //todo 待做
        return this
    }
}