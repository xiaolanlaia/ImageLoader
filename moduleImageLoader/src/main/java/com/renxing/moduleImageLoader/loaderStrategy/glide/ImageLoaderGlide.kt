package com.renxing.moduleImageLoader.loaderStrategy.glide

import android.annotation.SuppressLint
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
import com.renxing.moduleImageLoader.imageUtils.DisplayUtils
import com.renxing.moduleImageLoader.imageUtils.ImageLoaderUtils
import com.renxing.moduleImageLoader.imageUtils.ModuleImageConstant
import com.renxing.moduleImageLoader.imageUtils.NinePatchChunk
import com.renxing.moduleImageLoader.loaderStrategy.control.ImageLoaderInterface

@SuppressLint("CheckResult")
class ImageLoaderGlide : ImageLoaderInterface {
    override fun loadImage(url: String, imageView: ImageView) {
        glideLoadUrl(ImageLoaderUtils.replaceHttpToHttps(url),imageView)
    }

    override fun loadImage(id: Int, imageView: ImageView) {
        glideLoadId(id,imageView)
    }

    override fun loadImage(url: String, imageView: ImageView, defaultIv: Int) {
        glideLoadUrl(ImageLoaderUtils.replaceHttpToHttps(url), imageView,
            RequestOptions()
                .placeholder(defaultIv)
                .error(defaultIv))

    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int) {
        glideLoadUrl(ImageLoaderUtils.appendUrl(ImageLoaderUtils.replaceHttpToHttps(url), width, height, false),imageView)
    }

    override fun loadImage(url: String, imageView: ImageView, defaultIv: Int, width: Int, height: Int) {
        glideLoadUrl(ImageLoaderUtils.replaceHttpToHttps(url), imageView,
            RequestOptions()
                .placeholder(defaultIv)
                .error(defaultIv))

    }

    override fun loadImageWithFitCenter(url: String, imageView: ImageView) {
        glideLoadUrl(ImageLoaderUtils.replaceHttpToHttps(url), imageView, RequestOptions().fitCenter())

    }

    override fun loadImageWithFitCenter(id: Int, imageView: ImageView) {
        glideLoadId(id, imageView, RequestOptions().fitCenter())

    }

    override fun loadImageWithCenterCrop(url: String, imageView: ImageView) {
        glideLoadUrl(ImageLoaderUtils.replaceHttpToHttps(url), imageView, RequestOptions().centerCrop())

    }

    override fun loadImageWithCenterCrop(id: Int, imageView: ImageView) {
        glideLoadId(id, imageView, RequestOptions().centerCrop())

    }

    override fun loadImageWithCenterInside(url: String, imageView: ImageView) {
        glideLoadUrl(ImageLoaderUtils.replaceHttpToHttps(url), imageView, RequestOptions().centerInside())

    }

    override fun loadImageWithCenterInside(id: Int, imageView: ImageView) {
        glideLoadId(id, imageView, RequestOptions().centerInside())

    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView) {


        glideLoadUrl(ImageLoaderUtils.replaceHttpToHttps(url), imageView,
            RequestOptions()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE))

    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int) {


        glideLoadUrl(ImageLoaderUtils.appendUrl(ImageLoaderUtils.replaceHttpToHttps(url),width,height,false),
            imageView,
            RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE))
    }

    override fun loadCircleImage(url: String, imageView: ImageView) {
        glideLoadUrl(ImageLoaderUtils.replaceHttpToHttps(url), imageView, RequestOptions().circleCrop())
    }

    override fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float) {
        glideLoadUrl(ImageLoaderUtils.replaceHttpToHttps(url), imageView, RequestOptions().transform(RoundedCorners((ImageLoaderUtils.dp2px(radius) + 0.5f).toInt())))
    }

    override fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float, cornerType: ModuleImageConstant.CornerType) {

        glideLoadUrl(ImageLoaderUtils.replaceHttpToHttps(url), imageView,
            RequestOptions().optionalTransform(RoundedCornersTransform(ImageLoaderUtils.dp2px(radius) + 0.5f, cornerType)))
    }

    override fun loadRoundedCornersImage(bitmap: Bitmap, imageView: ImageView, radius: Float, cornerType: ModuleImageConstant.CornerType) {
        glideLoadBytes(ImageLoaderUtils.bitmap2Bytes(bitmap), imageView,
            RequestOptions().optionalTransform(RoundedCornersTransform(ImageLoaderUtils.dp2px(radius) + 0.5f, cornerType)))
    }

    override fun loadCircleImageWithBorder(
        url: String,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float
    ) {
        glideLoadUrl(ImageLoaderUtils.replaceHttpToHttps(url), imageView,
            RequestOptions().optionalTransform(CircleBorderTransformation(borderWidth,borderColor)))
    }

    override fun loadCircleImageWithBorder(
        id: Int,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float
    ) {
        glideLoadId(id, imageView,
            RequestOptions().optionalTransform(CircleBorderTransformation(borderWidth,borderColor)))
    }

    override fun loadGif(url: String, imageView: ImageView, vararg playTimes : Int) {
        Glide.with(imageView.context)
            .asGif()
            .load(ImageLoaderUtils.replaceHttpToHttps(url))
            .listener(object : RequestListener<GifDrawable> {

                override fun onResourceReady(resource: GifDrawable, model: Any, target: Target<GifDrawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    resource.setLoopCount(if(playTimes.isNotEmpty()) playTimes[0] else GifDrawable.LOOP_FOREVER) //只播放一次
                    resource.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
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

    override fun loadGif(id: Int, imageView: ImageView, vararg playTimes : Int) {
        Glide.with(imageView.context)
            .asGif()
            .load(id)
            .listener(object : RequestListener<GifDrawable> {

                override fun onResourceReady(resource: GifDrawable, model: Any, target: Target<GifDrawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    resource.setLoopCount(if(playTimes.isNotEmpty()) playTimes[0] else GifDrawable.LOOP_FOREVER) //只播放一次
                    resource.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
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

    override fun loadImageCustomTargetForBitmap(url: String, imageView: ImageView, width : Int, height : Int) {

        Glide.with(imageView.context)
            .asBitmap()
            .load(url)
            .into<CustomTarget<Bitmap>>(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ){

                    val params = imageView.layoutParams
                    params.height = DisplayUtils.dp2px(height.toFloat())
                    params.width = DisplayUtils.dp2px(width.toFloat())
                    imageView.layoutParams = params
                    imageView.setImageBitmap(resource)
                    imageView.visibility = View.VISIBLE
                }

                override fun onLoadCleared(placeholder: Drawable?) {}

            })
    }

    //@hide
//    override fun load9Png(url: String, imageView: ImageView) {
//
//        throw Exception("url加载点9图待验证")
//        //todo url加载点9图待验证
//
//    }

    override fun load9Png(id: Int, imageView: ImageView) {
        val res = imageView.context.resources

        val bitmap = BitmapFactory.decodeResource(res, id) ?: return

        val chunk: ByteArray = bitmap.ninePatchChunk
        if (NinePatch.isNinePatchChunk(chunk)) {
            val patchy : NinePatchDrawable = NinePatchDrawable(
                imageView.context.resources,
                bitmap,
                chunk,
                NinePatchChunk.deserialize(chunk).mPaddings,
                null
            )
            glideLoadDrawable(patchy,imageView)
        }
    }



    private fun glideLoadBytes(bytes: ByteArray, imageView: ImageView, requestOptions: RequestOptions) {
        Glide
            .with(imageView.context)
            .asBitmap()
            .load(bytes)
            .apply(requestOptions).into(imageView)
    }

    private fun glideLoadDrawable(drawable: Drawable, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(drawable)
            .into(imageView)
    }

    private fun glideLoadUrl(url: String, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }

    private fun glideLoadId(id: Int, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(id)
            .into(imageView)
    }
    private fun glideLoadUrl(url: String, imageView: ImageView, requestOptions: RequestOptions) {
        Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }
    private fun glideLoadId(id: Int, imageView: ImageView, requestOptions: RequestOptions) {
        Glide.with(imageView.context)
            .load(id)
            .apply(requestOptions)
            .into(imageView)
    }

}