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

/**
 *@author  :  WuJianFeng
 */
@SuppressLint("CheckResult")
internal class ImageLoaderGlide : ImageLoaderInterface {

    override fun loadImage(url: String, imageView: ImageView) {
        glideLoad(url, imageView)
    }

    override fun loadImage(id: Int, imageView: ImageView) {
        glideLoad(id, imageView)
    }

    override fun loadImage(url: String, imageView: ImageView, defaultIv: Int) {
        glideLoad(
            url, imageView,
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

    override fun loadImageWithFitCenter(url: String, imageView: ImageView) {
        glideLoad(url, imageView, RequestOptions().fitCenter())

    }

    override fun loadImageWithFitCenter(id: Int, imageView: ImageView) {
        glideLoad(id, imageView, RequestOptions().fitCenter())

    }

    override fun loadImageWithCenterCrop(url: String, imageView: ImageView) {
        glideLoad(url, imageView, RequestOptions().centerCrop())

    }

    override fun loadImageWithCenterCrop(id: Int, imageView: ImageView) {
        glideLoad(id, imageView, RequestOptions().centerCrop())

    }

    override fun loadImageWithCenterInside(url: String, imageView: ImageView) {
        glideLoad(url, imageView, RequestOptions().centerInside())

    }

    override fun loadImageWithCenterInside(id: Int, imageView: ImageView) {
        glideLoad(id, imageView, RequestOptions().centerInside())

    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView) {

        glideLoad(url, imageView,
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

    override fun loadCircleImage(url: String, imageView: ImageView) {
        glideLoad(url, imageView, RequestOptions().circleCrop())
    }

    override fun loadCircleImage(id: Int, imageView: ImageView) {
        glideLoad(id, imageView, RequestOptions().circleCrop())
    }

    override fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float) {
        glideLoad(
            url,
            imageView,
            RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(radius) + 0.5f).toInt()))
        )
    }

    override fun loadRoundedCornersImage(id: Int, imageView: ImageView, radius: Float) {
        glideLoad(
            id,
            imageView,
            RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(radius) + 0.5f).toInt()))
        )
    }

    override fun loadRoundedCornersImage(
        url: String,
        imageView: ImageView,
        radius: Float,
        cornerType: ModuleImageConstant.CornerType
    ) {

        glideLoad(
            url, imageView,
            RequestOptions().optionalTransform(
                RoundedCornersTransform(
                    ImageLoaderUtils.dp2px(radius) + 0.5f,
                    cornerType
                )
            )
        )
    }

    override fun loadRoundedCornersImage(
        id: Int,
        imageView: ImageView,
        radius: Float,
        cornerType: ModuleImageConstant.CornerType
    ) {
        glideLoad(
            id, imageView,
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
        url: String,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float
    ) {
        glideLoad(
            url, imageView,
            RequestOptions().optionalTransform(CircleBorderTransformation(borderWidth, borderColor))
        )
    }

    override fun loadBorderCircleImage(
        id: Int,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float
    ) {
        glideLoad(
            id, imageView,
            RequestOptions().optionalTransform(CircleBorderTransformation(borderWidth, borderColor))
        )
    }

    override fun loadBorderCornerImage(
        id: Int,
        imageView: ImageView,
        borderWidth: Float,
        borderColor: Int,
        cornerWidth: Int
    ) {
        glideLoad(
            id, imageView,
            RequestOptions().transform(BorderRoundTransform(borderWidth,borderColor,cornerWidth))
        )
    }

    override fun loadBorderCornerImage(url: String, imageView: ImageView, borderWidth: Float, borderColor: Int, cornerWidth : Int) {

        glideLoad(
            url, imageView,
            RequestOptions().transform(BorderRoundTransform(borderWidth,borderColor,cornerWidth))
        )

    }

    override fun loadGif(url: String, imageView: ImageView, playTimes: Int) {
        glideLoadGif(url, imageView, playTimes)
    }

    override fun loadGif(url: String, imageView: ImageView) {
        glideLoadGif(url, imageView, GifDrawable.LOOP_FOREVER)
    }

    override fun loadGif(id: Int, imageView: ImageView, playTimes: Int) {
        glideLoadGif(id, imageView, playTimes)
    }

    override fun loadGif(id: Int, imageView: ImageView) {
        glideLoadGif(id, imageView, GifDrawable.LOOP_FOREVER)
    }

    override fun loadCircleGif(id: Int, imageView: ImageView) {
        glideLoadCircleGif(id, imageView, GifDrawable.LOOP_FOREVER)

    }

    override fun loadCircleGif(id: Int, imageView: ImageView, playTimes: Int) {
        glideLoadCircleGif(id, imageView, playTimes)
    }

    override fun loadCircleGif(url: String, imageView: ImageView) {
        glideLoadCircleGif(url, imageView, GifDrawable.LOOP_FOREVER)
    }

    override fun loadCircleGif(url: String, imageView: ImageView, playTimes: Int) {
        glideLoadCircleGif(url, imageView, playTimes)
    }

    override fun loadRoundedCornerGif(url: String, imageView: ImageView, radius: Float) {
        glideLoadRoundedCornerGif(url, imageView, radius, GifDrawable.LOOP_FOREVER)
    }


    override fun loadRoundedCornerGif(
        url: String,
        imageView: ImageView,
        radius: Float,
        playTimes: Int
    ) {
        glideLoadRoundedCornerGif(url, imageView, radius, playTimes)
    }

    override fun loadRoundedCornerGif(id: Int, imageView: ImageView, radius: Float) {
        glideLoadRoundedCornerGif(id, imageView, radius, GifDrawable.LOOP_FOREVER)
    }

    override fun loadRoundedCornerGif(
        id: Int,
        imageView: ImageView,
        radius: Float,
        playTimes: Int
    ) {
        glideLoadRoundedCornerGif(id, imageView, radius, playTimes)
    }

    override fun loadBorderCornerGif(
        id: Int,
        imageView: ImageView,
        borderWidth: Float,
        borderColor: Int,
        cornerWidth: Int
    ) {
        glideLoadBorderCornerGif(id, imageView, borderWidth, borderColor, cornerWidth, GifDrawable.LOOP_FOREVER)
    }

    override fun loadBorderCornerGif(
        id: Int,
        imageView: ImageView,
        borderWidth: Float,
        borderColor: Int,
        cornerWidth: Int,
        playTimes: Int
    ) {
        glideLoadBorderCornerGif(id, imageView, borderWidth, borderColor, cornerWidth, playTimes)
    }

    override fun loadBorderCornerGif(
        url: String,
        imageView: ImageView,
        borderWidth: Float,
        borderColor: Int,
        cornerWidth: Int
    ) {
        glideLoadBorderCornerGif(url, imageView, borderWidth, borderColor, cornerWidth, GifDrawable.LOOP_FOREVER)
    }

    override fun loadBorderCornerGif(
        url: String,
        imageView: ImageView,
        borderWidth: Float,
        borderColor: Int,
        cornerWidth: Int,
        playTimes: Int
    ) {
        glideLoadBorderCornerGif(url, imageView, borderWidth, borderColor, cornerWidth, playTimes)
    }

    override fun loadImageWithRXCustomTarget(
        url: String,
        context: Context,
        rxCustomTarget: RXCustomTarget<Bitmap>
    ) {
        glideRXCustomTarget(url, context, rxCustomTarget)

    }

    override fun loadImageWithRXCustomTarget(
        id: Int,
        context: Context,
        rxCustomTarget: RXCustomTarget<Bitmap>
    ) {
        glideRXCustomTarget(id, context, rxCustomTarget)
    }

    override fun load9Png(url: String, view: View) {
        glideLoad9Png(url,view)
    }

    override fun load9Png(id: Int, view: View) {
        val res = view.context.resources

        val bitmap = BitmapFactory.decodeResource(res, id) ?: return
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
        Glide.with(imageView.context)
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .into(imageView)
    }

    private fun glideLoad(urlOrId: Any, imageView: ImageView, requestOptions: RequestOptions) {
        Glide.with(imageView.context)
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .apply(requestOptions)
            .into(imageView)
    }


    private fun glideLoadGif(urlOrId: Any, imageView: ImageView, playTimes: Int) {
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


}