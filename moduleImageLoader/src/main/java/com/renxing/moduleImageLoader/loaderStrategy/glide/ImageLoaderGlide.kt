package com.renxing.moduleImageLoader.loaderStrategy.glide

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.NinePatch
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.NinePatchDrawable
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
import com.renxing.moduleImageLoader.ImageLoaderInterface
import com.renxing.moduleImageLoader.imageUtils.ImageLoaderUtils
import com.renxing.moduleImageLoader.imageUtils.NinePatchChunk
import com.renxing.moduleImageLoader.loaderStrategy.glide.GlideRoundedCornersTransform.CornerType
import java.io.File
import java.io.FileInputStream

@SuppressLint("CheckResult")
object ImageLoaderGlide : ImageLoaderInterface {
    private var requestOptions = RequestOptions()

    override fun loadImage(url: String, imageView: ImageView) {
        Glide.with(imageView.context).load(url).into(imageView)
    }

    override fun loadImage(id: Int, imageView: ImageView) {
        Glide.with(imageView.context).load(id).into(imageView)
    }

    override fun loadImage(url: String, imageView: ImageView, defaultIv: Int) {
        requestOptions
            .placeholder(defaultIv)
            .error(defaultIv)
        Glide.with(imageView.context).load(url).apply(requestOptions).into(imageView)

    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int) {

        Glide.with(imageView.context)
            .load(ImageLoaderUtils.appendUrl(url, width, height, false))
            .apply(requestOptions)
            .into(imageView)
    }

    override fun loadImage(url: String, imageView: ImageView, defaultIv: Int, width: Int, height: Int) {
        requestOptions
            .placeholder(defaultIv)
            .error(defaultIv)

        Glide.with(imageView.context)
            .load(ImageLoaderUtils.appendUrl(url, width, height, false))
            .apply(requestOptions)
            .into(imageView)

    }

    override fun loadImageWithFitCenter(url: String, imageView: ImageView) {
        requestOptions.fitCenter()

        Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }

    override fun loadImageWithFitCenter(id: Int, imageView: ImageView) {
        requestOptions.fitCenter()

        Glide.with(imageView.context)
            .load(id)
            .apply(requestOptions)
            .into(imageView)
    }

    override fun loadImageWithCenterCrop(url: String, imageView: ImageView) {
        requestOptions.centerCrop()

        Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }

    override fun loadImageWithCenterCrop(id: Int, imageView: ImageView) {
        requestOptions.centerCrop()

        Glide.with(imageView.context)
            .load(id)
            .apply(requestOptions)
            .into(imageView)
    }

    override fun loadImageWithCenterInside(url: String, imageView: ImageView) {
        requestOptions.centerInside()

        Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }

    override fun loadImageWithCenterInside(id: Int, imageView: ImageView) {
        requestOptions.centerInside()

        Glide.with(imageView.context)
            .load(id)
            .apply(requestOptions)
            .into(imageView)
    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView) {
        requestOptions = RequestOptions()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)

        Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int) {
        requestOptions = RequestOptions()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)

        Glide.with(imageView.context)
            .load(ImageLoaderUtils.appendUrl(url,width,height,false))
            .apply(requestOptions)
            .into(imageView)
    }

    override fun loadCircleImage(url: String, imageView: ImageView) {
        val requestOptions = RequestOptions().circleCrop()
        ImageLoaderUtils.loadImageUrl(url, imageView, requestOptions)
    }

    override fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float) {
        val requestOptions =
            RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(radius) + 0.5f).toInt()))
        ImageLoaderUtils.loadImageUrl(url, imageView, requestOptions)
    }

    override fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float, cornerType: CornerType) {
        val requestOptions = RequestOptions().optionalTransform(GlideRoundedCornersTransform(ImageLoaderUtils.dp2px(radius) + 0.5f, cornerType))
        ImageLoaderUtils.loadImageUrl(url, imageView, requestOptions)
    }

    override fun loadRoundedCornersImage(bitmap: Bitmap, imageView: ImageView, radius: Float, cornerType: CornerType) {
        val requestOptions = RequestOptions().optionalTransform(GlideRoundedCornersTransform(ImageLoaderUtils.dp2px(radius) + 0.5f, cornerType))
        ImageLoaderUtils.loadImageBytes(ImageLoaderUtils.bitmap2Bytes(bitmap), imageView, requestOptions)
    }

    override fun loadGif(url: String, imageView: ImageView) {
        Glide.with(imageView.context)
            .asGif()
            .load(url)
            .listener(object : RequestListener<GifDrawable> {

                override fun onResourceReady(resource: GifDrawable, model: Any, target: Target<GifDrawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    resource.setLoopCount(1) //只播放一次
                    resource.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
                        override fun onAnimationStart(drawable: Drawable) {
                            super.onAnimationStart(drawable)
                            resource.start()
                        }

                        override fun onAnimationEnd(drawable: Drawable) {
                            super.onAnimationEnd(drawable)
                            //回收则图片展示被清除
//                            resource.recycle()
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

    override fun loadGif(id: Int, imageView: ImageView) {
        Glide.with(imageView.context)
            .asGif()
            .load(id)
            .into(imageView)
    }

    override fun loadGifWithLoop(url: String, imageView: ImageView) {
        Glide.with(imageView.context)
            .asGif()
            .load(url)
            .into(imageView)
    }

    override fun loadGifWithLoop(id: Int, imageView: ImageView) {
        Glide.with(imageView.context)
            .asGif()
            .load(id)
            .into(imageView)
    }

    override fun loadImageWithCustomTarget(context : Context,url: String, customTarget: CustomTarget<Bitmap>) {
        Glide.with(context)
            .asBitmap()
            .load(url)
            .into<CustomTarget<Bitmap>>(customTarget)
    }

    override fun load9Png(url: String, imageView: ImageView) {
        Glide.with(imageView.context)
            .asFile()
            .load(url)
            .into(object : CustomTarget<File?>() {
                override fun onResourceReady(resource: File, transition: Transition<in File?>?) {
                    try {
                        val inputStream = FileInputStream(resource)
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        val drawable: Drawable =
                            getNinePatchDrawable(
                                bitmap,
                                imageView.context
                            )
                        imageView.background = drawable
                        inputStream.close()
                    } catch (e: Exception) {
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {}

            })

    }

    private fun getNinePatchDrawable(bitmap: Bitmap, context: Context): Drawable {
        val chunk = bitmap.ninePatchChunk
        var ninePatchDrawable: NinePatchDrawable? = null
        ninePatchDrawable = if (NinePatch.isNinePatchChunk(chunk)) {
            NinePatchDrawable(context.resources, bitmap, chunk, Rect(), null)
        } else {
            return BitmapDrawable(context.resources, bitmap)
        }
        return ninePatchDrawable
    }

    override fun load9Png(context: Context,id: Int, imageView: ImageView) {
        val res = context.resources

        val bitmap = BitmapFactory.decodeResource(res, id) ?: return

        val chunk: ByteArray = bitmap.ninePatchChunk
        if (NinePatch.isNinePatchChunk(chunk)) {
            val patchy = NinePatchDrawable(
                context.resources,
                bitmap,
                chunk,
                NinePatchChunk.deserialize(chunk).mPaddings,
                null
            )
            Glide.with(context).load(patchy).into(imageView)
        }
    }


}