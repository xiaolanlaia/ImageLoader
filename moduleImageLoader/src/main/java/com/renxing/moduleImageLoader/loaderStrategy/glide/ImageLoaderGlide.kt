package com.renxing.moduleImageLoader.loaderStrategy.glide

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.NinePatch
import android.graphics.drawable.Drawable
import android.graphics.drawable.NinePatchDrawable
import android.view.View
import android.widget.ImageView
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.renxing.moduleImageLoader.imageUtils.ImageLoaderUtils
import com.renxing.moduleImageLoader.imageUtils.ModuleImageConstant
import com.renxing.moduleImageLoader.loaderStrategy.control.ImageLoaderInterface
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
import java.lang.IllegalArgumentException

/**
 *@author  :  WuJianFeng
 */
@SuppressLint("CheckResult")
internal class ImageLoaderGlide : ImageLoaderInterface {

    override fun loadImage(urlOrId: Any, imageView: ImageView) {

        glideLoad(urlOrId, imageView)
    }


    override fun loadImage(urlOrId: Any, imageView: ImageView, defaultIv: Int) {
        glideLoad(
            urlOrId, imageView,
            RequestOptions()
                .placeholder(CircleRoundDrawable(imageView.context, defaultIv))
                .error(CircleRoundDrawable(imageView.context, defaultIv))
        )

    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int) {
        glideLoad(ImageLoaderUtils.appendUrl(url, width, height, false), imageView)
    }

    override fun loadImage(url: String, imageView: ImageView, defaultIv: Int, width: Int, height: Int) {
        glideLoad(url, imageView,
            RequestOptions()
                .placeholder(defaultIv)
                .error(defaultIv))

    }

    override fun loadImageWithFitCenter(urlOrId: Any, imageView: ImageView) {
        glideLoad(urlOrId, imageView, RequestOptions().fitCenter())

    }

    override fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView) {
        glideLoad(urlOrId, imageView, RequestOptions().centerCrop())

    }

    override fun loadImageWithCenterInside(urlOrId: Any, imageView: ImageView) {
        glideLoad(urlOrId, imageView, RequestOptions().centerInside())

    }

    override fun loadImageWithSkipCache(urlOrId: Any, imageView: ImageView) {

        glideLoad(urlOrId, imageView,
            RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE))

    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int) {

        glideLoad(ImageLoaderUtils.appendUrl(url, width, height, false),
            imageView,
            RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
        )
    }

    override fun loadCircleImage(urlOrId: Any, imageView: ImageView) {
        glideLoad(urlOrId, imageView, RequestOptions().circleCrop())
    }

    override fun loadRoundedCornersImage(urlOrId: Any, imageView: ImageView, radius: Float) {
        glideLoad(
            urlOrId,
            imageView,
            RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(radius) + 0.5f).toInt()))
        )
    }

    override fun loadRoundedCornersImage(
        urlOrId: Any,
        imageView: ImageView,
        radius: Float,
        cornerType: ModuleImageConstant.CornerType
    ) {

        glideLoad(
            urlOrId, imageView,
            RequestOptions().optionalTransform(
                RoundedCornersTransform(
                    ImageLoaderUtils.dp2px(radius) + 0.5f,
                    cornerType
                )
            )
        )
    }

    override fun loadRoundedCornersImage(
        bitmap: Bitmap,
        imageView: ImageView,
        radius: Float,
        cornerType: ModuleImageConstant.CornerType
    ) {
        glideLoadBytes(
            ImageLoaderUtils.bitmap2Bytes(bitmap), imageView,
            RequestOptions().optionalTransform(
                RoundedCornersTransform(
                    ImageLoaderUtils.dp2px(radius) + 0.5f,
                    cornerType
                )
            )
        )
    }

    override fun loadBorderCircleImage(
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float
    ) {
        glideLoad(
            urlOrId, imageView,
            RequestOptions().optionalTransform(CircleBorderTransformation(borderWidth, borderColor))
        )
    }

    override fun loadBorderCornerImage(
        urlOrId: Any,
        imageView: ImageView,
        borderWidth: Float,
        borderColor: Int,
        cornerWidth: Int
    ) {
        glideLoad(
            urlOrId, imageView,
            RequestOptions().transform(BorderRoundTransform(borderWidth,borderColor,cornerWidth))
        )
    }

    override fun loadGif(urlOrId: Any, imageView: ImageView, playTimes: Int) {
        glideLoadGif(urlOrId, imageView, playTimes)
    }

    override fun loadGif(urlOrId: Any, imageView: ImageView) {
        glideLoadGif(urlOrId, imageView, GifDrawable.LOOP_FOREVER)
    }

    override fun loadCircleGif(urlOrId: Any, imageView: ImageView) {
        glideLoadCircleGif(urlOrId, imageView, GifDrawable.LOOP_FOREVER)

    }

    override fun loadCircleGif(urlOrId: Any, imageView: ImageView, playTimes: Int) {
        glideLoadCircleGif(urlOrId, imageView, playTimes)
    }

    override fun loadRoundedCornerGif(urlOrId: Any, imageView: ImageView, radius: Float) {
        glideLoadRoundedCornerGif(urlOrId, imageView, radius, GifDrawable.LOOP_FOREVER)
    }


    override fun loadRoundedCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        radius: Float,
        playTimes: Int
    ) {
        glideLoadRoundedCornerGif(urlOrId, imageView, radius, playTimes)
    }


    override fun loadBorderCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        borderWidth: Float,
        borderColor: Int,
        cornerWidth: Int
    ) {
        glideLoadBorderCornerGif(urlOrId, imageView, borderWidth, borderColor, cornerWidth, GifDrawable.LOOP_FOREVER)
    }

    override fun loadBorderCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        borderWidth: Float,
        borderColor: Int,
        cornerWidth: Int,
        playTimes: Int
    ) {
        glideLoadBorderCornerGif(urlOrId, imageView, borderWidth, borderColor, cornerWidth, playTimes)
    }

    override fun loadImageWithRXCustomTarget(
        urlOrId: Any,
        context: Context,
        rxCustomTarget: RXCustomTarget<Bitmap>
    ) {
        glideRXCustomTarget(urlOrId, context, rxCustomTarget)

    }

    override fun load9Png(urlOrId: Any, view: View) {
        checkUrlOrId(urlOrId)
        if (urlOrId is String){
            glideLoad9Png(urlOrId,view)

        }else if (urlOrId is Int){
            val res = view.context.resources

            val bitmap = BitmapFactory.decodeResource(res, urlOrId) ?: return
            if (bitmap.ninePatchChunk == null) return

            val chunk: ByteArray = bitmap.ninePatchChunk
            if (NinePatch.isNinePatchChunk(chunk)) {
                val patchy = NinePatchDrawable(
                    view.context.resources,
                    bitmap,
                    chunk,
                    NinePatchChunk.deserialize(chunk).mPaddings,
                    null
                )
                glideLoadDrawable(patchy, view)
            }
        }
    }


    /**
     * Glide加载图片的最终方法
     * 所有的方法url都要将http换成https
     */

    private fun glideLoadBytes(
        bytes: ByteArray,
        imageView: ImageView,
        requestOptions: RequestOptions
    ) {
        Glide
            .with(imageView.context)
            .asBitmap()
            .load(bytes)
            .apply(requestOptions).into(imageView)
    }

    private fun glideLoad9Png(url : String, view : View){
        Glide.with(view.context)
            .asFile()
            .load(ImageLoaderUtils.replaceHttpToHttps(url))
            .into(object : CustomTarget<File>() {
                override fun onResourceReady(resource: File, transition: Transition<in File>?) {

                    try {
                        val fileInputStream = FileInputStream(resource)
                        ImageLoaderUtils.setNinePathImage(
                            view.context,
                            view,
                            BitmapFactory.decodeStream(fileInputStream)
                        )
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


    private fun glideLoadDrawable(drawable: Drawable, view: View) {

        Glide.with(view.context)
            .load(drawable)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    view.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {}

            })
    }

    private fun glideRXCustomTarget(
        urlOrId: Any,
        context: Context,
        rxCustomTarget: RXCustomTarget<Bitmap>
    ) {
        Glide.with(context)
            .asBitmap()
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .into(rxCustomTarget)
    }

    private fun glideLoad(urlOrId: Any, imageView: ImageView) {
        checkUrlOrId(urlOrId)
        Glide.with(imageView.context)
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .into(imageView)
    }



    private fun glideLoad(urlOrId: Any, imageView: ImageView, requestOptions: RequestOptions) {
        checkUrlOrId(urlOrId)
        Glide.with(imageView.context)
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .apply(requestOptions)
            .into(imageView)
    }


    private fun glideLoadGif(urlOrId: Any, imageView: ImageView, playTimes: Int) {
        checkUrlOrId(urlOrId)
        Glide.with(imageView.context)
            .asGif()
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .listener(object : RequestListener<GifDrawable> {

                override fun onResourceReady(
                    resource: GifDrawable,
                    model: Any,
                    target: Target<GifDrawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    resource.setLoopCount(playTimes) //只播放一次
                    resource.registerAnimationCallback(object :
                        Animatable2Compat.AnimationCallback() {
                        override fun onAnimationStart(drawable: Drawable) {
                            super.onAnimationStart(drawable)
                            resource.start()
                        }

                        override fun onAnimationEnd(drawable: Drawable) {
                            super.onAnimationEnd(drawable)
                        }
                    })
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            }).into(imageView)
    }

    private fun glideLoadCircleGif(urlOrId: Any, imageView: ImageView, playTimes: Int) {
        checkUrlOrId(urlOrId)
        Glide.with(imageView.context)
            .asGif()
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .listener(object : RequestListener<GifDrawable> {

                override fun onResourceReady(
                    resource: GifDrawable,
                    model: Any,
                    target: Target<GifDrawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    resource.setLoopCount(playTimes) //只播放一次
                    resource.registerAnimationCallback(object :
                        Animatable2Compat.AnimationCallback() {
                        override fun onAnimationStart(drawable: Drawable) {
                            super.onAnimationStart(drawable)
                            resource.start()
                        }

                        override fun onAnimationEnd(drawable: Drawable) {
                            super.onAnimationEnd(drawable)
                        }
                    })
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            }).into(imageView)
    }

    private fun glideLoadRoundedCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        radius: Float,
        playTimes: Int
    ) {

        checkUrlOrId(urlOrId)
        Glide.with(imageView.context)
            .asGif()
            .apply(RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(radius) + 0.5f).toInt())))
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .listener(object : RequestListener<GifDrawable> {

                override fun onResourceReady(
                    resource: GifDrawable,
                    model: Any,
                    target: Target<GifDrawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    resource.setLoopCount(playTimes) //只播放一次
                    resource.registerAnimationCallback(object :
                        Animatable2Compat.AnimationCallback() {
                        override fun onAnimationStart(drawable: Drawable) {
                            super.onAnimationStart(drawable)
                            resource.start()
                        }

                        override fun onAnimationEnd(drawable: Drawable) {
                            super.onAnimationEnd(drawable)
                        }
                    })
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            }).into(imageView)
    }


    private fun glideLoadBorderCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        borderWidth: Float,
        borderColor: Int,
        cornerWidth: Int,
        playTimes: Int
    ) {
        checkUrlOrId(urlOrId)
        Glide.with(imageView.context)
            .asGif()
            .apply(RequestOptions().transform(BorderRoundTransform(borderWidth,borderColor,cornerWidth)))
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .listener(object : RequestListener<GifDrawable> {

                override fun onResourceReady(
                    resource: GifDrawable,
                    model: Any,
                    target: Target<GifDrawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    resource.setLoopCount(playTimes) //只播放一次
                    resource.registerAnimationCallback(object :
                        Animatable2Compat.AnimationCallback() {
                        override fun onAnimationStart(drawable: Drawable) {
                            super.onAnimationStart(drawable)
                            resource.start()
                        }

                        override fun onAnimationEnd(drawable: Drawable) {
                            super.onAnimationEnd(drawable)
                        }
                    })
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            }).into(imageView)
    }

    private fun checkUrlOrId(urlOrId: Any) {
        if (urlOrId !is String && urlOrId !is Int) {
            throw IllegalArgumentException("urlOrId is not correct argument")
        }
    }

}