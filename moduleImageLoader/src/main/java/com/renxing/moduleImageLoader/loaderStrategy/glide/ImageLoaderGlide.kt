package com.renxing.moduleImageLoader.loaderStrategy.glide

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.NinePatch
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.NinePatchDrawable
import android.net.Uri
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException

import com.bumptech.glide.load.resource.bitmap.*
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.renxing.moduleImageLoader.imageUtils.ImageLoaderUtils
import com.renxing.moduleImageLoader.imageUtils.ImgLoadParams
import com.renxing.moduleImageLoader.imageUtils.NinePatchChunk
import com.renxing.moduleImageLoader.imageUtils.enumUtils.*
import com.renxing.moduleImageLoader.loaderStrategy.control.ImageLoaderInterface
import com.renxing.moduleImageLoader.loaderStrategy.control.OnAnimationStatus
import com.renxing.moduleImageLoader.loaderStrategy.control.RXRequestListener
import com.renxing.moduleImageLoader.loaderStrategy.glide.config.ImgLoadConfigImpl
import com.renxing.moduleImageLoader.loaderStrategy.glide.placeholder.CircleRoundDrawable
import com.renxing.moduleImageLoader.loaderStrategy.glide.target.RXCustomTarget
import com.renxing.moduleImageLoader.loaderStrategy.glide.transformation.*
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
    override fun loadImage(imgLoadParams: ImgLoadParams) {

        checkImgType(imgLoadParams)
    }
    override fun loadGifImage(imgLoadParams: ImgLoadParams) {
        imgLoadParams.requestBuilderTypeEnum(RequestBuilderTypeEnum.GIF_DRAWABLE)
        checkImgType(imgLoadParams)
    }
    override fun loadBitmapImage(imgLoadParams: ImgLoadParams) {
        imgLoadParams.requestBuilderTypeEnum(RequestBuilderTypeEnum.BITMAP)
        checkImgType(imgLoadParams)
    }


    override fun loadCircleImage(imgLoadParams: ImgLoadParams) {
        imgLoadParams.transitionEnum(TransitionEnum.CircleCrop)
        checkImgType(imgLoadParams)
    }
    override fun loadCornersImage(imgLoadParams: ImgLoadParams) {
        imgLoadParams.transitionEnum(TransitionEnum.CORNER)
        checkImgType(imgLoadParams)
    }

    override fun loadBorderCircleImage(imgLoadParams: ImgLoadParams) {
        imgLoadParams.transitionEnum(TransitionEnum.BORDER_CIRCLE)
        checkImgType(imgLoadParams)
    }
    override fun load9Png(imgLoadParams: ImgLoadParams) {
        checkImgType(imgLoadParams)
    }


    private fun checkImgType(imgLoadParams: ImgLoadParams) {
        if (imgLoadParams.requestBuilderTypeEnum != null) {
            when (imgLoadParams.requestBuilderTypeEnum) {
                RequestBuilderTypeEnum.GIF_DRAWABLE -> {
                    glideLoadImageGif(imgLoadParams.context, TransformImgLoadParams(imgLoadParams))
                }
                RequestBuilderTypeEnum.BITMAP -> {
                    glideLoadImageBitmap(
                        imgLoadParams.context,
                        TransformImgLoadParams(imgLoadParams)
                    )
                }
                else -> {
                    glideLoadImage(imgLoadParams.context, TransformImgLoadParams(imgLoadParams))

                }
            }
        }
    }


    fun TransformImgLoadParams(params: ImgLoadParams) : ImgLoadConfigImpl{
        val imgLoadConfigImpl = ImgLoadConfigImpl.builder()
        if (!TextUtils.isEmpty(params.url)){
            imgLoadConfigImpl.load(ImageLoaderUtils.replaceHttpToHttps(params.url!!))
        }
        if (params.id != 0){
            imgLoadConfigImpl.load(params.id)
        }
        if (params.uri != null){
            imgLoadConfigImpl.load(params.uri)
        }
        if (params.drawable != null){
            imgLoadConfigImpl.load(params.drawable)
        }
        if (params.colorDrawable != null){
            imgLoadConfigImpl.load(params.colorDrawable)
        }
        if (params.imageView != null){
            imgLoadConfigImpl.imageView(params.imageView)
        }

        if (params.imageWidth != 0 && params.imageHeight != 0){
            imgLoadConfigImpl.resize(params.imageWidth,params.imageHeight)
        }
        if (params.diskcacheStrategyEnum != null){
            imgLoadConfigImpl.cacheStrategy(params.diskcacheStrategyEnum!!)
        }
        if (params.rxRequestListenerDrawable != null){
            imgLoadConfigImpl.requestListenerDrawable(params.rxRequestListenerDrawable)
        }
        if (params.rxRequestListenerBitmap != null){
            imgLoadConfigImpl.requestListenerBitmap(params.rxRequestListenerBitmap)
        }
        if (params.rxRequestListenerGifDrawable != null){
            imgLoadConfigImpl.requestListenerGifDrawable(params.rxRequestListenerGifDrawable)
        }
        if (params.rxCustomTargetDrawable != null){
            imgLoadConfigImpl.rxDrawableTarget(params.rxCustomTargetDrawable)
        }
        if (params.rxCustomTargetBitmap != null){
            imgLoadConfigImpl.rxBitmapTarget(params.rxCustomTargetBitmap)
        }
        if (params.rxCustomTargetGifDrawable != null){
            imgLoadConfigImpl.rxGifDrawableTarget(params.rxCustomTargetGifDrawable)
        }
        if (params.fitCenter){
            imgLoadConfigImpl.isFitCenter(true)
        }
        if (params.centerCrop){
            imgLoadConfigImpl.isCropCenter(true)
        }
        if (params.centerInside){
            imgLoadConfigImpl.isCenterInside(true)
        }
        if (params.circleCrop){
            imgLoadConfigImpl.isCropCircle(true)
        }

        if (params.placeholder != 0){
            setPlaceHolder(params,imgLoadConfigImpl)
        }

        if (params.transitionEnum != null && params.transitionEnum.size > 0){
            imgLoadConfigImpl.transformation(*getBitmapTransformation(params))
        }
        if (params.requestBuilderTypeEnum != null){
            imgLoadConfigImpl.requestBuilderTypeEnum(params.requestBuilderTypeEnum!!)
        }

        if (params.priority != null){
            imgLoadConfigImpl.priorityEnum(params.priority)
        }
        if (params.dontAnimate){
            imgLoadConfigImpl.dontAnimate(params.dontAnimate)
        }
        if (params.crossfade){
            imgLoadConfigImpl.isCrossFade(params.crossfade)
        }

        return imgLoadConfigImpl.build()
    }

    fun setPlaceHolder(params: ImgLoadParams,imgLoadConfigImpl : ImgLoadConfigImpl.Builder) {
        params.transitionEnum.forEach {
            when(it){
                TransitionEnum.BORDER_CIRCLE,
                TransitionEnum.CircleCrop -> {
                    imgLoadConfigImpl
                        .placeholder(CircleRoundDrawable(params.context, params.placeholder).setType(CircleRoundDrawable.TYPE_CIRCLE))
                        .error(CircleRoundDrawable(params.context, params.placeholder).setType(CircleRoundDrawable.TYPE_CIRCLE))
                }
                TransitionEnum.CORNER -> {
                    imgLoadConfigImpl
                        .placeholder(CircleRoundDrawable(params.context, params.placeholder).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(params.cornerRadius))
                        .error(CircleRoundDrawable(params.context, params.placeholder).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(params.cornerRadius))
                }
            }
        }
    }



    fun getBitmapTransformation(params: ImgLoadParams) : Array<BitmapTransformation>{
        var bitmapTransformation  = ArrayList<BitmapTransformation>()
        params.transitionEnum.forEach {
            when(it){
                TransitionEnum.FitCenter -> {
                    bitmapTransformation.add(FitCenter())
                }
                TransitionEnum.CenterCrop -> {
                    bitmapTransformation.add(CenterCrop())
                }
                TransitionEnum.CenterInside -> {
                    bitmapTransformation.add(CenterInside())
                }
                TransitionEnum.CircleCrop -> {
                    bitmapTransformation.add(CircleCrop())
                }
                TransitionEnum.CORNER -> {
                    bitmapTransformation.add(RoundedCornersTransformation(params.cornerRadius.toInt(), 0))
                }
                TransitionEnum.BORDER_CIRCLE -> {
                    bitmapTransformation.add(CircleWithBorderTransformation(params.borderWidth, params.borderColor))
                }
            }
        }

        return bitmapTransformation.toTypedArray()
    }


    override fun loadImage(urlOrIdOrUri: Any, imageView: ImageView) {
        glideLoad(urlOrIdOrUri, imageView, nullOptions,
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadImage(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        rxRequestListener: RXRequestListener<Drawable>
    ) {
        glideLoad(urlOrIdOrUri, imageView, rxRequestListener)
    }

    override fun loadImage(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(urlOrIdOrUri, imageView, RequestOptions().placeholder(placeholderImg).error(placeholderImg),
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int) {
        glideLoad(ImageLoaderUtils.appendUrl(url, width, height, false), imageView,nullOptions,
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadImage(urlOrIdOrUri: Any, imageView: ImageView, progressView: View,placeholderImg: Int,errorHolder : Int) {
        glideLoad(urlOrIdOrUri,imageView,progressView,placeholderImg,errorHolder)
    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int, placeholderImg: Int) {
        glideLoad(ImageLoaderUtils.appendUrl(url, width, height, false), imageView,
            RequestOptions().placeholder(placeholderImg).error(placeholderImg),
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadImageWithFitCenter(urlOrIdOrUri: Any, imageView: ImageView) {
        glideLoad(urlOrIdOrUri, imageView, fitCenterOptions,
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )

    }

    override fun loadImageWithFitCenter(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(urlOrIdOrUri, imageView, RequestOptions().fitCenter().placeholder(placeholderImg).error(placeholderImg),
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadImageWithFitCenter(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategyEnum: DiskCacheStrategyEnum,
        priorityEnum: PriorityEnum
    ) {
        glideLoad(urlOrIdOrUri,imageView,
            getDiskCacheStrategy(diskCacheStrategyEnum).fitCenter().placeholder(placeholderImg).error(placeholderImg).priority(getPriority(priorityEnum)),
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0)
    }

    override fun loadImageWithCenterCrop(urlOrIdOrUri: Any, imageView: ImageView) {
        glideLoad(urlOrIdOrUri, imageView, centerScopeOptions,
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadImageWithCenterCrop(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(urlOrIdOrUri, imageView, RequestOptions().centerCrop().placeholder(placeholderImg).error(placeholderImg),
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadImageWithCenterCrop(urlOrIdOrUri: Any,imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        glideLoad(urlOrIdOrUri, imageView, getDiskCacheStrategy(diskCacheStrategy).centerCrop(),
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadImageWithCenterCrop(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int,
                                         diskCacheStrategy: DiskCacheStrategyEnum, transition: Boolean) {

        glideLoad(urlOrIdOrUri, imageView, getDiskCacheStrategy(diskCacheStrategy).centerCrop().placeholder(placeholderImg).error(placeholderImg),transition,false,0,0)

    }

    override fun loadImageWithCenterCrop(
        urlOrIdOrUri: Any,
        view: View,
        thumbnail: Boolean,
        thumbnailWidth: Int,
        thumbnailHeight: Int
    ) {
        glideLoad(urlOrIdOrUri, view, RequestOptions().centerCrop(),
            transition = false,
            thumbnail = true,
            thumbnailWidth = thumbnailWidth,
            thumbnailHeight = thumbnailHeight
        )


    }

    override fun loadImageWithCenterCrop(urlOrIdOrUri: Any,imageView: ImageView,placeholderImg: Int,diskCacheStrategy: DiskCacheStrategyEnum) {
        glideLoad(urlOrIdOrUri, imageView, getDiskCacheStrategy(diskCacheStrategy).centerCrop().placeholder(placeholderImg).error(placeholderImg),
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )

    }

    override fun loadImageWithCenterCrop(urlOrIdOrUri: Any,imageView: ImageView,placeholderImg: Int,transition: Boolean) {
        glideLoad(urlOrIdOrUri, imageView, RequestOptions().centerCrop().placeholder(placeholderImg).error(placeholderImg),transition,false,0,0)
    }

    override fun loadImageWithCenterCrop(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int, diskCacheStrategy: DiskCacheStrategyEnum,
                                         transition: Boolean, thumbnail: Boolean, thumbnailWidth: Int, thumbnailHeight: Int) {
        glideLoad(urlOrIdOrUri, imageView,
            getDiskCacheStrategy(diskCacheStrategy).centerCrop().placeholder(placeholderImg).error(placeholderImg),transition,thumbnail,
            thumbnailWidth, thumbnailHeight

        )
    }

    override fun loadImageWithCenterInside(urlOrIdOrUri: Any, imageView: ImageView) {
        glideLoad(urlOrIdOrUri, imageView, centerInsideOptions,
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadImageWithCenterInside(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(urlOrIdOrUri, imageView,
            RequestOptions().centerInside().placeholder(placeholderImg).error(placeholderImg),
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadImageWithCenterInside(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int,
                                           diskCacheStrategy: DiskCacheStrategyEnum, transition: Boolean) {
        glideLoad(urlOrIdOrUri, imageView,
            getDiskCacheStrategy(diskCacheStrategy).centerInside().placeholder(placeholderImg).error(placeholderImg),transition,false,0,0)
    }

    override fun loadImageWithCenterInside(urlOrIdOrUri: Any,imageView: ImageView,placeholderImg: Int,diskCacheStrategy: DiskCacheStrategyEnum) {
        glideLoad(urlOrIdOrUri, imageView,
            getDiskCacheStrategy(diskCacheStrategy).centerInside().placeholder(placeholderImg).error(placeholderImg).diskCacheStrategy(DiskCacheStrategy.ALL),
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadImageWithCenterInside(urlOrIdOrUri: Any,imageView: ImageView,placeholderImg: Int,transition: Boolean) {

        glideLoad(urlOrIdOrUri, imageView,
            RequestOptions().centerInside().placeholder(placeholderImg).error(placeholderImg).diskCacheStrategy(DiskCacheStrategy.ALL),transition,false,0,0)

    }


    override fun loadImageWithSkipCache(urlOrIdOrUri: Any, imageView: ImageView) {
        glideLoad(urlOrIdOrUri, imageView, skipMemoryOptions,
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadImageWithSkipCache(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(urlOrIdOrUri, imageView,
            RequestOptions()
                .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(placeholderImg).error(placeholderImg), transition = false
            , thumbnail = false, thumbnailWidth = 0, thumbnailHeight = 0
        )
    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int) {
        glideLoad(ImageLoaderUtils.appendUrl(url, width, height, false), imageView, skipMemoryOptions,
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int, placeholderImg: Int) {
        glideLoad(ImageLoaderUtils.appendUrl(url, width, height, false), imageView,
            RequestOptions()
                .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(placeholderImg).error(placeholderImg), transition = false
            , thumbnail = false, thumbnailWidth = 0, thumbnailHeight = 0
        )
    }

    override fun loadCircleImage(urlOrIdOrUri: Any, imageView: ImageView) {
        glideLoad(urlOrIdOrUri, imageView, circleOptions,
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadCircleImage(urlOrIdOrUri: Any,imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        glideLoad(urlOrIdOrUri, imageView, getDiskCacheStrategy(diskCacheStrategy),
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadCircleImage(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(urlOrIdOrUri, imageView,
            RequestOptions().circleCrop()
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE)), transition = false
            , thumbnail = false, thumbnailWidth = 0, thumbnailHeight = 0
        )
    }

    override fun loadCircleImage(urlOrIdOrUri: Any,imageView: ImageView,placeholderImg: Int,diskCacheStrategy: DiskCacheStrategyEnum) {
        glideLoad(urlOrIdOrUri, imageView,
            getDiskCacheStrategy(diskCacheStrategy).circleCrop()
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE)), transition = false
            , thumbnail = false, thumbnailWidth = 0, thumbnailHeight = 0
        )
    }


    override fun loadCircleImageCenterCrop(url: String,imageView: ImageView,placeholderImg: Int,diskCacheStrategy: DiskCacheStrategyEnum) {
        glideLoadImage(imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .transformation(CenterCrop(),CircleCrop())
                .error(placeholderImg)
                .cacheStrategy(diskCacheStrategy)
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .imageView(imageView)
                .build())
    }

    override fun loadCircleImageCenterCrop(url: String,imageView: ImageView,placeholderImg: Int,diskCacheStrategy: DiskCacheStrategyEnum,crossFade : Boolean) {

        glideLoadImage(imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .transformation(CenterCrop(),CircleCrop())
                .error(placeholderImg)
                .isCrossFade(crossFade)
                .cacheStrategy(diskCacheStrategy)
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .imageView(imageView)
                .build())
    }

    override fun loadCircleImageCenterCrop(
        id: Int,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum,
        crossFade: Boolean
    ) {

        glideLoadImage(imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(id)
                .transformation(CenterCrop(),CircleCrop())
                .error(placeholderImg)
                .isCrossFade(crossFade)
                .cacheStrategy(diskCacheStrategy)
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .imageView(imageView)
                .build())
    }

    override fun loadCircleImageCenterCrop(
        url: String,
        imageView: ImageView,
        imageWidth: Float,
        imageHeight: Float,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {

        glideLoadImage(imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .resize(imageWidth.toInt(),imageHeight.toInt())
                .transformation(CenterCrop(),CircleCrop())
                .error(placeholderImg)
                .cacheStrategy(diskCacheStrategy)
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .imageView(imageView)
                .build())
    }

    override fun loadCircleImageCenterCrop(
        url: String,
        imageView: ImageView,
        imageWidth: Float,
        imageHeight: Float,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum,
        crossFade: Boolean
    ) {

        glideLoadImage(imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .resize(imageWidth.toInt(),imageHeight.toInt())
                .transformation(CenterCrop(),CircleCrop())
                .error(placeholderImg)
                .isCrossFade(crossFade)
                .cacheStrategy(diskCacheStrategy)
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .imageView(imageView)
                .build())
    }

    override fun loadCircleImageCenterCrop(
        imgId: Int,
        imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {

        glideLoadImage(imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(imgId)
                .transformation(CenterCrop(),CircleCrop())
                .cacheStrategy(diskCacheStrategy)
                .imageView(imageView)
                .build())
    }

    override fun loadCircleImageCenterCrop(
        uri: Uri,
        imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        Glide
            .with(imageView.context)
            .load(uri)
            .transform(CenterCrop(),CircleCrop())
            .apply(getDiskCacheStrategy(diskCacheStrategy))
            .into(imageView)
    }

    override fun loadCircleImageCenterCrop(
        uri: Uri,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        Glide
            .with(imageView.context)
            .load(uri)
            .transform(CenterCrop(),CircleCrop())
            .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
            .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
            .apply(getDiskCacheStrategy(diskCacheStrategy))
            .into(imageView)
    }

    override fun loadCircleImageCenterCrop(
        colorDrawable: ColorDrawable,
        imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        Glide
            .with(imageView.context)
            .load(colorDrawable)
            .transform(CenterCrop(),CircleCrop())
            .into(imageView)
    }

    override fun loadCircleImageCenterCrop(
        colorDrawable: ColorDrawable,
        imageView: ImageView,
        diskCacheStrategy: DiskCacheStrategyEnum,
        crossFade: Boolean
    ) {
        Glide
            .with(imageView.context)
            .load(colorDrawable)
            .transition(DrawableTransitionOptions.withCrossFade())
            .transform(CenterCrop(),CircleCrop())
            .into(imageView)
    }

    override fun loadCircleImageCenterInside(
        url: String,
        imageView: ImageView,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        Glide
            .with(imageView.context)
            .load(url)
            .transform(CenterInside(),CircleCrop())
            .into(imageView)
    }

    override fun loadCornersImage(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float) {
        //注释七牛方案
//        ImageLoaderUtils.checkAndAppendCornerUrl(urlOrIdOrUri, cornerRadius).apply {
//            when (this) {
//                is String -> {
//                    glideLoad(this, imageView,nullOptions)
//                }
//                else -> {
//                    glideLoad(urlOrIdOrUri, imageView,
//                        RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
//                    )
//                }
//            }
//        }

        glideLoad(urlOrIdOrUri, imageView,
            RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt())),false
            ,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadCornersImage(url: String, imageView: ImageView, cornerRadius: Float, placeholderImg: Int) {


        glideLoadImage(imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .transformation(RoundedCornersTransformation(cornerRadius.toInt(), 0))
                .isCrossFade(false)
                .error(placeholderImg)
                .placeholder(placeholderImg)
                .imageView(imageView)
                .build())
    }

    override fun loadCornersImage(
        url: String,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int,
        transition: Boolean
    ) {

        glideLoadImage(imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .transformation(RoundedCornersTransformation(cornerRadius.toInt(), 0))
                .isCrossFade(false)
                .error(placeholderImg)
                .placeholder(placeholderImg)
                .imageView(imageView)
                .build())
    }

    override fun loadCornersImageCenterCrop(
        url: String,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int,
        transition: Boolean
    ) {

        //todo 其他相同情况也需要判空
        if (TextUtils.isEmpty(url)){
            return
        }

        if (imageView.context is Activity) {
            if ((imageView.context as Activity).isFinishing || (imageView.context as Activity).isDestroyed) {
                return
            }
        }
        if (checkUrl(url)) {
            return
        }
        glideLoadImage(imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .transformation(CenterCrop(), RoundedCornersTransformation(cornerRadius.toInt(), 0))
                .isCrossFade(transition)
                .error(placeholderImg)
                .placeholder(placeholderImg)
                .imageView(imageView)
                .build())
    }

    fun checkUrl(url: String): Boolean {
        return if (TextUtils.isEmpty(url)) {
            true
        } else url.endsWith("null")
    }

    override fun loadCornersImageCenterCrop(
        url: String,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {

        glideLoadImage(imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .transformation(CenterCrop(), RoundedCornersTransformation(cornerRadius.toInt(), 0))
                .cacheStrategy(diskCacheStrategy)

                .error(placeholderImg)
                .placeholder(placeholderImg)
                .imageView(imageView)
                .build())
    }

    override fun loadCornersImageCenterCrop(
        url: String,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum,
        crossFade: Boolean
    ) {

        glideLoadImage(imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .isCrossFade(crossFade)
                .transformation(CenterCrop(), RoundedCornersTransformation(cornerRadius.toInt(), 0))
                .cacheStrategy(diskCacheStrategy)
                .error(placeholderImg)
                .placeholder(placeholderImg)
                .imageView(imageView)
                .build())
    }

    override fun loadCornersImageCenterCrop(
        id: Int,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum,
        crossFade: Boolean
    ) {

        glideLoadImage(imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(id)
                .transformation(CenterCrop(), RoundedCornersTransformation(cornerRadius.toInt(), 0))
                .isCrossFade(crossFade)
                .cacheStrategy(diskCacheStrategy)
                .error(placeholderImg)
                .placeholder(placeholderImg)
                .imageView(imageView)
                .build())
    }


    override fun loadCornersImage(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        glideLoad(urlOrIdOrUri, imageView,
            getDiskCacheStrategy(diskCacheStrategy)
                .transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius)
                ), transition = false
            , thumbnail = false, thumbnailWidth = 0, thumbnailHeight = 0
        )
    }

    override fun loadCornersImage(
        context: Context,
        urlOrIdOrUri: Any,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        glideLoad(context,urlOrIdOrUri, imageView,
            getDiskCacheStrategy(diskCacheStrategy)
                .transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius)
                ), transition = false
            , thumbnail = false, thumbnailWidth = 0, thumbnailHeight = 0
        )
    }

    override fun loadCornersImage(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum) {
        //注释七牛方案
//        when (cornerType) {
//            RXImageLoaderConstant.CornerType.ALL -> {
//                ImageLoaderUtils.checkAndAppendCornerUrl(urlOrIdOrUri, cornerRadius).apply {
//                    when (this) {
//                        is String -> {
//                            glideLoad(this, imageView,nullOptions)
//                        }
//                        else -> {
//                            loadCornersImageConbine1(urlOrIdOrUri, imageView, cornerRadius, cornerType)
//                        }
//                    }
//                }
//            }
//
//            else -> {
//                loadCornersImageConbine1(urlOrIdOrUri, imageView, cornerRadius, cornerType)
//            }
//        }

        loadCornersImageCombine(urlOrIdOrUri, imageView, cornerRadius, cornerTypeEnum,-1)


    }

    override fun loadCornersImage(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum, placeholderImg: Int) {
        //注释七牛方案
//        when (cornerType) {
//            RXImageLoaderConstant.CornerType.ALL -> {
//                ImageLoaderUtils.checkAndAppendCornerUrl(urlOrIdOrUri, cornerRadius).apply {
//                    when (this) {
//                        is String -> {
//                            glideLoad(this, imageView,
//                                RequestOptions()
//                                    .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
//                                    .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle(cornerRadius))
//                            )
//                        }
//                        else -> {
//                            loadCornersImageConbine2(urlOrIdOrUri, imageView, cornerRadius, cornerType, placeholderImg)
//                        }
//                    }
//                }
//            }
//
//            else -> {
//                loadCornersImageConbine2(urlOrIdOrUri, imageView, cornerRadius, cornerType, placeholderImg)
//            }
//        }

        loadCornersImageCombine(urlOrIdOrUri, imageView, cornerRadius, cornerTypeEnum, placeholderImg)

    }

    override fun loadCornersImage(bitmap: Bitmap, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum) {
        glideLoadBytes(ImageLoaderUtils.bitmap2Bytes(bitmap), imageView,
            RequestOptions().optionalTransform(
                RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt())
            )
        )

    }

    override fun loadCornersImage(bitmap: Bitmap, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum, placeholderImg: Int) {
        glideLoadBytes(
            ImageLoaderUtils.bitmap2Bytes(bitmap), imageView,
            RequestOptions()
                .optionalTransform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
        )
    }

    override fun loadCornersImageCenterCrop(
        url: String,
        imageView: ImageView,
        cornerRadius: Float,
        placeholderImg: Int
    ) {


        glideLoadImage(imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .transformation(CenterCrop(), RoundedCornersTransformation(cornerRadius.toInt(), 0))
                .isCrossFade(false)
                .error(placeholderImg)
                .placeholder(placeholderImg)
                .imageView(imageView)
                .build())
    }

    override fun loadCornersImageCenterCrop(
        url: String,
        imageView: ImageView,
        imageViewWidth: Float,
        imageViewHeight: Float,
        cornerRadius: Float,
        placeholderImg: Int
    ) {

        glideLoadImage(imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .resize(imageViewWidth.toInt(),imageViewHeight.toInt())
                .transformation(CenterCrop(), RoundedCornersTransformation(cornerRadius.toInt(), 0))
                .isCrossFade(false)
                .error(placeholderImg)
                .placeholder(placeholderImg)
                .imageView(imageView)
                .build())
    }

    override fun loadCornersImageCenterCrop(
        url: String,
        imageView: ImageView,
        imageViewWidth: Float,
        imageViewHeight: Float,
        cornerRadius: Float,
        placeholderImg: Int,
        crossFade: Boolean
    ) {

        glideLoadImage(imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .resize(imageViewWidth.toInt(),imageViewHeight.toInt())
                .transformation(CenterCrop(), RoundedCornersTransformation(cornerRadius.toInt(), 0))
                .isCrossFade(crossFade)
                .error(placeholderImg)
                .placeholder(placeholderImg)
                .imageView(imageView)
                .build())
    }

    override fun loadCornersImageCenterCrop(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        cornerRadius: Float,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {
        Glide
            .with(imageView.context)
            .load(urlOrIdOrUri)
            .transform(CenterCrop(), RoundedCornersTransform(cornerRadius, CornerTypeEnum.ALL))
            .apply(getDiskCacheStrategy(diskCacheStrategy))
            .into(imageView)
    }

    override fun loadCornersImageCenterCrop(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        cornerRadius: Float,
        diskCacheStrategy: DiskCacheStrategyEnum,
        crossFade: Boolean
    ) {
        Glide
            .with(imageView.context)
            .load(urlOrIdOrUri)
            .transition(DrawableTransitionOptions.withCrossFade())
            .transform(CenterCrop(), RoundedCornersTransform(cornerRadius, CornerTypeEnum.ALL))
            .apply(getDiskCacheStrategy(diskCacheStrategy))
            .into(imageView)
    }

    override fun loadCornersImageCenterCrop(
        colorDrawable: ColorDrawable,
        imageView: ImageView,
        cornerRadius: Float
    ) {
        Glide
            .with(imageView.context)
            .load(colorDrawable)

            .transform(CenterCrop(), RoundedCornersTransformation(cornerRadius.toInt(), 0))
            .into(imageView)
    }

    override fun loadCornersImageWithCenterCrop(url: String, imageView: ImageView, placeholderImg: Int,
                                                cornerRadius: Float, diskCacheStrategy: DiskCacheStrategyEnum,
                                                transition: Boolean, thumbnail: Boolean, thumbnailWidth: Int, thumbnailHeight: Int) {

//        glideLoad(urlOrIdOrUri, imageView,
//            getDiskCacheStrategy(diskCacheStrategy)
//                .optionalTransform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
//                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
//                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius)),transition,
//            thumbnail,thumbnailWidth,thumbnailHeight
//        )
//
        glideLoad(url, imageView,
            getDiskCacheStrategy(diskCacheStrategy)
                .centerCrop()
                .placeholder(placeholderImg)
                .error(placeholderImg),
            transition,thumbnail, thumbnailWidth, thumbnailHeight

        )
//
//        loadImage(imageView.context,
//            GlideConfigImpl
//                .builder()
//                .url(url)
//                .transformation(RoundedCornersTransformation(cornerRadius.toInt(), 0))
//                .isCropCenter(true)
//                .isCrossFade(false)
//                .errorPic(placeholderImg)
//                .placeholder(placeholderImg)
//                .imageView(imageView)
//                .build())

    }

    override fun loadBorderCircleImage(urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float) {
        glideLoad(urlOrIdOrUri, imageView,
            RequestOptions().optionalTransform(CircleBorderTransformation(borderWidth, borderColor)),
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
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

        glideLoadImage(
            imageView.context,

            ImgLoadConfigImpl
                .builder()
                .load(url)
                .resize(imageWidth,imageHeight)
                .transformation(CircleWithBorderTransformation(borderWidth, borderColor))
                .isCrossFade(false)
                .error(placeholderImg)
                .placeholder(placeholderImg)
                .imageView(imageView)
                .build()
        )
    }

    override fun loadBorderCircleImageCenterCrop(
        url: String,
        imageView: ImageView,
        imageWidth : Float,
        imageHeight : Float,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int
    ) {


        glideLoadImage(
            imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .resize(imageWidth.toInt(),imageHeight.toInt())
                .transformation(CenterCrop(),
                    CircleWithBorderTransformation(borderWidth, borderColor)
                )
                .isCrossFade(false)
                .error(placeholderImg)
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .imageView(imageView)
                .build()
        )
    }

    override fun loadBorderCircleImageCenterCrop(
        url: String,
        imageView: ImageView,
        imageWidth: Float,
        imageHeight: Float,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int,
        crossFae: Boolean
    ) {

        glideLoadImage(
            imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .resize(imageWidth.toInt(),imageHeight.toInt())
                .transformation(CenterCrop(),
                    CircleWithBorderTransformation(borderWidth, borderColor)
                )
                .isCrossFade(crossFae)
                .error(placeholderImg)
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .imageView(imageView)
                .build()
        )
    }

    override fun loadBorderCircleImageCenterCrop(
        url: String,
        imageView: ImageView,
        imageWidth: Float,
        imageHeight: Float,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum
    ) {

        glideLoadImage(
            imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .resize(imageWidth.toInt(),imageHeight.toInt())
                .cacheStrategy(diskCacheStrategy)
                .transformation(CenterCrop(),
                    CircleWithBorderTransformation(borderWidth, borderColor)
                )
                .isCrossFade(false)
                .error(placeholderImg)
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .imageView(imageView)
                .build()
        )
    }

    override fun loadBorderCircleImageCenterCrop(
        url: String,
        imageView: ImageView,
        imageWidth: Float,
        imageHeight: Float,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int,
        diskCacheStrategy: DiskCacheStrategyEnum,
        crossFade: Boolean
    ) {
        glideLoadImage(
            imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .resize(imageWidth.toInt(),imageHeight.toInt())
                .cacheStrategy(diskCacheStrategy)

                .transformation(CenterCrop(),
                    CircleWithBorderTransformation(borderWidth, borderColor)
                )
                .isCrossFade(crossFade)
                .error(placeholderImg)
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .imageView(imageView)
                .build()
        )
    }

    override fun loadBorderCircleImage(
        url: String,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int
    ) {
        glideLoadImage(
            imageView.context,

            ImgLoadConfigImpl
                .builder()
                .load(url)
                .transformation(CircleWithBorderTransformation(borderWidth, borderColor))
                .error(placeholderImg)
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .imageView(imageView)
                .build()
        )
    }


    override fun loadBorderCircleImageCenterCrop(
        url: String,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int
    ) {
        glideLoadImage(
            imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .transformation(CenterCrop(),
                    CircleWithBorderTransformation(borderWidth, borderColor)
                )
                .isCrossFade(false)
                .error(placeholderImg)
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .imageView(imageView)
                .build()
        )
    }

    override fun loadBorderCircleImageCenterCrop(
        url: String,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int,
        crossFade: Boolean
    ) {
        glideLoadImage(
            imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .transformation(CenterCrop(),
                    CircleWithBorderTransformation(borderWidth, borderColor)
                )
                .isCrossFade(crossFade)
                .error(placeholderImg)
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .imageView(imageView)
                .build()
        )
    }

    override fun loadBorderCircleImageCenterCrop(
        url: String,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int,
        diskCacheStrategyEnum: DiskCacheStrategyEnum
    ) {
        glideLoadImage(
            imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .transformation(CenterCrop(),
                    CircleWithBorderTransformation(borderWidth, borderColor)
                )
                .cacheStrategy(diskCacheStrategyEnum)
                .isCrossFade(false)
                .error(placeholderImg)
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .imageView(imageView)
                .build()
        )
    }

    override fun loadBorderCircleImageCenterCrop(
        url: String,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int,
        diskCacheStrategyEnum: DiskCacheStrategyEnum,
        crossFade: Boolean
    ) {
        glideLoadImage(
            imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(url)
                .transformation(CenterCrop(),
                    CircleWithBorderTransformation(borderWidth, borderColor)
                )
                .cacheStrategy(diskCacheStrategyEnum)
                .isCrossFade(crossFade)
                .error(placeholderImg)
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .imageView(imageView)
                .build()
        )
    }

    override fun loadBorderCircleImageCenterCrop(
        imgId: Int,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int,
        diskCacheStrategyEnum: DiskCacheStrategyEnum
    ) {
        glideLoadImage(
            imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(imgId)
                .transformation(CenterCrop(),
                    CircleWithBorderTransformation(borderWidth, borderColor)
                )
                .cacheStrategy(diskCacheStrategyEnum)
                .isCrossFade(false)
                .error(placeholderImg)
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .imageView(imageView)
                .build())
    }

    override fun loadBorderCircleImageCenterCrop(
        uri: Uri,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int,
        diskCacheStrategyEnum: DiskCacheStrategyEnum
    ) {

        Glide
            .with(imageView.context)
            .load(uri)
            .transform(CenterCrop(),CircleBorderTransformation(borderWidth, borderColor))
            .apply(getDiskCacheStrategy(diskCacheStrategyEnum))
            .into(imageView)
    }

    override fun loadBorderCircleImageCenterCrop(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float
    ) {
        Glide
            .with(imageView.context)
            .load(urlOrIdOrUri)
            .transform(CenterCrop(), CircleWithBorderTransformation(borderWidth, borderColor))
            .into(imageView)
    }

    override fun loadBorderCircleImageCenterCrop(
        urlOrIdOrUri: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        crossFade: Boolean
    ) {
        Glide
            .with(imageView.context)
            .load(urlOrIdOrUri)
            .transition(DrawableTransitionOptions.withCrossFade())
            .transform(CenterCrop(), CircleWithBorderTransformation(borderWidth, borderColor))
            .into(imageView)

    }

    override fun loadBorderCircleImageCenterCrop(
        colorDrawable: ColorDrawable,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float
    ) {
        //todo
        glideLoadImage(
            imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(colorDrawable)
                .transformation(CenterCrop(),CircleWithBorderTransformation(borderWidth, borderColor))
                .imageView(imageView)
                .build()
        )

    }

    override fun loadBorderCircleImageCenterCrop(
        colorDrawable: ColorDrawable,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        crossFade: Boolean
    ) {

        glideLoadImage(
            imageView.context,
            ImgLoadConfigImpl
                .builder()
                .load(colorDrawable)
                .isCrossFade(crossFade)
                .transformation(CenterCrop(),CircleWithBorderTransformation(borderWidth, borderColor))
                .imageView(imageView)
                .build()
        )
    }

    override fun loadBorderCornerImage(urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius: Float) {
        glideLoad(urlOrIdOrUri, imageView,
            RequestOptions().transform(BorderRoundTransform(borderWidth, borderColor, cornerRadius)),
            transition = false,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }

    override fun loadBorderCornerImage(urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius: Float, placeholderImg: Int) {
        glideLoad(urlOrIdOrUri, imageView,
            RequestOptions()
                .transform(BorderRoundTransform(borderWidth, borderColor, cornerRadius))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius)),false
            ,
            thumbnail = false,
            thumbnailWidth = 0,
            thumbnailHeight = 0
        )
    }


    override fun loadGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes, nullOptions,null)
    }

    override fun loadGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView,
                         diskCacheStrategy: DiskCacheStrategyEnum, onAnimationStatus: OnAnimationStatus) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes, getDiskCacheStrategy(diskCacheStrategy),onAnimationStatus)
    }

    override fun loadGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes,
            RequestOptions()
                .placeholder(placeholderImg)
                .error(placeholderImg),null
        )
    }

    override fun loadGif(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoadGif(urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions()
                .placeholder(placeholderImg)
                .error(placeholderImg),null
        )

    }

    override fun loadGif(context: Context,urlOrIdOrUri: Any, imageView: ImageView) {
        glideLoadGif(context,urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER, nullOptions,null)
    }

    override fun loadGif(
        context: Context,
        urlOrIdOrUri: Any,
        imageView: ImageView,
        imgWidth: Int,
        imgHeight: Int
    ) {
        glideLoadGif(context,urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER, nullOptions,null,imgWidth,imgHeight)
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

    override fun loadCircleGif(urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoadGif(urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions()
                .circleCrop()
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
            ,null)
    }

    override fun loadCircleGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes, circleOptions,null)
    }

    override fun loadCircleGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes,
            RequestOptions()
                .circleCrop()
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_CIRCLE))
            ,null)
    }

    override fun loadCornerGif(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float) {
        glideLoadGif(urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
            ,null)
    }

    override fun loadCornerGif(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float, placeholderImg: Int) {
        glideLoadGif(urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions()
                .transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
            ,null)
    }

    override fun loadCornerGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes,
            RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
            ,null)
    }

    override fun loadCornerGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float, placeholderImg: Int) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes,
            RequestOptions()
                .transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerRadius) + 0.5f).toInt()))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
            ,null)
    }

    override fun loadBorderCornerGif(urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius: Float) {
        glideLoadGif(urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions().transform(BorderRoundTransform(borderWidth, borderColor, cornerRadius))
            ,null)
    }

    override fun loadBorderCornerGif(urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius: Float, placeholderImg: Int) {
        glideLoadGif(urlOrIdOrUri, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions()
                .transform(BorderRoundTransform(borderWidth, borderColor, cornerRadius))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
            ,null)
    }

    override fun loadBorderCornerGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius: Float) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes,
            RequestOptions().transform(BorderRoundTransform(borderWidth, borderColor, cornerRadius))
            ,null)
    }

    override fun loadBorderCornerGif(playTimes: Int, urlOrIdOrUri: Any, imageView: ImageView, borderColor: Int, borderWidth: Float, cornerRadius: Float, placeholderImg: Int) {
        glideLoadGif(urlOrIdOrUri, imageView, playTimes,
            RequestOptions()
                .transform(BorderRoundTransform(borderWidth, borderColor, cornerRadius))
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
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

    override fun loadImageWithRXCustomTarget(urlOrIdOrUri: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>, placeholderImg: Int) {
        glideRXCustomTarget(urlOrIdOrUri, context, rxCustomTarget, RequestOptions().placeholder(placeholderImg).error(placeholderImg))
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

    override fun load9Png(urlOrIdOrUri: Any, view: View, placeholderImg: Int) {
        load9PngCombine(urlOrIdOrUri, view, placeholderImg)
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
        Glide.with(imageView.context).asBitmap().load(bytes).apply(requestOptions).into(imageView)
    }


    private fun load9PngCombine(urlOrIdOrUri: Any,view: View,placeholderImg: Int) {
        ImageLoaderUtils.checkUrlOrId(urlOrIdOrUri)
        when (urlOrIdOrUri) {
            is String -> {
                glideLoad9Png(urlOrIdOrUri,view,RequestOptions().placeholder(placeholderImg).error(placeholderImg))
            }

            is Int -> {
                val bitmap = BitmapFactory.decodeResource(view.context.resources, urlOrIdOrUri) ?: return
                if (bitmap.ninePatchChunk == null) return
                val chunk: ByteArray = bitmap.ninePatchChunk
                if (NinePatch.isNinePatchChunk(chunk)) {
                    glideLoadDrawable(
                        NinePatchDrawable(view.context.resources,bitmap,chunk,
                            NinePatchChunk.deserialize(chunk).mPaddings,null),
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

    private fun glideRXCustomTarget(urlOrIdOrUri: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>, requestOptions: RequestOptions) {
        ImageLoaderUtils.checkUrlOrId(urlOrIdOrUri)
        Glide.with(context).asBitmap().load(if (urlOrIdOrUri is String) ImageLoaderUtils.replaceHttpToHttps(urlOrIdOrUri) else urlOrIdOrUri).apply(requestOptions).into(rxCustomTarget)
    }

    private fun glideRXCustomTarget(urlOrIdOrUri: Any,context: Context,thumbnailWidth: Int,thumbnailHeight: Int,rxCustomTarget: RXCustomTarget<Bitmap>) {
        ImageLoaderUtils.checkUrlOrId(urlOrIdOrUri)
        Glide.with(context).asBitmap()
            .load(if (urlOrIdOrUri is String) ImageLoaderUtils.replaceHttpToHttps(urlOrIdOrUri) else urlOrIdOrUri)
            .thumbnail(Glide.with(context).asBitmap()
                .load(urlOrIdOrUri)
                .override(thumbnailWidth, thumbnailHeight))
            .into(rxCustomTarget)
    }

    private fun glideLoad(urlOrIdOrUri: Any, imageView: ImageView, progressView: View,placeholderImg: Int,errorHolder : Int) {
        ImageLoaderUtils.checkUrlOrId(urlOrIdOrUri)

        Glide.with(imageView.context)
            .asBitmap()
            .load(urlOrIdOrUri)
            .apply(
                RequestOptions().fitCenter().error(errorHolder)
                    .placeholder(placeholderImg)
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


        if (thumbnail){
            glide
                .override(thumbnailWidth, thumbnailHeight)
        }
        if (transition){
            glide.transition(DrawableTransitionOptions.withCrossFade())
        }
        glide.into(imageView)
    }

    private fun glideLoad(context: Context,urlOrIdOrUri: Any, imageView: ImageView, requestOptions: RequestOptions,transition: Boolean,thumbnail:Boolean,
                          thumbnailWidth: Int,
                          thumbnailHeight: Int) {
        ImageLoaderUtils.checkUrlOrId(urlOrIdOrUri)


        val glide =
            Glide
                .with(context)
                .load(if (urlOrIdOrUri is String) ImageLoaderUtils.replaceHttpToHttps(urlOrIdOrUri) else urlOrIdOrUri)
                .apply(requestOptions)


        if (thumbnail){
            glide
                .override(thumbnailWidth, thumbnailHeight)
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
            glide
                .override(thumbnailWidth, thumbnailHeight)
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

    private fun glideLoadGif(context: Context,urlOrIdOrUri: Any, imageView: ImageView, playTimes: Int, requestOptions: RequestOptions,onAnimationStatus: OnAnimationStatus?) {
        ImageLoaderUtils.checkUrlOrId(urlOrIdOrUri)
        Glide.with(context).asGif()
            .load(if (urlOrIdOrUri is String) ImageLoaderUtils.replaceHttpToHttps(urlOrIdOrUri) else urlOrIdOrUri)
            .listener(ImageLoaderUtils.gifDrawableRequestListener(playTimes,onAnimationStatus))
            .apply(requestOptions)
            .into(imageView)
    }

    private fun glideLoadGif(context: Context,urlOrIdOrUri: Any, imageView: ImageView, playTimes: Int, requestOptions: RequestOptions,onAnimationStatus: OnAnimationStatus?,
                             imgWidth: Int,
                             imgHeight: Int) {
        ImageLoaderUtils.checkUrlOrId(urlOrIdOrUri)
        Glide.with(context).asGif()
            .load(if (urlOrIdOrUri is String) ImageLoaderUtils.replaceHttpToHttps(urlOrIdOrUri) else urlOrIdOrUri)
            .override(imgWidth,imgHeight)
            .listener(ImageLoaderUtils.gifDrawableRequestListener(playTimes,onAnimationStatus))
            .apply(requestOptions)
            .into(imageView)
    }
    private fun loadCornersImageCombine(urlOrIdOrUri: Any, imageView: ImageView, cornerRadius: Float, cornerTypeEnum: CornerTypeEnum, placeholderImg: Int) {

        val requestOptions = RequestOptions()

        if (placeholderImg != -1){
            requestOptions
                .placeholder(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
                .error(CircleRoundDrawable(imageView.context, placeholderImg).setType(CircleRoundDrawable.TYPE_ROUND).setRoundAngle(cornerRadius))
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

    private fun getPriority(priorityEnum: PriorityEnum) : Priority{

        when(priorityEnum){

            PriorityEnum.IMMEDIATE -> {
                return Priority.IMMEDIATE
            }
            PriorityEnum.HIGH -> {
                return Priority.HIGH
            }
            PriorityEnum.NORMAL -> {
                return Priority.NORMAL
            }
            PriorityEnum.LOW -> {
                return Priority.LOW
            }

        }
    }

    private fun getDecodeFormat(decodeFormatEnum: DecodeFormatEnum) : DecodeFormat{
        when(decodeFormatEnum) {
            DecodeFormatEnum.PREFER_ARGB_8888 -> {
                return DecodeFormat.PREFER_ARGB_8888
            }
        }
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


    fun glideLoadImageBitmap(context: Context, config: ImgLoadConfigImpl) {

        val glide = Glide.with(context).asBitmap()


        val glideRequest = if (config.drawableId != 0) {
            glide.load(config.drawableId)
        } else if (!TextUtils.isEmpty(config.url)){
            glide.load(config.url)
        }else if (config.uri != null){
            glide.load(config.uri)
        }else if (config.drawable != null){
            glide.load(config.drawable)
        }else if (config.colorDrawable != null){
            glide.load(config.colorDrawable)
        }else{
            glide.load(config.drawableId)
        }
        glideRequest.apply {
            if (config.diskCacheStrategyEnum != null){
                when (getCacheStrategyEnum(config.diskCacheStrategyEnum)) {
                    DiskCacheStrategyEnum.ALL -> diskCacheStrategy(DiskCacheStrategy.ALL)
                    DiskCacheStrategyEnum.NONE -> diskCacheStrategy(DiskCacheStrategy.NONE)
                    DiskCacheStrategyEnum.RESOURCE -> diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    DiskCacheStrategyEnum.DATA -> diskCacheStrategy(DiskCacheStrategy.DATA)
                    else -> diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

                }
            }

            //todo bitmap不行吗
//            if (config.isCrossFade) {
//                transition(DrawableTransitionOptions.withCrossFade())
//            }
            if (config.dontAnimate) {
                dontAnimate()
            }
            if (config.isImageRadius()) {
                transform(RoundedCorners(config.imageRadius))
            }
            //glide用它来改变图形的形状
            if (config.transformation != null) {
                Log.d("__tr-1","1")
                transform(*config.transformation)
            }
            if (config.placeHolderDrawable != null) {
                placeholder(config.placeHolderDrawable)
            }else if (config.placeholder != 0) {
                placeholder(config.placeholder)
            }

            if (config.errorDrawable != null) {
                error(config.errorDrawable)
            }else if (config.errorPic != 0) {
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
            if (config.isCenterInside) {
                centerInside()
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
            if (config.requestListenerBitmap != null) {
                addListener(config.requestListenerBitmap)
            }
            if (config.priorityEnum != null) {
                priority(getPriority(config.priorityEnum!!))
            }


            if (config.imageView != null){
                into(config.imageView!!)
            }else if (config.rxCustomTargetBitmap != null){

                into(config.rxCustomTargetBitmap!!)
            }
        }
    }
    fun glideLoadImageGif(context: Context, config: ImgLoadConfigImpl) {

        val glide = Glide.with(context).asGif()


        val glideRequest = if (config.drawableId != 0) {
            glide.load(config.drawableId)
        } else if (!TextUtils.isEmpty(config.url)){
            glide.load(config.url)
        }else if (config.uri != null){
            glide.load(config.uri)
        }else if (config.drawable != null){
            glide.load(config.drawable)
        }else if (config.colorDrawable != null){
            glide.load(config.colorDrawable)
        }else{
            glide.load(config.drawableId)
        }
        glideRequest.apply {
            if (config.diskCacheStrategyEnum != null){
                when (getCacheStrategyEnum(config.diskCacheStrategyEnum)) {
                    DiskCacheStrategyEnum.ALL -> diskCacheStrategy(DiskCacheStrategy.ALL)
                    DiskCacheStrategyEnum.NONE -> diskCacheStrategy(DiskCacheStrategy.NONE)
                    DiskCacheStrategyEnum.RESOURCE -> diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    DiskCacheStrategyEnum.DATA -> diskCacheStrategy(DiskCacheStrategy.DATA)
                    DiskCacheStrategyEnum.AUTOMATIC -> diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    else -> diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                }
            }

            if (config.isCrossFade) {
                transition(DrawableTransitionOptions.withCrossFade())
            }
            if (config.dontAnimate) {
                dontAnimate()
            }
            if (config.isImageRadius()) {
                transform(RoundedCorners(config.imageRadius))
            }
            //glide用它来改变图形的形状
            if (config.transformation != null) {
                Log.d("__tr-2","1")
                transform(*config.transformation)
            }
            if (config.placeHolderDrawable != null) {
                placeholder(config.placeHolderDrawable)
            }else if (config.placeholder != 0) {
                placeholder(config.placeholder)
            }

            if (config.errorDrawable != null) {
                error(config.errorDrawable)
            }else if (config.errorPic != 0) {
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
            if (config.isCenterInside) {
                centerInside()
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
            if (config.requestListenerGifDrawable != null) {
                addListener(config.requestListenerGifDrawable)
            }
            if (config.priorityEnum != null) {
                priority(getPriority(config.priorityEnum!!))
            }


            if (config.imageView != null){
                into(config.imageView!!)
            }else if (config.rxCustomTargetGifDrawable != null){

                into(config.rxCustomTargetGifDrawable!!)
            }
        }
    }

    fun glideLoadImage(context: Context, config: ImgLoadConfigImpl) {

        val glide = Glide.with(context)


        val glideRequest = if (config.drawableId != 0) {
            glide.load(config.drawableId)
        } else if (!TextUtils.isEmpty(config.url)){
            glide.load(config.url)
        }else if (config.uri != null){
            glide.load(config.uri)
        }else if (config.drawable != null){
            glide.load(config.drawable)
        }else if (config.colorDrawable != null){
            glide.load(config.colorDrawable)
        }else{
            glide.load(config.drawableId)
        }
        glideRequest.apply {
            if (config.diskCacheStrategyEnum != null){
                when (getCacheStrategyEnum(config.diskCacheStrategyEnum)) {
                    DiskCacheStrategyEnum.ALL -> diskCacheStrategy(DiskCacheStrategy.ALL)
                    DiskCacheStrategyEnum.NONE -> diskCacheStrategy(DiskCacheStrategy.NONE)
                    DiskCacheStrategyEnum.RESOURCE -> diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    DiskCacheStrategyEnum.DATA -> diskCacheStrategy(DiskCacheStrategy.DATA)
                    DiskCacheStrategyEnum.AUTOMATIC -> diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    else -> diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                }
            }

            if (config.isCrossFade) {
                transition(DrawableTransitionOptions.withCrossFade())
            }
            if (config.dontAnimate) {
                dontAnimate()
            }
            if (config.isImageRadius()) {
                transform(RoundedCorners(config.imageRadius))
            }
            //glide用它来改变图形的形状
            if (config.transformation != null) {
                Log.d("__tr-3","1")
                transform(*config.transformation)
            }
            if (config.placeHolderDrawable != null) {
                placeholder(config.placeHolderDrawable)
            }else if (config.placeholder != 0) {
                placeholder(config.placeholder)
            }

            if (config.errorDrawable != null) {
                error(config.errorDrawable)
            }else if (config.errorPic != 0) {
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
            if (config.isCenterInside) {
                centerInside()
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
            if (config.requestListenerDrawable != null) {
                addListener(config.requestListenerDrawable)
            }
            if (config.priorityEnum != null) {
                priority(getPriority(config.priorityEnum!!))
            }


            if (config.imageView != null){
                into(config.imageView!!)
            }else if (config.rxCustomTargetDrawable != null){

                into(config.rxCustomTargetDrawable!!)
            }
        }
    }


    fun setRequestBuilderTypeEnum(requestBuilderTypeEnum : RequestBuilderTypeEnum,glide : RequestManager){

        when(requestBuilderTypeEnum){
            RequestBuilderTypeEnum.GIF_DRAWABLE -> {
                glide.asGif()
            }
            RequestBuilderTypeEnum.BITMAP -> {
                glide.asBitmap()
            }
        }
    }
    private fun getCacheStrategy(diskCacheStrategy: DiskCacheStrategyEnum) : Int{
        return when(diskCacheStrategy){

            DiskCacheStrategyEnum.AUTOMATIC -> {
                4
            }

            DiskCacheStrategyEnum.RESOURCE -> {
                2
            }
            DiskCacheStrategyEnum.NONE -> {
                1
            }
            DiskCacheStrategyEnum.ALL -> {
                0
            }
            else ->{
                0
            }
        }
    }
    private fun getCacheStrategyEnum(diskCacheStrategy: DiskCacheStrategyEnum) : DiskCacheStrategyEnum{
        return when(diskCacheStrategy){

            DiskCacheStrategyEnum.RESOURCE -> {
                DiskCacheStrategyEnum.RESOURCE
            }
            DiskCacheStrategyEnum.NONE -> {
                DiskCacheStrategyEnum.NONE
            }
            DiskCacheStrategyEnum.ALL -> {
                DiskCacheStrategyEnum.ALL
            }
            else ->{
                DiskCacheStrategyEnum.AUTOMATIC
            }
        }
    }

}