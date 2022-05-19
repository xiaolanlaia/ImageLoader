package com.renxing.moduleImageLoader.loaderStrategy.glide

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.NinePatch
import android.graphics.drawable.Drawable
import android.graphics.drawable.NinePatchDrawable
import android.os.Looper
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.renxing.moduleImageLoader.imageUtils.ImageLoaderUtils
import com.renxing.moduleImageLoader.imageUtils.enumUtils.CornerTypeEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.DiskCacheStrategyEnum
import com.renxing.moduleImageLoader.loaderStrategy.control.ImageLoaderInterface
import com.renxing.moduleImageLoader.loaderStrategy.control.OnAnimationStatus
import com.renxing.moduleImageLoader.loaderStrategy.glide.ninePic.NinePatchChunk
import com.renxing.moduleImageLoader.loaderStrategy.glide.placeholder.CircleRoundDrawable
import com.renxing.moduleImageLoader.loaderStrategy.glide.target.RXCustomTarget
import com.renxing.moduleImageLoader.loaderStrategy.glide.transformation.BorderRoundTransform
import com.renxing.moduleImageLoader.loaderStrategy.glide.transformation.CircleBorderTransformation
import com.renxing.moduleImageLoader.loaderStrategy.glide.transformation.RoundedCornersTransform
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException

/**
 *@author  :  WuJianFeng
 */
@SuppressLint("CheckResult")
internal class ImageLoaderGlide : ImageLoaderInterface {

    //nullOptions：占位RequestOptions没作用
    private val nullOptions = RequestOptions()
    private val fitCenterOptions = RequestOptions().fitCenter()
    private val centerScopeOptions = RequestOptions().centerCrop()
    private val centerInsideOptions = RequestOptions().centerInside()
    private val skipMemoryOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
    private val circleOptions = RequestOptions().circleCrop()

    override fun loadImage(urlOrId: Any, imageView: ImageView) {
        glideLoad(urlOrId, imageView, nullOptions,false,false,0,0)
    }

    override fun loadImage(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(urlOrId, imageView, RequestOptions().placeholder(placeholderImg).error(placeholderImg),false,false,0,0)
    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int) {
        glideLoad(ImageLoaderUtils.appendUrl(url, width, height, false), imageView,nullOptions,false,false,0,0)
    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int, placeholderImg: Int) {
        glideLoad(ImageLoaderUtils.appendUrl(url, width, height, false), imageView,
            RequestOptions().placeholder(placeholderImg).error(placeholderImg),false,false,0,0)
    }

    override fun loadImageWithFitCenter(urlOrId: Any, imageView: ImageView) {
        glideLoad(urlOrId, imageView, fitCenterOptions,false,false,0,0)

    }

    override fun loadImageWithFitCenter(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(urlOrId, imageView, RequestOptions().fitCenter().placeholder(placeholderImg).error(placeholderImg),false,false,0,0)
    }

    override fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView) {
        glideLoad(urlOrId, imageView, centerScopeOptions,false,false,0,0)
    }

    override fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(urlOrId, imageView, RequestOptions().centerCrop().placeholder(placeholderImg).error(placeholderImg),false,false,0,0)
    }

    override fun loadImageWithCenterCrop(urlOrId: Any,imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        glideLoad(urlOrId, imageView, getDiskCacheStrategy(diskCacheStrategy).centerCrop(),false,false,0,0)
    }

    override fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView, placeholderImg: Int,
                                         diskCacheStrategy: DiskCacheStrategyEnum, transition: Boolean) {

        glideLoad(urlOrId, imageView, getDiskCacheStrategy(diskCacheStrategy).centerCrop().placeholder(placeholderImg).error(placeholderImg),transition,false,0,0)

    }

    override fun loadImageWithCenterCrop(
        urlOrId: Any,
        view: View,
        thumbnail: Boolean,
        thumbnailWidth: Int,
        thumbnailHeight: Int
    ) {
        glideLoad(urlOrId, view, RequestOptions().centerCrop(),false,true,thumbnailWidth,thumbnailHeight)


    }

    override fun loadImageWithCenterCrop(urlOrId: Any,imageView: ImageView,placeholderImg: Int,diskCacheStrategy: DiskCacheStrategyEnum) {
        glideLoad(urlOrId, imageView, getDiskCacheStrategy(diskCacheStrategy).centerCrop().placeholder(placeholderImg).error(placeholderImg),false,false,0,0)

    }

    override fun loadImageWithCenterCrop(urlOrId: Any,imageView: ImageView,placeholderImg: Int,transition: Boolean) {
        glideLoad(urlOrId, imageView, RequestOptions().centerCrop().placeholder(placeholderImg).error(placeholderImg),transition,false,0,0)
    }

    override fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView, placeholderImg: Int, diskCacheStrategy: DiskCacheStrategyEnum,
                                         transition: Boolean, thumbnail: Boolean, thumbnailWidth: Int, thumbnailHeight: Int) {
        glideLoad(urlOrId, imageView,
            getDiskCacheStrategy(diskCacheStrategy).centerCrop().placeholder(placeholderImg).error(placeholderImg),transition,thumbnail,
            thumbnailWidth, thumbnailHeight

        )

    }

    override fun loadImageWithCenterInside(urlOrId: Any, imageView: ImageView) {
        glideLoad(urlOrId, imageView, centerInsideOptions,false,false,0,0)
    }

    override fun loadImageWithCenterInside(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(urlOrId, imageView,
            RequestOptions().centerInside().placeholder(placeholderImg).error(placeholderImg),false,false,0,0)
    }

    override fun loadImageWithCenterInside(urlOrId: Any, imageView: ImageView, placeholderImg: Int,
                                           diskCacheStrategy: DiskCacheStrategyEnum, transition: Boolean) {
        glideLoad(urlOrId, imageView,
            getDiskCacheStrategy(diskCacheStrategy).centerInside().placeholder(placeholderImg).error(placeholderImg),transition,false,0,0)
    }

    override fun loadImageWithCenterInside(urlOrId: Any,imageView: ImageView,placeholderImg: Int,diskCacheStrategy: DiskCacheStrategyEnum) {
        glideLoad(urlOrId, imageView,
            getDiskCacheStrategy(diskCacheStrategy).centerInside().placeholder(placeholderImg).error(placeholderImg).diskCacheStrategy(DiskCacheStrategy.ALL),false,false,0,0)
    }

    override fun loadImageWithCenterInside(urlOrId: Any,imageView: ImageView,placeholderImg: Int,transition: Boolean) {

        glideLoad(urlOrId, imageView,
            RequestOptions().centerInside().placeholder(placeholderImg).error(placeholderImg).diskCacheStrategy(DiskCacheStrategy.ALL),transition,false,0,0)

    }


    override fun loadImageWithSkipCache(urlOrId: Any, imageView: ImageView) {
        glideLoad(urlOrId, imageView, skipMemoryOptions,false,false,0,0)
    }

    override fun loadImageWithSkipCache(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(urlOrId, imageView,
            RequestOptions()
                .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(placeholderImg).error(placeholderImg),false
            ,false,0,0)
    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int) {
        glideLoad(ImageLoaderUtils.appendUrl(url, width, height, false), imageView, skipMemoryOptions,false,false,0,0)
    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int, placeholderImg: Int) {
        glideLoad(ImageLoaderUtils.appendUrl(url, width, height, false), imageView,
            RequestOptions()
                .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(placeholderImg).error(placeholderImg),false
            ,false,0,0)
    }

    override fun loadCircleImage(urlOrId: Any, imageView: ImageView) {
        glideLoad(urlOrId, imageView, circleOptions,false,false,0,0)
    }

    override fun loadCircleImage(urlOrId: Any,imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        glideLoad(urlOrId, imageView, getDiskCacheStrategy(diskCacheStrategy),false,false,0,0)
    }

    override fun loadCircleImage(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(urlOrId, imageView,
            RequestOptions().circleCrop()
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg))
                .error(CircleRoundDrawable(imageView.context, placeholderImg)),false
            ,false,0,0)
    }

    override fun loadCircleImage(urlOrId: Any,imageView: ImageView,placeholderImg: Int,diskCacheStrategy: DiskCacheStrategyEnum) {
        glideLoad(urlOrId, imageView,
            getDiskCacheStrategy(diskCacheStrategy).circleCrop()
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg))
                .error(CircleRoundDrawable(imageView.context, placeholderImg)),false
            ,false,0,0)
    }

    override fun loadCornersImage(urlOrId: Any, imageView: ImageView, cornerRadius: Float) {
        //注释七牛方案
//        ImageLoaderUtils.checkAndAppendCornerUrl(urlOrId, cornerRadius).apply {
//            when (this) {
//                is String -> {
//                    glideLoad(this, imageView,nullOptions)
//                }
//                else -> {
//                    glideLoad(urlOrId, imageView,
//                        RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
//                    )
//                }
//            }
//        }

        glideLoad(urlOrId, imageView,
            RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt())),false
            ,false,0,0)
    }

    override fun loadCornersImage(urlOrId: Any, imageView: ImageView, cornerRadius: Float, placeholderImg: Int) {
        //注释七牛方案
//        ImageLoaderUtils.checkAndAppendCornerUrl(urlOrId, cornerRadius).apply {
//            when (this) {
//                is String -> {
//                    glideLoad(this, imageView,
//                        RequestOptions()
//                            .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
//                            .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
//                    )
//
//                }
//                else -> {
//                    glideLoad(urlOrId, imageView,
//                        RequestOptions()
//                            .transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
//                            .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
//                            .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius)
//                            )
//                    )
//                }
//            }
//        }
        glideLoad(urlOrId, imageView,
            RequestOptions()
                .transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius)
                ),false
            ,false,0,0)
    }

    override fun loadCornersImage(urlOrId: Any, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum) {
        //注释七牛方案
//        when (cornerType) {
//            RXImageLoaderConstant.CornerType.ALL -> {
//                ImageLoaderUtils.checkAndAppendCornerUrl(urlOrId, cornerRadius).apply {
//                    when (this) {
//                        is String -> {
//                            glideLoad(this, imageView,nullOptions)
//                        }
//                        else -> {
//                            loadCornersImageConbine1(urlOrId, imageView, cornerRadius, cornerType)
//                        }
//                    }
//                }
//            }
//
//            else -> {
//                loadCornersImageConbine1(urlOrId, imageView, cornerRadius, cornerType)
//            }
//        }

        loadCornersImageCombine(urlOrId, imageView, cornerRadius, cornerTypeEnum,-1)


    }

    override fun loadCornersImage(urlOrId: Any, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum, placeholderImg: Int) {
        //注释七牛方案
//        when (cornerType) {
//            RXImageLoaderConstant.CornerType.ALL -> {
//                ImageLoaderUtils.checkAndAppendCornerUrl(urlOrId, cornerRadius).apply {
//                    when (this) {
//                        is String -> {
//                            glideLoad(this, imageView,
//                                RequestOptions()
//                                    .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
//                                    .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
//                            )
//                        }
//                        else -> {
//                            loadCornersImageConbine2(urlOrId, imageView, cornerRadius, cornerType, placeholderImg)
//                        }
//                    }
//                }
//            }
//
//            else -> {
//                loadCornersImageConbine2(urlOrId, imageView, cornerRadius, cornerType, placeholderImg)
//            }
//        }

        loadCornersImageCombine(urlOrId, imageView, cornerRadius, cornerTypeEnum, placeholderImg)

    }

    override fun loadCornersImage(bitmap: Bitmap, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum) {
        glideLoadBytes(ImageLoaderUtils.bitmap2Bytes(bitmap), imageView,
            RequestOptions().optionalTransform(
                RoundedCornersTransform(ImageLoaderUtils.dp2px(cornerRadius) + 0.5f, cornerTypeEnum)
            )
        )

    }

    override fun loadCornersImage(bitmap: Bitmap, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum, placeholderImg: Int) {
        glideLoadBytes(
            ImageLoaderUtils.bitmap2Bytes(bitmap), imageView,
            RequestOptions()
                .optionalTransform(RoundedCornersTransform(ImageLoaderUtils.dp2px(cornerRadius) + 0.5f, cornerTypeEnum))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
        )
    }

    override fun loadCornersImageWithCenterCrop(urlOrId: Any, imageView: ImageView, placeholderImg: Int,
                                                cornerRadius: Float, diskCacheStrategy: DiskCacheStrategyEnum,
                                                transition: Boolean, thumbnail: Boolean, thumbnailWidth: Int, thumbnailHeight: Int) {

        glideLoad(urlOrId, imageView,
            getDiskCacheStrategy(diskCacheStrategy)
                .optionalTransform(RoundedCornersTransform(ImageLoaderUtils.dp2px(cornerRadius) + 0.5f,
                    CornerTypeEnum.ALL))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius)),transition,
            thumbnail,thumbnailWidth,thumbnailHeight
        )

    }

    override fun loadBorderCircleImage(urlOrId: Any, imageView: ImageView, borderColor: Int, borderWidth: Float) {
        glideLoad(urlOrId, imageView,
            RequestOptions().optionalTransform(CircleBorderTransformation(borderWidth, borderColor)),false
            ,false,0,0)
    }

    override fun loadBorderCircleImage(urlOrId: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, placeholderImg: Int) {
        glideLoad(urlOrId, imageView,
            RequestOptions()
                .optionalTransform(CircleBorderTransformation(borderWidth, borderColor))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg))
                .error(CircleRoundDrawable(imageView.context, placeholderImg)),false
            ,false,0,0)
    }

    override fun loadBorderCornerImage(urlOrId: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius: Float) {
        glideLoad(urlOrId, imageView,
            RequestOptions().transform(BorderRoundTransform(borderWidth, borderColor, cornerRadius)),false
            ,false,0,0)
    }

    override fun loadBorderCornerImage(urlOrId: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius: Float, placeholderImg: Int) {
        glideLoad(urlOrId, imageView,
            RequestOptions()
                .transform(BorderRoundTransform(borderWidth, borderColor, cornerRadius))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius)),false
            ,false,0,0)
    }

    override fun loadGif(playTimes: Int, urlOrId: Any, imageView: ImageView) {
        glideLoadGif(urlOrId, imageView, playTimes, nullOptions,null)
    }

    override fun loadGif(playTimes: Int, urlOrId: Any, imageView: ImageView,
                         diskCacheStrategy: DiskCacheStrategyEnum, onAnimationStatus: OnAnimationStatus) {
        glideLoadGif(urlOrId, imageView, playTimes, getDiskCacheStrategy(diskCacheStrategy),onAnimationStatus)
    }

    override fun loadGif(playTimes: Int, urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoadGif(urlOrId, imageView, playTimes,
            RequestOptions()
                .placeholder(placeholderImg)
                .error(placeholderImg),null
        )
    }

    override fun loadGif(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoadGif(urlOrId, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions()
                .placeholder(placeholderImg)
                .error(placeholderImg),null
        )

    }

    override fun loadGif(urlOrId: Any, imageView: ImageView) {
        glideLoadGif(urlOrId, imageView, GifDrawable.LOOP_FOREVER, nullOptions,null)
    }

    override fun loadGif(
        urlOrId: Any,
        imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        glideLoadGif(urlOrId, imageView, GifDrawable.LOOP_FOREVER, getDiskCacheStrategy(diskCacheStrategy),null)
    }

    override fun loadCircleGif(urlOrId: Any, imageView: ImageView) {
        glideLoadGif(urlOrId, imageView, GifDrawable.LOOP_FOREVER, circleOptions,null)

    }

    override fun loadCircleGif(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoadGif(urlOrId, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions()
                .circleCrop()
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg))
                .error(CircleRoundDrawable(imageView.context, placeholderImg))
            ,null)
    }

    override fun loadCircleGif(playTimes: Int, urlOrId: Any, imageView: ImageView) {
        glideLoadGif(urlOrId, imageView, playTimes, circleOptions,null)
    }

    override fun loadCircleGif(playTimes: Int, urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoadGif(urlOrId, imageView, playTimes,
            RequestOptions()
                .circleCrop()
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg))
                .error(CircleRoundDrawable(imageView.context, placeholderImg))
            ,null)
    }

    override fun loadCornerGif(urlOrId: Any, imageView: ImageView, cornerRadius: Float) {
        glideLoadGif(urlOrId, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
            ,null)
    }

    override fun loadCornerGif(urlOrId: Any, imageView: ImageView, cornerRadius: Float, placeholderImg: Int) {
        glideLoadGif(urlOrId, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions()
                .transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
            ,null)
    }

    override fun loadCornerGif(playTimes: Int, urlOrId: Any, imageView: ImageView, cornerRadius: Float) {
        glideLoadGif(urlOrId, imageView, playTimes,
            RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
            ,null)
    }

    override fun loadCornerGif(playTimes: Int, urlOrId: Any, imageView: ImageView, cornerRadius: Float, placeholderImg: Int) {
        glideLoadGif(urlOrId, imageView, playTimes,
            RequestOptions()
                .transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
            ,null)
    }

    override fun loadBorderCornerGif(urlOrId: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius: Float) {
        glideLoadGif(urlOrId, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions().transform(BorderRoundTransform(borderWidth, borderColor, cornerRadius))
            ,null)
    }

    override fun loadBorderCornerGif(urlOrId: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius: Float, placeholderImg: Int) {
        glideLoadGif(urlOrId, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions()
                .transform(BorderRoundTransform(borderWidth, borderColor, cornerRadius))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
            ,null)
    }

    override fun loadBorderCornerGif(playTimes: Int, urlOrId: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius: Float) {
        glideLoadGif(urlOrId, imageView, playTimes,
            RequestOptions().transform(BorderRoundTransform(borderWidth, borderColor, cornerRadius))
            ,null)
    }

    override fun loadBorderCornerGif(playTimes: Int, urlOrId: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius: Float, placeholderImg: Int) {
        glideLoadGif(urlOrId, imageView, playTimes,
            RequestOptions()
                .transform(BorderRoundTransform(borderWidth, borderColor, cornerRadius))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
            ,null)
    }


    override fun loadImageWithRXCustomTarget(urlOrId: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>) {
        glideRXCustomTarget(urlOrId, context, rxCustomTarget, nullOptions)
    }

    override fun loadImageWithRXCustomTarget(urlOrId: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>, placeholderImg: Int) {
        glideRXCustomTarget(urlOrId, context, rxCustomTarget, RequestOptions().placeholder(placeholderImg).error(placeholderImg))
    }

    override fun loadImageWithRXCustomTarget(
        urlOrId: Any,
        context: Context,
        rxCustomTarget: RXCustomTarget<Bitmap>,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        glideRXCustomTarget(urlOrId, context, rxCustomTarget, getDiskCacheStrategy(diskCacheStrategy))
    }

    override fun load9Png(urlOrId: Any, view: View) {
        load9PngCombine(urlOrId, view, -1)
    }

    override fun load9Png(urlOrId: Any, view: View, placeholderImg: Int) {
        load9PngCombine(urlOrId, view, placeholderImg)
    }


    override fun clearImageDiskCache(context: Context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Thread { Glide.get(context).clearDiskCache() }.start()
            } else {
                Glide.get(context).clearDiskCache()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun clearImageMemoryCache(context: Context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context).clearMemory()
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }


    /**
     * Glide加载图片的最终方法
     * 所有的方法url都要将http换成https
     */
    private fun glideLoadBytes(bytes: ByteArray, imageView: ImageView, requestOptions: RequestOptions) {
        Glide.with(imageView.context).asBitmap().load(bytes).apply(requestOptions).into(imageView)
    }


    private fun load9PngCombine(urlOrId: Any,view: View,placeholderImg: Int) {
        ImageLoaderUtils.checkUrlOrId(urlOrId)
        when (urlOrId) {
            is String -> {
                glideLoad9Png(urlOrId,view,RequestOptions().placeholder(placeholderImg).error(placeholderImg))
            }

            is Int -> {
                val bitmap = BitmapFactory.decodeResource(view.context.resources, urlOrId) ?: return
                if (bitmap.ninePatchChunk == null) return
                val chunk: ByteArray = bitmap.ninePatchChunk
                if (NinePatch.isNinePatchChunk(chunk)) {
                    glideLoadDrawable(
                        NinePatchDrawable(view.context.resources,bitmap,chunk,NinePatchChunk.deserialize(chunk).mPaddings,null),
                        view,RequestOptions().placeholder(placeholderImg).error(placeholderImg)
                    )
                }
            }
        }
    }
    private fun glideLoad9Png(url: String, view: View, requestOptions: RequestOptions) {
        Glide.with(view.context).asFile().load(ImageLoaderUtils.replaceHttpToHttps(url)).apply(requestOptions)
            .into(object : CustomTarget<File>() {
                override fun onResourceReady(resource: File, transition: Transition<in File>?) {
                    try {
                        val fileInputStream = FileInputStream(resource)
                        ImageLoaderUtils.setNinePathImage(view.context, view, BitmapFactory.decodeStream(fileInputStream))
                        fileInputStream.close()
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }

    private fun glideLoadDrawable(drawable: Drawable, view: View, requestOptions: RequestOptions) {
        Glide.with(view.context).load(drawable).apply(requestOptions)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    view.background = resource
                }
                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }

    private fun glideRXCustomTarget(urlOrId: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>, requestOptions: RequestOptions) {
        ImageLoaderUtils.checkUrlOrId(urlOrId)
        Glide.with(context).asBitmap().load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId).apply(requestOptions).into(rxCustomTarget)
    }

    private fun glideLoad(urlOrId: Any, imageView: ImageView, requestOptions: RequestOptions,transition: Boolean,thumbnail:Boolean,
                          thumbnailWidth: Int,
                          thumbnailHeight: Int) {
        ImageLoaderUtils.checkUrlOrId(urlOrId)

        val glide =
            Glide
                .with(imageView.context)
                .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
                .apply(requestOptions)

        if (thumbnail){
            glide.thumbnail(Glide.with(imageView.context)
                .load(urlOrId)
                .override(thumbnailWidth, thumbnailHeight))
        }
        if (transition){
            glide.transition(DrawableTransitionOptions.withCrossFade())
        }
        glide.into(imageView)
    }

    private fun glideLoad(urlOrId: Any, view: View, requestOptions: RequestOptions,transition: Boolean,thumbnail:Boolean,
                          thumbnailWidth: Int,
                          thumbnailHeight: Int) {
        ImageLoaderUtils.checkUrlOrId(urlOrId)

        val glide =
            Glide
                .with(view.context)
                .asDrawable()
                .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
                .apply(requestOptions)

        if (thumbnail){
            glide.thumbnail(Glide.with(view.context)
                .load(urlOrId)
                .override(thumbnailWidth, thumbnailHeight))
        }
        if (transition){
            glide.transition(DrawableTransitionOptions.withCrossFade())
        }
        glide.into(object : CustomTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                view.background = resource
            }
            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    }

    private fun glideLoadGif(urlOrId: Any, imageView: ImageView, playTimes: Int, requestOptions: RequestOptions,onAnimationStatus: OnAnimationStatus?) {
        ImageLoaderUtils.checkUrlOrId(urlOrId)
        Glide.with(imageView.context).asGif()
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .listener(ImageLoaderUtils.gifDrawableRequestListener(playTimes,onAnimationStatus))
            .apply(requestOptions)
            .into(imageView)
    }
    private fun loadCornersImageCombine(urlOrId: Any, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum, placeholderImg: Int) {

        val requestOptions = RequestOptions()

        if (placeholderImg != -1){
            requestOptions
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
        }
        glideLoad(urlOrId, imageView,
            requestOptions.optionalTransform(RoundedCornersTransform(ImageLoaderUtils.dp2px(cornerRadius) + 0.5f, cornerTypeEnum)),
            false,false,0,0)
    }
    private fun getDiskCacheStrategy(diskCacheStrategy: DiskCacheStrategyEnum) : RequestOptions{
        val requestOptions = RequestOptions()
        when (diskCacheStrategy) {
            DiskCacheStrategyEnum.NONE -> {
                requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
            }
            DiskCacheStrategyEnum.ALL -> {
                requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
            }
            DiskCacheStrategyEnum.AUTOMATIC -> {
                requestOptions.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            }
        }
        return requestOptions
    }
}