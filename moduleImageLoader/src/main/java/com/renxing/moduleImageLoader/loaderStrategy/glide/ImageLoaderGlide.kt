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
        glideLoadUrl(url, imageView)
    }

    override fun loadImage(id: Int, imageView: ImageView) {
        glideLoadId(id, imageView)
    }

    override fun loadImage(url: String, imageView: ImageView, defaultIv: Int) {
        glideLoadUrl(
            url, imageView,
            RequestOptions()
                .placeholder(CircleRoundDrawable(imageView.context, defaultIv))
                .error(CircleRoundDrawable(imageView.context, defaultIv))
        )

    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int) {
        glideLoadUrl(ImageLoaderUtils.appendUrl(url, width, height, false), imageView)
    }

    override fun loadImage(
        url: String,
        imageView: ImageView,
        defaultIv: Int,
        width: Int,
        height: Int
    ) {
        glideLoadUrl(
            url, imageView,
            RequestOptions()
                .placeholder(defaultIv)
                .error(defaultIv)
        )

    }

    override fun loadImageWithFitCenter(url: String, imageView: ImageView) {
        glideLoadUrl(url, imageView, RequestOptions().fitCenter())

    }

    override fun loadImageWithFitCenter(id: Int, imageView: ImageView) {
        glideLoadId(id, imageView, RequestOptions().fitCenter())

    }

    override fun loadImageWithCenterCrop(url: String, imageView: ImageView) {
        glideLoadUrl(url, imageView, RequestOptions().centerCrop())

    }

    override fun loadImageWithCenterCrop(id: Int, imageView: ImageView) {
        glideLoadId(id, imageView, RequestOptions().centerCrop())

    }

    override fun loadImageWithCenterInside(url: String, imageView: ImageView) {
        glideLoadUrl(url, imageView, RequestOptions().centerInside())

    }

    override fun loadImageWithCenterInside(id: Int, imageView: ImageView) {
        glideLoadId(id, imageView, RequestOptions().centerInside())

    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView) {


        glideLoadUrl(
            url, imageView,
            RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
        )

    }

    override fun loadImageWithSkipCache(
        url: String,
        imageView: ImageView,
        width: Int,
        height: Int
    ) {


        glideLoadUrl(
            ImageLoaderUtils.appendUrl(url, width, height, false),
            imageView,
            RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
        )
    }

    override fun loadCircleImage(url: String, imageView: ImageView) {
        glideLoadUrl(url, imageView, RequestOptions().circleCrop())
    }

    override fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float) {
        glideLoadUrl(
            url,
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

        glideLoadUrl(
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

    override fun loadCircleImageWithBorder(
        url: String,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float
    ) {
        glideLoadUrl(
            url, imageView,
            RequestOptions().optionalTransform(CircleBorderTransformation(borderWidth, borderColor))
        )
    }

    override fun loadCircleImageWithBorder(
        id: Int,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float
    ) {
        glideLoadId(
            id, imageView,
            RequestOptions().optionalTransform(CircleBorderTransformation(borderWidth, borderColor))
        )
    }

    override fun loadGif(url: String, imageView: ImageView, playTimes: Int) {
        glideLoadGifUrl(url, imageView, playTimes)
    }

    override fun loadGif(url: String, imageView: ImageView) {
        glideLoadGifUrl(url, imageView, GifDrawable.LOOP_FOREVER)
    }

    override fun loadGif(id: Int, imageView: ImageView, playTimes: Int) {
        glideLoadGifId(id, imageView, playTimes)
    }

    override fun loadGif(id: Int, imageView: ImageView) {
        glideLoadGifId(id, imageView, GifDrawable.LOOP_FOREVER)
    }

    override fun loadImageWithRxCustomTarget(
        url: String,
        context: Context,
        rxCustomTarget: RXCustomTarget<Bitmap>
    ) {
        glideRxCustomTarget(url, context, rxCustomTarget)

    }

    override fun load9Png(url: String, view: View) {

        Glide.with(view.context)
            .asFile()
            .load(url)
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

    override fun load9Png(id: Int, view: View) {
        val res = view.context.resources

        val bitmap = BitmapFactory.decodeResource(res, id) ?: return

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
     */

    private fun glideLoadBytes(bytes: ByteArray, imageView: ImageView, requestOptions: RequestOptions) {
        Glide
            .with(imageView.context)
            .asBitmap()
            .load(bytes)
            .apply(requestOptions).into(imageView)
    }

    private fun glideLoadDrawable(drawable: Drawable, view: View) {
//        Glide.with(imageView.context)
//            .load(drawable)
//            .into(imageView)

        Glide.with(view.context)
            .load(drawable)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {

//                    try {
//                        val fileInputStream = FileInputStream(resource)
//                        ImageLoaderUtils.setNinePathImage(
//                            view.context,
//                            view,
//                            BitmapFactory.decodeStream(fileInputStream)
//                        )
//                        fileInputStream.close()
//                    } catch (e: FileNotFoundException) {
//                        e.printStackTrace()
//                    } catch (e: IOException) {
//                        e.printStackTrace()
//                    }

                    view.setBackgroundDrawable(resource)


                }

                override fun onLoadCleared(placeholder: Drawable?) {}

            })
    }

    private fun glideRxCustomTarget(url: String, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>) {
        Glide.with(context)
            .asBitmap()
            .load(ImageLoaderUtils.replaceHttpToHttps(url))
            .into(rxCustomTarget)
    }

    private fun glideLoadUrl(url: String, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(ImageLoaderUtils.replaceHttpToHttps(url))
            .into(imageView)
    }

    private fun glideLoadId(id: Int, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(id)
            .into(imageView)
    }

    private fun glideLoadUrl(url: String, imageView: ImageView, requestOptions: RequestOptions) {
        Glide.with(imageView.context)
            .load(ImageLoaderUtils.replaceHttpToHttps(url))
            .apply(requestOptions)
            .into(imageView)
    }

    private fun glideLoadId(id: Int, imageView: ImageView, requestOptions: RequestOptions) {
        Glide.with(imageView.context)
            .load(id)
            .apply(requestOptions)
            .into(imageView)
    }

    private fun glideLoadGifUrl(url: String, imageView: ImageView, playTimes: Int) {
        Glide.with(imageView.context)
            .asGif()
            .load(ImageLoaderUtils.replaceHttpToHttps(url))
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

    private fun glideLoadGifId(id: Int, imageView: ImageView, playTimes: Int) {
        Glide.with(imageView.context)
            .asGif()
            .load(id)
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