package com.renxing.moduleImage.loaderStrategy.glide

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.renxing.moduleImage.loaderStrategy.glide.GlideRoundedCornersTransform.CornerType
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.TypedValue
import android.widget.ImageView
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.renxing.moduleImage.imageUtils.DisplayUtils
import com.renxing.moduleImage.imageUtils.ImageToUtil
import com.renxing.moduleImage.imageUtils.ModuleImageConstant
import com.renxing.moduleImage.loaderStrategy.control.ImageLoaderInterface
import java.io.ByteArrayOutputStream

@SuppressLint("CheckResult")
object ImageLoaderGlide : ImageLoaderInterface {
    private var requestOptions = RequestOptions()
    private fun checkUrl(url: String): Boolean {
        if (TextUtils.isEmpty(url)) {
            return true
        }
        return url.endsWith("null")
    }
    private fun activityFinishedOrDestroyed(mContext: Context): Boolean {
        if (mContext is Activity) {
            if (mContext.isFinishing || mContext.isDestroyed) {
                return true
            }
        }

        return false
    }
    private fun appendUrl(url: String): String {
        var newUrl = ""
        url.run{
            if (startsWith(ModuleImageConstant.HTTP) && (endsWith(ModuleImageConstant.PNG_LOWERCASE) || endsWith(
                    ModuleImageConstant.PNG_UPPERCASE
                )
                        || endsWith(ModuleImageConstant.JPEG_UPPERCASE) || endsWith(
                    ModuleImageConstant.JPEG_LOWERCASE
                )
                        || endsWith(ModuleImageConstant.JPG_UPPERCASE) || endsWith(
                    ModuleImageConstant.JPG_LOWERCASE
                )) && !contains(ModuleImageConstant.QUERY_MARK)) {
                //将http替换为https
                if (!startsWith(ModuleImageConstant.HTTPS)){
                    replace(ModuleImageConstant.HTTP, ModuleImageConstant.HTTPS)
                }
                newUrl = this + ModuleImageConstant.URL_APPEND_STR
            }else{
                return url
            }
        }
        return newUrl
    }

    private fun replaceHttpToHttps(url: String) : String{
        var newUrl = ""
        url.run{
            //将http替换为https
            if (!startsWith(ModuleImageConstant.HTTPS)){
                newUrl = replace(ModuleImageConstant.HTTP, ModuleImageConstant.HTTPS)
            }else{
                return url
            }
        }
        return newUrl
    }

    private fun appendUrl(url: String, width: Int, height: Int, needToPx: Boolean): String {
        var newUrl = ""
        url.run{
            if (!this.contains(ModuleImageConstant.URL_APPEND_WIDTH)) {
                newUrl = if (needToPx) {
                    this + ModuleImageConstant.URL_APPEND_WIDTH + DisplayUtils.dp2px(width.toFloat()) + ModuleImageConstant.URL_APPEND_HEIGHT + DisplayUtils.dp2px(height.toFloat()) + ModuleImageConstant.interlaceStr
                } else {
                    this + ModuleImageConstant.URL_APPEND_WIDTH + width + ModuleImageConstant.URL_APPEND_HEIGHT + height + ModuleImageConstant.interlaceStr
                }
            }
        }
        return newUrl
    }
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
            .load(ImageToUtil.appendUrl(url, width, height, false))
            .apply(requestOptions)
            .into(imageView)
    }

    override fun loadImage(url: String, imageView: ImageView, defaultIv: Int, width: Int, height: Int) {
        requestOptions
            .placeholder(defaultIv)
            .error(defaultIv)

        Glide.with(imageView.context)
            .load(ImageToUtil.appendUrl(url, width, height, false))
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
            .load(ImageToUtil.appendUrl(url,width,height,false))
            .apply(requestOptions)
            .into(imageView)
    }

    override fun loadCircleImage(url: String, imageView: ImageView) {
        val requestOptions = RequestOptions().circleCrop()
        loadImageUrl(url, imageView, requestOptions)
    }

    override fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float) {
        val requestOptions =
            RequestOptions().transform(RoundedCorners((dp2px(radius) + 0.5f).toInt()))
        loadImageUrl(url, imageView, requestOptions)
    }

    override fun loadRoundedCornersImage(url: String, imageView: ImageView, radius: Float, cornerType: CornerType) {
        val requestOptions = RequestOptions().optionalTransform(GlideRoundedCornersTransform(dp2px(radius) + 0.5f, cornerType))
        loadImageUrl(url, imageView, requestOptions)
    }

    override fun loadRoundedCornersImage(bitmap: Bitmap, imageView: ImageView, radius: Float, cornerType: CornerType) {
        val requestOptions = RequestOptions().optionalTransform(GlideRoundedCornersTransform(dp2px(radius) + 0.5f, cornerType))
        loadImageBytes(bitmap2Bytes(bitmap), imageView, requestOptions)
    }

    override fun loadGif(url: String, imageView: ImageView) {
        Glide.with(imageView.context)
            .asGif()
            .load(url)
            .skipMemoryCache(true)
            .listener(object : RequestListener<GifDrawable> {

                override fun onResourceReady(resource: GifDrawable, model: Any, target: Target<GifDrawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    resource.setLoopCount(1) //只播放一次
                    resource.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
                        override fun onAnimationStart(drawable: Drawable) {
                            super.onAnimationStart(drawable)
                            resource.start()
//                            mOnAnimationStatus.onAnimationStart()
                        }

                        override fun onAnimationEnd(drawable: Drawable) {
                            super.onAnimationEnd(drawable)
                            resource.recycle()
//                            mOnAnimationStatus.onAnimationEnd()
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
        TODO("Not yet implemented")
    }

    override fun loadGifWithLoop(url: String, imageView: ImageView) {
        TODO("Not yet implemented")
    }

    override fun loadGifWithLoop(id: Int, imageView: ImageView) {
        TODO("Not yet implemented")
    }

    override fun loadImageWithCustomTarget(url: String, customTarget: CustomTarget<Bitmap>) {
        TODO("Not yet implemented")
    }

    override fun load9Png(url: String, imageView: ImageView, resId: Int) {
        TODO("Not yet implemented")
    }

    /**
     * 把Bitmap转Byte
     */
    private fun bitmap2Bytes(bitmap: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        return baos.toByteArray()
    }


    private fun loadImageUrl(url: String, imageView: ImageView, requestOptions: RequestOptions) {
        Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }

    private fun loadImageBytes(bytes: ByteArray, imageView: ImageView, requestOptions: RequestOptions) {
        Glide.with(imageView.context).asBitmap().load(bytes).apply(requestOptions).into(imageView)
    }


    private fun dp2px(value: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, Resources.getSystem().displayMetrics)
    }
}