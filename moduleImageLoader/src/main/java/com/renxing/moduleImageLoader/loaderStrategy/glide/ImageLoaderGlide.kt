package com.renxing.moduleImageLoader.loaderStrategy.glide

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.NinePatch
import android.graphics.drawable.Drawable
import android.graphics.drawable.NinePatchDrawable
import android.os.Looper
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.*
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.FutureTarget
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
import com.renxing.moduleImageLoader.loaderStrategy.glide.config.ImgLoadConfigImpl
import com.renxing.moduleImageLoader.loaderStrategy.glide.placeholder.CircleRoundDrawable
import com.renxing.moduleImageLoader.loaderStrategy.glide.transformation.*
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 *@author  :  WuJianFeng
 */
@SuppressLint("CheckResult")
class ImageLoaderGlide : ImageLoaderInterface {


    override fun loadFutureTarget(
        context: Context,
        url: String,
        decodeFormateEnum: DecodeFormateEnum,
        diskCacheStrategyEnum: DiskCacheStrategyEnum,
        function1: (Bitmap?) -> Unit
    ) {
        val bitmapFutureTarget: FutureTarget<Bitmap> = Glide.with(context)
            .asBitmap()
            .format(DecodeFormat.PREFER_ARGB_8888)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .submit()
        function1.invoke(bitmapFutureTarget[2000L, TimeUnit.MILLISECONDS])
        Glide.with(context).clear(bitmapFutureTarget)

    }


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
        if (params.registerAnimationCallback != null){
            imgLoadConfigImpl.registerAnimationCallback(params.registerAnimationCallback)
        }
        if (params.rxListener != null){
            imgLoadConfigImpl.rxListener(params.rxListener)
        }

        if (params.setLoopCount != 0){
            imgLoadConfigImpl.setLoopCount(params.setLoopCount)

        }

        if (params.intoBitmapTarget != null){
            imgLoadConfigImpl.intoCustomTarget(params.intoBitmapTarget)
        }
        if (params.intoDrawableTarget != null){
            imgLoadConfigImpl.intoDrawableTarget(params.intoDrawableTarget)
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
        val bitmapTransformation  = ArrayList<BitmapTransformation>()
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
            if (config.rxListener != null) {
                addListener(object : RequestListener<Bitmap>{

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        config.rxListener?.onLoadFailed()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        config.rxListener?.onResourceReady()
                        return false
                    }

                })
            }
            if (config.priorityEnum != null) {
                priority(getPriority(config.priorityEnum!!))
            }


            if (config.imageView != null){
                into(config.imageView!!)
            }else if (config.intoBitmapTarget != null){
                into(object : CustomTarget<Bitmap>(){
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        config.intoBitmapTarget?.onResourceReady(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        config.intoBitmapTarget?.onLoadCleared()
                    }

                })
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
            if (config.rxListener != null || config.registerAnimationCallback != null || config.setLoopCount != 0) {
                addListener(object : RequestListener<GifDrawable>{

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<GifDrawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (config.rxListener != null){
                            config.rxListener!!.onLoadFailed()
                        }
                        return false
                    }

                    override fun onResourceReady(
                        resource: GifDrawable?,
                        model: Any?,
                        target: Target<GifDrawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (config.setLoopCount != 0){
                            resource?.setLoopCount(config.setLoopCount)

                        }
                        if (config.rxListener != null){
                            config.rxListener!!.onResourceReady()
                        }
                        if (config.registerAnimationCallback != null){
                            resource?.registerAnimationCallback(object :
                                Animatable2Compat.AnimationCallback() {
                                override fun onAnimationStart(drawable: Drawable) {
                                    super.onAnimationStart(drawable)
                                    config.registerAnimationCallback?.onAnimationStart()
                                }

                                override fun onAnimationEnd(drawable: Drawable) {
                                    super.onAnimationEnd(drawable)
                                    config.registerAnimationCallback?.onAnimationEnd()

                                }
                            })
                        }

                        return false
                    }

                })
            }
            if (config.priorityEnum != null) {
                priority(getPriority(config.priorityEnum!!))
            }


            if (config.imageView != null){
                into(config.imageView!!)
            }else if (config.intoDrawableTarget != null){
                into(object : CustomTarget<GifDrawable>(){

                    override fun onLoadCleared(placeholder: Drawable?) {
                        config.intoDrawableTarget?.onLoadCleared()
                    }

                    override fun onResourceReady(
                        resource: GifDrawable,
                        transition: Transition<in GifDrawable>?
                    ) {
                        config.intoDrawableTarget?.onResourceReady(resource)
                    }

                })
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
            if (config.rxListener != null || config.registerAnimationCallback != null || config.setLoopCount != 0) {
                addListener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (config.rxListener != null){
                            config.rxListener!!.onLoadFailed()
                        }

                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (config.setLoopCount != 0){
                            (resource as GifDrawable).setLoopCount(config.setLoopCount)
                        }
                        if (config.rxListener != null){
                            config.rxListener!!.onResourceReady()
                        }


                        if (config.registerAnimationCallback != null){
                            (resource as GifDrawable).registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
                                override fun onAnimationEnd(drawable: Drawable?) {
                                    super.onAnimationEnd(drawable)
                                    config.registerAnimationCallback?.onAnimationEnd()
                                }

                                override fun onAnimationStart(drawable: Drawable?) {
                                    super.onAnimationStart(drawable)
                                    config.registerAnimationCallback?.onAnimationStart()

                                }
                            })
                        }
                        return false
                    }

                })
            }
            if (config.priorityEnum != null) {
                priority(getPriority(config.priorityEnum!!))
            }


            if (config.imageView != null){
                into(config.imageView!!)
            }else if (config.intoDrawableTarget != null){
                into(object : CustomTarget<Drawable>(){
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        config.intoDrawableTarget?.onResourceReady(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        config.intoDrawableTarget?.onLoadCleared()
                    }

                })
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