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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
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

    override fun loadImage(urlOrId: Any, imageView: ImageView) {

        glideLoad(urlOrId, imageView)
    }


    override fun loadImage(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(
            urlOrId, imageView,
            RequestOptions()
                .placeholder(placeholderImg)
                .error(placeholderImg)
        )

    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int) {
        glideLoad(ImageLoaderUtils.appendUrl(url, width, height, false), imageView)
    }

    override fun loadImage(
        url: String,
        imageView: ImageView,
        width: Int,
        height: Int,
        placeholderImg: Int
    ) {
        glideLoad(
            ImageLoaderUtils.appendUrl(url, width, height, false), imageView,
            RequestOptions()
                .placeholder(placeholderImg)
                .error(placeholderImg)
        )

    }

    override fun loadImageWithFitCenter(urlOrId: Any, imageView: ImageView) {
        glideLoad(urlOrId, imageView, RequestOptions().fitCenter())

    }

    override fun loadImageWithFitCenter(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(urlOrId, imageView,
            RequestOptions().fitCenter()
            .placeholder(placeholderImg)
            .error(placeholderImg))
    }

    override fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView) {
        glideLoad(urlOrId, imageView, RequestOptions().centerCrop())

    }

    override fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(urlOrId, imageView,
            RequestOptions()
                .centerCrop()
                .placeholder(placeholderImg)
                .error(placeholderImg))
    }

    override fun loadImageWithCenterInside(urlOrId: Any, imageView: ImageView) {
        glideLoad(urlOrId, imageView, RequestOptions().centerInside())

    }

    override fun loadImageWithCenterInside(
        urlOrId: Any,
        imageView: ImageView,
        placeholderImg: Int
    ) {
        glideLoad(urlOrId, imageView,
            RequestOptions()
                .centerInside()
                .placeholder(placeholderImg)
                .error(placeholderImg))
    }

    override fun loadImageWithSkipCache(urlOrId: Any, imageView: ImageView) {

        glideLoad(
            urlOrId, imageView,
            RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
        )

    }

    override fun loadImageWithSkipCache(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(
            urlOrId, imageView,
            RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(placeholderImg)
                .error(placeholderImg)
        )
    }

    override fun loadImageWithSkipCache(
        url: String,
        imageView: ImageView,
        width: Int,
        height: Int
    ) {

        glideLoad(
            ImageLoaderUtils.appendUrl(url, width, height, false),
            imageView,
            RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
        )
    }

    override fun loadImageWithSkipCache(
        url: String,
        imageView: ImageView,
        width: Int,
        height: Int,
        placeholderImg: Int
    ) {

        glideLoad(
            ImageLoaderUtils.appendUrl(url, width, height, false),
            imageView,
            RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(placeholderImg)
                .error(placeholderImg)
        )
    }

    override fun loadCircleImage(urlOrId: Any, imageView: ImageView) {
        glideLoad(urlOrId, imageView, RequestOptions().circleCrop())
    }

    override fun loadCircleImage(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoad(urlOrId, imageView,
            RequestOptions()
                .circleCrop()
                .placeholder(CircleRoundDrawable(imageView.context,placeholderImg))
                .error(CircleRoundDrawable(imageView.context,placeholderImg)))
    }

    override fun loadRoundedCornersImage(urlOrId: Any, imageView: ImageView, cornerWidth: Float) {
        glideLoad(
            urlOrId,
            imageView,
            RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f).toInt()))
        )
    }

    override fun loadRoundedCornersImage(
        urlOrId: Any,
        imageView: ImageView,
        cornerWidth: Float,
        placeholderImg: Int
    ) {
        glideLoad(
            urlOrId,
            imageView,
            RequestOptions()
                .transform(RoundedCorners((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f).toInt()))
                .placeholder(CircleRoundDrawable(imageView.context,placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f)))
                .error(CircleRoundDrawable(imageView.context,placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f))))

    }

    override fun loadRoundedCornersImage(
        urlOrId: Any,
        imageView: ImageView,
        cornerWidth: Float,
        cornerType: ModuleImageConstant.CornerType
    ) {

        glideLoad(
            urlOrId, imageView,
            RequestOptions().optionalTransform(
                RoundedCornersTransform(
                    ImageLoaderUtils.dp2px(cornerWidth) + 0.5f,
                    cornerType
                )
            )
        )
    }

    override fun loadRoundedCornersImage(
        urlOrId: Any,
        imageView: ImageView,
        cornerWidth: Float,
        cornerType: ModuleImageConstant.CornerType,
        placeholderImg: Int
    ) {

        glideLoad(
            urlOrId, imageView,
            RequestOptions()
                .optionalTransform(RoundedCornersTransform(ImageLoaderUtils.dp2px(cornerWidth) + 0.5f, cornerType))
                .placeholder(CircleRoundDrawable(imageView.context,placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f)))
                .error(CircleRoundDrawable(imageView.context,placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f)))
        )
    }

    override fun loadRoundedCornersImage(
        bitmap: Bitmap,
        imageView: ImageView,
        cornerWidth: Float,
        cornerType: ModuleImageConstant.CornerType
    ) {
        glideLoadBytes(
            ImageLoaderUtils.bitmap2Bytes(bitmap), imageView,
            RequestOptions().optionalTransform(
                RoundedCornersTransform(
                    ImageLoaderUtils.dp2px(cornerWidth) + 0.5f,
                    cornerType
                )
            )
        )
    }

    override fun loadRoundedCornersImage(
        bitmap: Bitmap,
        imageView: ImageView,
        cornerWidth: Float,
        cornerType: ModuleImageConstant.CornerType,
        placeholderImg: Int
    ) {
        glideLoadBytes(
            ImageLoaderUtils.bitmap2Bytes(bitmap), imageView,
            RequestOptions()
                .optionalTransform(RoundedCornersTransform(ImageLoaderUtils.dp2px(cornerWidth) + 0.5f, cornerType))
                .placeholder(CircleRoundDrawable(imageView.context,placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f)))
                .error(CircleRoundDrawable(imageView.context,placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f)))
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

    override fun loadBorderCircleImage(
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int
    ) {
        glideLoad(urlOrId, imageView,
            RequestOptions()
                .optionalTransform(CircleBorderTransformation(borderWidth, borderColor))
                .placeholder(CircleRoundDrawable(imageView.context,placeholderImg))
                .error(CircleRoundDrawable(imageView.context,placeholderImg))
        )
    }

    override fun loadBorderCornerImage(
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerWidth: Float
    ) {
        glideLoad(
            urlOrId, imageView,
            RequestOptions().transform(BorderRoundTransform(borderWidth, borderColor, cornerWidth))
        )
    }

    override fun loadBorderCornerImage(
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerWidth: Float,
        placeholderImg: Int
    ) {
        glideLoad(urlOrId, imageView,
            RequestOptions()
                .transform(BorderRoundTransform(borderWidth, borderColor, cornerWidth))
                .placeholder(CircleRoundDrawable(imageView.context,placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f)))
                .error(CircleRoundDrawable(imageView.context,placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f)))
        )
    }

    override fun loadGif(playTimes: Int, urlOrId: Any, imageView: ImageView) {
        glideLoadGif(urlOrId, imageView, playTimes)
    }

    override fun loadGif(playTimes: Int, urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoadGif(urlOrId, imageView, playTimes,
            RequestOptions()
                .placeholder(placeholderImg)
                .error(placeholderImg))
    }

    override fun loadGif(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoadGif(urlOrId, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions()
                .placeholder(placeholderImg)
                .error(placeholderImg))

    }

    override fun loadGif(urlOrId: Any, imageView: ImageView) {
        glideLoadGif(urlOrId, imageView, GifDrawable.LOOP_FOREVER)
    }

    override fun loadCircleGif(urlOrId: Any, imageView: ImageView) {
        glideLoadCircleGif(urlOrId, imageView, GifDrawable.LOOP_FOREVER)

    }

    override fun loadCircleGif(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        glideLoadCircleGif(urlOrId, imageView, GifDrawable.LOOP_FOREVER,
            RequestOptions()
                .placeholder(CircleRoundDrawable(imageView.context,placeholderImg))
                .error(CircleRoundDrawable(imageView.context,placeholderImg)))
    }

    override fun loadCircleGif(playTimes: Int, urlOrId: Any, imageView: ImageView) {
        glideLoadCircleGif(urlOrId, imageView, playTimes)
    }

    override fun loadCircleGif(
        playTimes: Int,
        urlOrId: Any,
        imageView: ImageView,
        placeholderImg: Int
    ) {
        glideLoadCircleGif(urlOrId, imageView, playTimes,
            RequestOptions()
                .placeholder(CircleRoundDrawable(imageView.context,placeholderImg))
                .error(CircleRoundDrawable(imageView.context,placeholderImg)))
    }

    override fun loadRoundedCornerGif(urlOrId: Any, imageView: ImageView, cornerWidth: Float) {
        glideLoadRoundedCornerGif(urlOrId, imageView, cornerWidth, GifDrawable.LOOP_FOREVER)
    }

    override fun loadRoundedCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        cornerWidth: Float,
        placeholderImg: Int
    ) {
        glideLoadRoundedCornerGif(urlOrId, imageView, cornerWidth, GifDrawable.LOOP_FOREVER,
            RequestOptions()
                .placeholder(CircleRoundDrawable(imageView.context,placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f)))
                .error(CircleRoundDrawable(imageView.context,placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f))))
    }

    override fun loadRoundedCornerGif(
        playTimes: Int,
        urlOrId: Any,
        imageView: ImageView,
        cornerWidth: Float
    ) {
        glideLoadRoundedCornerGif(urlOrId, imageView, cornerWidth, playTimes)
    }

    override fun loadRoundedCornerGif(
        playTimes: Int,
        urlOrId: Any,
        imageView: ImageView,
        cornerWidth: Float,
        placeholderImg: Int
    ) {
        glideLoadRoundedCornerGif(urlOrId, imageView, cornerWidth, playTimes,
            RequestOptions()
                .placeholder(CircleRoundDrawable(imageView.context,placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f)))
                .error(CircleRoundDrawable(imageView.context,placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f))))
    }


    override fun loadBorderCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerWidth: Float
    ) {
        glideLoadBorderCornerGif(
            urlOrId,
            imageView,
            borderWidth,
            borderColor,
            cornerWidth,
            GifDrawable.LOOP_FOREVER
        )
    }

    override fun loadBorderCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerWidth: Float,
        placeholderImg: Int
    ) {
        glideLoadBorderCornerGif(
            urlOrId,
            imageView,
            borderWidth,
            borderColor,
            cornerWidth,
            GifDrawable.LOOP_FOREVER,
            RequestOptions()
                .placeholder(CircleRoundDrawable(imageView.context,placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f)))
                .error(CircleRoundDrawable(imageView.context,placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f)))
        )
    }

    override fun loadBorderCornerGif(
        playTimes: Int,
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerWidth: Float
    ) {
        glideLoadBorderCornerGif(
            urlOrId,
            imageView,
            borderWidth,
            borderColor,
            cornerWidth,
            playTimes
        )
    }

    override fun loadBorderCornerGif(
        playTimes: Int,
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerWidth: Float,
        placeholderImg: Int
    ) {
        glideLoadBorderCornerGif(
            urlOrId,
            imageView,
            borderWidth,
            borderColor,
            cornerWidth,
            playTimes,
            RequestOptions()
                .placeholder(CircleRoundDrawable(imageView.context,placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f)))
                .error(CircleRoundDrawable(imageView.context,placeholderImg).setType(CircleRoundDrawable.TYPE_Round).setRoundAngle((ImageLoaderUtils.dp2px(cornerWidth) + 0.5f)))
        )
    }


    override fun loadImageWithRXCustomTarget(
        urlOrId: Any,
        context: Context,
        rxCustomTarget: RXCustomTarget<Bitmap>
    ) {
        glideRXCustomTarget(urlOrId, context, rxCustomTarget)

    }

    override fun loadImageWithRXCustomTarget(
        urlOrId: Any,
        context: Context,
        rxCustomTarget: RXCustomTarget<Bitmap>,
        placeholderImg: Int
    ) {
        glideRXCustomTarget(urlOrId, context, rxCustomTarget,
            RequestOptions()
                .placeholder(placeholderImg)
                .error(placeholderImg))
    }

    override fun load9Png(urlOrId: Any, view: View) {
        ImageLoaderUtils.checkUrlOrId(urlOrId)

        when (urlOrId) {
            is String -> {
                glideLoad9Png(urlOrId, view)
            }

            is Int -> {
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
    }

    override fun load9Png(urlOrId: Any, view: View, placeholderImg: Int) {
        ImageLoaderUtils.checkUrlOrId(urlOrId)

        when (urlOrId) {
            is String -> {
                glideLoad9Png(urlOrId, view,
                    RequestOptions()
                        .placeholder(placeholderImg)
                        .error(placeholderImg))
            }

            is Int -> {
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
                    glideLoadDrawable(patchy, view,
                        RequestOptions()
                            .placeholder(placeholderImg)
                            .error(placeholderImg))
                }
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
            .apply(requestOptions)
            .into(imageView)
    }

    private fun glideLoad9Png(url: String, view: View) {
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

    private fun glideLoad9Png(url: String, view: View, requestOptions: RequestOptions) {
        Glide.with(view.context)
            .asFile()
            .load(ImageLoaderUtils.replaceHttpToHttps(url))
            .apply(requestOptions)
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

    private fun glideLoadDrawable(drawable: Drawable, view: View, requestOptions: RequestOptions) {

        Glide.with(view.context)
            .load(drawable)
            .apply(requestOptions)
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

    private fun glideRXCustomTarget(
        urlOrId: Any,
        context: Context,
        rxCustomTarget: RXCustomTarget<Bitmap>,
        requestOptions: RequestOptions
    ) {
        Glide.with(context)
            .asBitmap()
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .apply(requestOptions)
            .into(rxCustomTarget)
    }

    private fun glideLoad(urlOrId: Any, imageView: ImageView) {
        ImageLoaderUtils.checkUrlOrId(urlOrId)
        Glide.with(imageView.context)
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .into(imageView)
    }


    private fun glideLoad(urlOrId: Any, imageView: ImageView, requestOptions: RequestOptions) {
        ImageLoaderUtils.checkUrlOrId(urlOrId)
        Glide.with(imageView.context)
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .apply(requestOptions)
            .into(imageView)
    }


    private fun glideLoadGif(urlOrId: Any, imageView: ImageView, playTimes: Int) {
        ImageLoaderUtils.checkUrlOrId(urlOrId)
        Glide.with(imageView.context)
            .asGif()
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .listener(ImageLoaderUtils.gifDrawableRequestListener(playTimes))
            .into(imageView)
    }

    private fun glideLoadGif(urlOrId: Any, imageView: ImageView, playTimes: Int, requestOptions: RequestOptions) {
        ImageLoaderUtils.checkUrlOrId(urlOrId)
        Glide.with(imageView.context)
            .asGif()
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .listener(ImageLoaderUtils.gifDrawableRequestListener(playTimes))
            .apply(requestOptions)
            .into(imageView)
    }

    private fun glideLoadCircleGif(urlOrId: Any, imageView: ImageView, playTimes: Int) {
        ImageLoaderUtils.checkUrlOrId(urlOrId)
        Glide.with(imageView.context)
            .asGif()
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .listener(ImageLoaderUtils.gifDrawableRequestListener(playTimes))
            .into(imageView)
    }

    private fun glideLoadCircleGif(urlOrId: Any, imageView: ImageView, playTimes: Int, requestOptions: RequestOptions) {
        ImageLoaderUtils.checkUrlOrId(urlOrId)
        Glide.with(imageView.context)
            .asGif()
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .listener(ImageLoaderUtils.gifDrawableRequestListener(playTimes))
            .apply(requestOptions)
            .into(imageView)
    }

    private fun glideLoadRoundedCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        radius: Float,
        playTimes: Int
    ) {

        ImageLoaderUtils.checkUrlOrId(urlOrId)
        Glide.with(imageView.context)
            .asGif()
            .apply(RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(radius) + 0.5f).toInt())))
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .listener(ImageLoaderUtils.gifDrawableRequestListener(playTimes))
            .into(imageView)
    }

    private fun glideLoadRoundedCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        radius: Float,
        playTimes: Int,
        requestOptions: RequestOptions
    ) {

        ImageLoaderUtils.checkUrlOrId(urlOrId)
        Glide.with(imageView.context)
            .asGif()
            .apply(RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(radius) + 0.5f).toInt())))
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .listener(ImageLoaderUtils.gifDrawableRequestListener(playTimes))
            .apply(requestOptions)
            .into(imageView)
    }


    private fun glideLoadBorderCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        borderWidth: Float,
        borderColor: Int,
        cornerWidth: Float,
        playTimes: Int
    ) {
        ImageLoaderUtils.checkUrlOrId(urlOrId)
        Glide.with(imageView.context)
            .asGif()
            .apply(
                RequestOptions().transform(
                    BorderRoundTransform(
                        borderWidth,
                        borderColor,
                        cornerWidth
                    )
                )
            )
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .listener(ImageLoaderUtils.gifDrawableRequestListener(playTimes))
            .into(imageView)
    }

    private fun glideLoadBorderCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        borderWidth: Float,
        borderColor: Int,
        cornerWidth: Float,
        playTimes: Int,
        requestOptions: RequestOptions
    ) {
        ImageLoaderUtils.checkUrlOrId(urlOrId)
        Glide.with(imageView.context)
            .asGif()
            .apply(
                RequestOptions().transform(
                    BorderRoundTransform(
                        borderWidth,
                        borderColor,
                        cornerWidth
                    )
                )
            )
            .load(if (urlOrId is String) ImageLoaderUtils.replaceHttpToHttps(urlOrId) else urlOrId)
            .listener(ImageLoaderUtils.gifDrawableRequestListener(playTimes))
            .apply(requestOptions)
            .into(imageView)
    }


}