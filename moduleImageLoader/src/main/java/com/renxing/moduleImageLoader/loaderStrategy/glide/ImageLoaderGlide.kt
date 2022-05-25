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
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.bumptech.glide.request.transition.Transition
import com.renxing.moduleImageLoader.imageUtils.ImageLoaderUtils
import com.renxing.moduleImageLoader.imageUtils.enumUtils.*
import com.renxing.moduleImageLoader.loaderStrategy.control.ImageLoaderInterface
import com.renxing.moduleImageLoader.loaderStrategy.control.OnAnimationStatus
import com.renxing.moduleImageLoader.loaderStrategy.control.RXRequestListener
import com.renxing.moduleImageLoader.loaderStrategy.glide.easyglide.config.CACHE_STRATEGY_NONE
import com.renxing.moduleImageLoader.loaderStrategy.glide.ninePic.NinePatchChunk
import com.renxing.moduleImageLoader.loaderStrategy.glide.placeholder.CircleRoundDrawable
import com.renxing.moduleImageLoader.loaderStrategy.glide.target.RXCustomTarget
import com.renxing.moduleImageLoader.loaderStrategy.glide.transformation.BorderRoundTransform
import com.renxing.moduleImageLoader.loaderStrategy.glide.transformation.CircleBorderTransformation
import com.renxing.moduleImageLoader.loaderStrategy.glide.easyglide.config.ImgConfigImpl
import com.renxing.moduleImageLoader.loaderStrategy.glide.easyglide.progress.GlideImageViewTarget
import com.renxing.moduleImageLoader.loaderStrategy.glide.easyglide.transformation.BlurTransformation
import com.renxing.moduleImageLoader.loaderStrategy.glide.easyglide.transformation.CircleWithBorderTransformation
import com.renxing.moduleImageLoader.loaderStrategy.glide.easyglide.transformation.RoundedCornersTransformation
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException

/**
 *@author  :  WuJianFeng
 */
@SuppressLint("CheckResult")
class ImageLoaderGlide : ImageLoaderInterface {

    //nullOptions：占位RequestOptions没作用
    private val nullOptions = RequestOptions()
    private val fitCenterOptions = RequestOptions().fitCenter()
    private val centerScopeOptions = RequestOptions().centerCrop()
    private val centerInsideOptions = RequestOptions().centerInside()
    private val skipMemoryOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
    private val circleOptions = RequestOptions().circleCrop()


    override fun loadImage(context: Context, imgConfigImpl: ImgConfigImpl) {
        glideLoadImage(context,imgConfigImpl)
    }

    override fun loadImage(urlOrIdOrUri: Any, imageView: ImageView, progressView: View,placeholderId: Int,errorHolder : Int) {
        glideLoad(urlOrIdOrUri,imageView,progressView,placeholderId,errorHolder)
    }
//    override fun loadCornersImage(url: String, imageView: ImageView, cornerRadius: Float, placeholderImg: Int) {
        //注释七牛方案
//        ImageLoaderUtils.checkAndAppendCornerUrl(urlOrIdOrUri, cornerRadius).apply {
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
//                    glideLoad(urlOrIdOrUri, imageView,
//                        RequestOptions()
//                            .transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
//                            .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
//                            .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius)
//                            )
//                    )
//                }
//            }
//        }
//        loadImage(imageView.context,
//            ImgConfigImpl
//                .builder()
//                .url(url)
//                .transformation(RoundedCornersTransformation(cornerRadius.toInt(), 0))
//                .isCrossFade(false)
//                .errorPic(placeholderImg)
//                .placeholder(placeholderImg)
//                .imageView(imageView)
//                .build())
//    }
//    override fun loadCornersImage(
//        url: String,
//        imageView: ImageView,
//        cornerRadius: Float,
//        placeholderId: Int,
//        transition: Boolean
//    ) {
//        glideLoadImage(imageView.context,
//            ImgConfigImpl
//                .builder()
//                .url(url)
//                .transformation(RoundedCornersTransformation(cornerRadius.toInt(), 0))
//                .errorPic(placeholderId)
//                .placeholder(placeholderId)
//                .imageView(imageView)
//                .build())
//    }

//    override fun loadCornersImageCenterCrop(
//        url: String,
//        imageView: ImageView,
//        cornerRadius: Float,
//        placeholderId: Int,
//        transition: Boolean
//    ) {
//        glideLoadImage(imageView.context,
//            ImgConfigImpl
//                .builder()
//                .url(url)
//                .transformation(CenterCrop(), RoundedCornersTransformation(cornerRadius.toInt(), 0))
//                .isCrossFade(false)
//                .errorPic(placeholderId)
//                .placeholder(placeholderId)
//                .imageView(imageView)
//                .build())
//    }



//    override fun loadBorderCircleImage(
//        url: String,
//        imageView: ImageView,
//        imageWidth : Int,
//        imageHeight : Int,
//        borderColor: Int,
//        borderWidth: Float,
//        placeholderId: Int
//    ) {
//
//        glideLoadImage(
//            imageView.context,
//            ImgConfigImpl
//                .builder()
//                .url(url)
//                .transformation(CircleWithBorderTransformation(borderWidth.toInt(), borderColor))
//                .isCrossFade(false)
//                .errorPic(placeholderId)
//                .placeholder(placeholderId)
//                .imageView(imageView)
//                .build()
//        )
//    }

//    override fun loadBorderCircleImage(
//        url: String,
//        imageView: ImageView,
//        borderColor: Int,
//        borderWidth: Float,
//        placeholderId: Int
//    ) {
//        glideLoadImage(
//            imageView.context,
//            ImgConfigImpl
//                .builder()
//                .url(url)
//                .transformation(CircleWithBorderTransformation(borderWidth.toInt(), borderColor))
//                .isCrossFade(false)
//                .errorPic(placeholderId)
//                .placeholder(placeholderId)
//                .imageView(imageView)
//                .build()
//        )
//    }



    override fun loadGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes, nullOptions,null)
    }

    override fun loadGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView,
                         diskCacheStrategy: DiskCacheStrategyEnum, onAnimationStatus: OnAnimationStatus) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes, getDiskCacheStrategy(diskCacheStrategy),onAnimationStatus)
    }

    override fun loadGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView, placeholderId: Int) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes,
            RequestOptions()
                .placeholder(placeholderId)
                .error(placeholderId),null
        )
    }

    override fun loadGif(urlOrIdOrUri: Any, imageView: ImageView, placeholderId: Int) {
        glideLoadGif(urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions()
                .placeholder(placeholderId)
                .error(placeholderId),null
        )

    }

    override fun loadGif(urlOrIdOrUri: Any, imageView: ImageView) {
        glideLoadGif(urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER, nullOptions,null)
    }

    override fun loadGif(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        glideLoadGif(urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER, getDiskCacheStrategy(diskCacheStrategy),null)
    }

    override fun loadCircleGif(urlOrIdOrUri: Any, imageView: ImageView) {
        glideLoadGif(urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER, circleOptions,null)

    }

    override fun loadCircleGif(urlOrIdOrUri: Any, imageView: ImageView, placeholderId: Int) {
        glideLoadGif(urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions()
                .circleCrop()
                .placeholder(CircleRoundDrawable(imageView.context, placeholderId))
                .error(CircleRoundDrawable(imageView.context, placeholderId))
            ,null)
    }

    override fun loadCircleGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes, circleOptions,null)
    }

    override fun loadCircleGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView, placeholderId: Int) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes,
            RequestOptions()
                .circleCrop()
                .placeholder(CircleRoundDrawable(imageView.context, placeholderId))
                .error(CircleRoundDrawable(imageView.context, placeholderId))
            ,null)
    }

    override fun loadCornerGif(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float) {
        glideLoadGif(urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
            ,null)
    }

    override fun loadCornerGif(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float, placeholderId: Int) {
        glideLoadGif(urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions()
                .transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderId).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderId).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
            ,null)
    }

    override fun loadCornerGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes,
            RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
            ,null)
    }

    override fun loadCornerGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float, placeholderId: Int) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes,
            RequestOptions()
                .transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderId).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderId).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
            ,null)
    }

    override fun loadBorderCornerGif(urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius: Float) {
        glideLoadGif(urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions().transform(BorderRoundTransform(borderWidth, borderColor, cornerRadius))
            ,null)
    }

    override fun loadBorderCornerGif(urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius: Float, placeholderId: Int) {
        glideLoadGif(urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions()
                .transform(BorderRoundTransform(borderWidth, borderColor, cornerRadius))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderId).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderId).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
            ,null)
    }

    override fun loadBorderCornerGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius: Float) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes,
            RequestOptions().transform(BorderRoundTransform(borderWidth, borderColor, cornerRadius))
            ,null)
    }

    override fun loadBorderCornerGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius: Float, placeholderId: Int) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes,
            RequestOptions()
                .transform(BorderRoundTransform(borderWidth, borderColor, cornerRadius))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderId).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderId).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
            ,null)
    }


    override fun loadImageWithRXCustomTarget(urlOrIdOrUri: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>) {
        glideRXCustomTarget(urlOrIdOrUri, context, rxCustomTarget, nullOptions)
    }

    override fun loadImageWithRXCustomTarget(
        urlOrIdOrUri: Any,
        context: Context,
        thumbnailWidth: Int,
        thumbnailHeight: Int,
        rxCustomTarget: RXCustomTarget<Bitmap>
    ) {
        glideRXCustomTarget(urlOrIdOrUri, context, thumbnailWidth, thumbnailHeight, rxCustomTarget)
    }

    override fun loadImageWithRXCustomTarget(urlOrIdOrUri: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>, placeholderId: Int) {
        glideRXCustomTarget(urlOrIdOrUri, context, rxCustomTarget, RequestOptions().placeholder(placeholderId).error(placeholderId))
    }

    override fun loadImageWithRXCustomTarget(
        urlOrIdOrUri: Any,
        context: Context,
        rxCustomTarget: RXCustomTarget<Bitmap>,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        glideRXCustomTarget(urlOrIdOrUri, context, rxCustomTarget, getDiskCacheStrategy(diskCacheStrategy))
    }

    override fun load9Png(urlOrIdOrUri: Any, view: View) {
        load9PngCombine(urlOrIdOrUri, view, -1)
    }

    override fun load9Png(urlOrIdOrUri: Any, view: View, placeholderId: Int) {
        load9PngCombine(urlOrIdOrUri, view, placeholderId)
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

    override fun clear(context: Context, imageView: ImageView) {
        glideClear(context, imageView)
    }

    override fun pauseRequests(context: Context) {
        glidePauseRequests(context)
    }

    override fun resumeRequests(context: Context) {
        glideResumeRequests(context)
    }

    override fun clearMemory(context: Context) {
        glideClearMemory(context)
    }

    override fun trimMemory(context: Context, level : Int) {
        glideTrimMemory(context,level)
    }



    /**
     * Glide加载图片的最终方法
     * 所有的方法url都要将http换成https
     */
    private fun glideLoadBytes(bytes: ByteArray, imageView: ImageView, requestOptions: RequestOptions) {
        Glide.with(imageView.context).asBitmap().dontAnimate().load(bytes).apply(requestOptions).into(imageView)
    }


    private fun load9PngCombine(urlOrIdOrUri: Any,view: View,placeholderId: Int) {
        ImageLoaderUtils.checkUrlOrId(urlOrIdOrUri)
        when (urlOrIdOrUri) {
            is String -> {
                glideLoad9Png(urlOrIdOrUri,view,RequestOptions().placeholder(placeholderId).error(placeholderId))
            }

            is Int -> {
                val bitmap = BitmapFactory.decodeResource(view.context.resources, urlOrIdOrUri) ?: return
                if (bitmap.ninePatchChunk == null) return
                val chunk: ByteArray = bitmap.ninePatchChunk
                if (NinePatch.isNinePatchChunk(chunk)) {
                    glideLoadDrawable(
                        NinePatchDrawable(view.context.resources,bitmap,chunk,NinePatchChunk.deserialize(chunk).mPaddings,null),
                        view,RequestOptions().placeholder(placeholderId).error(placeholderId)
                    )
                }
            }
        }
    }
    private fun glideLoad9Png(url: String, view: View, requestOptions: RequestOptions) {
        Glide.with(view.context).asFile().dontAnimate().load(ImageLoaderUtils.replaceHttpToHttps(url)).apply(requestOptions)
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
        Glide.with(view.context).load(drawable).dontAnimate().apply(requestOptions)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    view.background = resource
                }
                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }

    private fun glideRXCustomTarget(urlOrIdOrUri: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>, requestOptions: RequestOptions) {
        ImageLoaderUtils.checkUrlOrId(urlOrIdOrUri)
        Glide.with(context).asBitmap().dontAnimate().load(if (urlOrIdOrUri is String) ImageLoaderUtils.replaceHttpToHttps(urlOrIdOrUri) else urlOrIdOrUri).apply(requestOptions).into(rxCustomTarget)
    }

    private fun glideRXCustomTarget(urlOrIdOrUri: Any,context: Context,thumbnailWidth: Int,thumbnailHeight: Int,rxCustomTarget: RXCustomTarget<Bitmap>) {
        ImageLoaderUtils.checkUrlOrId(urlOrIdOrUri)
        Glide.with(context).asBitmap().dontAnimate()
            .load(if (urlOrIdOrUri is String) ImageLoaderUtils.replaceHttpToHttps(urlOrIdOrUri) else urlOrIdOrUri)
            .thumbnail(Glide.with(context).asBitmap().dontAnimate()
                .load(urlOrIdOrUri)
                .override(thumbnailWidth, thumbnailHeight))
            .into(rxCustomTarget)
    }

    private fun glideLoad(urlOrIdOrUri: Any, imageView: ImageView, progressView: View,placeholderId: Int,errorHolder : Int) {
        ImageLoaderUtils.checkUrlOrId(urlOrIdOrUri)

        Glide.with(imageView.context)
            .asBitmap().dontAnimate()
            .load(urlOrIdOrUri)
            .apply(
                RequestOptions().fitCenter().error(errorHolder)
                    .placeholder(placeholderId)
            )
            .listener(object : RequestListener<Bitmap?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Bitmap?>,
                    isFirstResource: Boolean
                ): Boolean {
                    progressView.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any,
                    target: Target<Bitmap?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    progressView.visibility = View.GONE
                    return false
                }
            })
            .into(imageView)
    }

    private fun glideLoad(urlOrIdOrUri: Any, imageView: ImageView, requestOptions: RequestOptions,transition: Boolean,thumbnail:Boolean,
                          thumbnailWidth: Int,
                          thumbnailHeight: Int) {
        ImageLoaderUtils.checkUrlOrId(urlOrIdOrUri)


        val glide =
            Glide
                .with(imageView.context)
                .load(if (urlOrIdOrUri is String) ImageLoaderUtils.replaceHttpToHttps(urlOrIdOrUri) else urlOrIdOrUri)
                .apply(requestOptions)
                .dontAnimate()


        if (thumbnail){
            glide.thumbnail(Glide.with(imageView.context)
                .load(urlOrIdOrUri).dontAnimate()
                .override(thumbnailWidth, thumbnailHeight))
        }
        if (transition){
            glide.transition(DrawableTransitionOptions.withCrossFade())
        }
        glide.into(imageView)
    }
    private fun glideLoad(urlOrIdOrUri: Any, imageView: ImageView, rxRequestListener: RXRequestListener<Drawable>) {
        ImageLoaderUtils.checkUrlOrId(urlOrIdOrUri)

        Glide.with(imageView.context)
            .load(urlOrIdOrUri)
            .listener(rxRequestListener)
            .into(imageView)
    }

    private fun glideLoad(urlOrIdOrUri: Any, view: View, requestOptions: RequestOptions,transition: Boolean,thumbnail:Boolean,
                          thumbnailWidth: Int,
                          thumbnailHeight: Int) {
        ImageLoaderUtils.checkUrlOrId(urlOrIdOrUri)

        val glide =
            Glide
                .with(view.context)
                .asDrawable()
                .load(if (urlOrIdOrUri is String) ImageLoaderUtils.replaceHttpToHttps(urlOrIdOrUri) else urlOrIdOrUri)
                .apply(requestOptions)

        if (thumbnail){
            glide.thumbnail(Glide.with(view.context)
                .load(urlOrIdOrUri).dontAnimate()
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

    private fun glideLoadGif(urlOrIdOrUri: Any, imageView: ImageView, playTimes: Int, requestOptions: RequestOptions,onAnimationStatus: OnAnimationStatus?) {
        ImageLoaderUtils.checkUrlOrId(urlOrIdOrUri)
        Glide.with(imageView.context).asGif()
            .load(if (urlOrIdOrUri is String) ImageLoaderUtils.replaceHttpToHttps(urlOrIdOrUri) else urlOrIdOrUri)
            .listener(ImageLoaderUtils.gifDrawableRequestListener(playTimes,onAnimationStatus))
            .apply(requestOptions)
            .into(imageView)
    }
    private fun loadCornersImageCombine(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum, placeholderId: Int) {

        val requestOptions = RequestOptions()

        if (placeholderId != -1){
            requestOptions
                .placeholder(CircleRoundDrawable(imageView.context, placeholderId).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderId).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
        }
        glideLoad(urlOrIdOrUri, imageView,
            requestOptions.optionalTransform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt())),
            transition = false, thumbnail = false, thumbnailWidth = 0, thumbnailHeight = 0
        )
    }
    private fun getDiskCacheStrategy(diskCacheStrategy: DiskCacheStrategyEnum) : RequestOptions{
        val requestOptions = RequestOptions()
        when (diskCacheStrategy) {
            DiskCacheStrategyEnum.RESOURCE -> {
                requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE).skipMemoryCache(true)
            }
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

    private fun glideClear(context: Context, imageView: ImageView) {
        Glide.with(context).clear(imageView)
    }

    private fun glideResumeRequests(context: Context) {
        Glide.with(context).resumeRequests()
    }

    private fun glidePauseRequests(context: Context) {
        Glide.with(context).pauseRequests()
    }

    private fun glideClearMemory(context: Context) {
        Glide.get(context).clearMemory()
    }

    private fun glideTrimMemory(context: Context, level : Int) {
        Glide.get(context).trimMemory(level)
    }

    private fun glideLoadImage(context: Context, config: ImgConfigImpl) {
        val glideRequest = if (config.drawableId != 0) {
            Glide.with(context).load(config.drawableId)
        } else {
            Glide.with(context).load(config.url)
        }
        glideRequest.apply {
            when (config.cacheStrategy) {
                0 -> diskCacheStrategy(DiskCacheStrategy.ALL)
                1 -> diskCacheStrategy(DiskCacheStrategy.NONE)
                2 -> diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                3 -> diskCacheStrategy(DiskCacheStrategy.DATA)
                4 -> diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                else -> diskCacheStrategy(DiskCacheStrategy.ALL)
            }
            if (config.isCrossFade) {
                val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                transition(DrawableTransitionOptions.withCrossFade(factory))
            }
            if (config.isCrossFade) {
                val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                transition(DrawableTransitionOptions.withCrossFade(factory))
            }
            if (config.isImageRadius()) {
                transform(RoundedCorners(config.imageRadius))
            }
            if (config.isBlurImage) {
                transform(BlurTransformation(context, config.blurValue))
            }
            //glide用它来改变图形的形状
            if (config.transformation != null) {
                transform(*config.transformation)
            }
            if (config.placeHolderDrawable != null) {
                placeholder(config.placeHolderDrawable)
            }
            //设置占位符
            if (config.placeholder != 0) {
                placeholder(config.placeholder)
            }
            //设置错误的图片
            if (config.errorPic != 0) {
                error(config.errorPic)
            }
            //设置请求 url 为空图片
            if (config.fallback != 0) {
                fallback(config.fallback)
            }
            if (config.resizeX != 0 && config.resizeY != 0) {
                override(config.resizeX, config.resizeY)
            }
            if (config.isCropCenter) {
                centerCrop()
            }
            if (config.isCropCircle) {
                circleCrop()
            }
            if (config.formatType != null) {
                format(config.formatType)
            }
            if (config.isFitCenter) {
                fitCenter()
            }
            if (config.requestListener != null) {
                addListener(config.requestListener)
            }
            into(GlideImageViewTarget(config.imageView, config.url))
        }



    }
}