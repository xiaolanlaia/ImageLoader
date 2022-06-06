package com.renxing.moduleImageLoader.imageUtils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.NinePatch
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.NinePatchDrawable
import android.net.Uri
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.renxing.moduleImageLoader.loaderStrategy.control.OnAnimationStatus
import java.io.ByteArrayOutputStream

/**
 *@author  :  WuJianFeng
 */
object ImageLoaderUtils {

    fun appendUrl(url: String, width: Int, height: Int, needToPx: Boolean): String {
        if (TextUtils.isEmpty(url)){
            return ""
        }
        var newUrl: String
        url.run{
            newUrl = if (!this.contains(URL_APPEND_WIDTH)) {
                if (needToPx) {
                    this + URL_APPEND_WIDTH + DisplayUtils.dp2px(width.toFloat()) + URL_APPEND_HEIGHT + DisplayUtils.dp2px(height.toFloat()) + interlaceStr
                } else {
                    this + URL_APPEND_WIDTH + width + URL_APPEND_HEIGHT + height + interlaceStr
                }
            }else{
                url
            }
        }
        return newUrl
    }

    fun appendUrl(url: String, width: Float, height: Float, needToPx: Boolean): String {
        if (TextUtils.isEmpty(url)){
            return ""
        }
        var newUrl: String
        url.run{
            newUrl = if (!this.contains(URL_APPEND_WIDTH)) {
                if (needToPx) {
                    this + URL_APPEND_WIDTH + DisplayUtils.dp2px(width) + URL_APPEND_HEIGHT + DisplayUtils.dp2px(height) + interlaceStr
                } else {
                    this + URL_APPEND_WIDTH + width.toInt() + URL_APPEND_HEIGHT + height.toInt() + interlaceStr
                }
            }else{
                url
            }
        }
        return newUrl
    }

    fun appendUrl(url: String, width: Float, height: Float): String {
        if (TextUtils.isEmpty(url)){
            return ""
        }
        var newUrl: String
        url.run{
            newUrl = if (!this.contains(URL_APPEND_WIDTH)) {
                this + URL_APPEND_WIDTH + DisplayUtils.dp2px(width) + URL_APPEND_HEIGHT + DisplayUtils.dp2px(height) + interlaceStr

            }else{
                url
            }
        }
        return newUrl
    }


    /**
     * 只处理png和jpg格式图片
     */
    fun checkAndAppendCornerUrl(url: Any, cornerRadius: Float) : Any{

        if (url !is String) return false

        if (!url.endsWith(PNG_LOWERCASE) && !url.endsWith(PNG_UPPERCASE) && !url.endsWith(JPG_LOWERCASE) && !url.endsWith(JPG_UPPERCASE)){
            //不是规定的格式
            return false
        }

        return url+ CORNER_SUFFIX + cornerRadius
    }


    fun checkUrlOrId(urlOrIdOrUri: Any) {
        if (urlOrIdOrUri !is String && urlOrIdOrUri !is Int && urlOrIdOrUri !is Uri) {
            throw IllegalArgumentException("urlOrIdOrUri is not correct argument")
        }
    }

    fun activityFinishedOrDestroyed(mContext: Context): Boolean {
        if (mContext is Activity) {
            if (mContext.isFinishing || mContext.isDestroyed) {
                return true
            }
        }

        return false
    }
    fun appendSlim(url: String): String {
        var newUrl: String
        url.run{
            if (startsWith(HTTPS) &&
                (endsWith(PNG_LOWERCASE) ||
                        endsWith(PNG_UPPERCASE) ||
                        endsWith(JPEG_UPPERCASE) ||
                        endsWith(JPEG_LOWERCASE) ||
                        endsWith(JPG_UPPERCASE) ||
                        endsWith(JPG_LOWERCASE)) &&
                !contains(QUERY_MARK)) {

                newUrl = this + URL_APPEND_STR
            }else{
                return url
            }
        }
        return newUrl
    }

    fun replaceHttpToHttps(url: String) : String{
        var newUrl: String
        url.run{
            //将http替换为https
            if (startsWith(HTTP) && !startsWith(HTTPS)){
                newUrl = replace(HTTP, HTTPS)
            }else{
                return url
            }
        }
        return newUrl
    }



    fun dp2px(value: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, Resources.getSystem().displayMetrics)
    }

    fun getNinePatchDrawable(bitmap: Bitmap, context: Context): Drawable {
        val chunk = bitmap.ninePatchChunk
        return if (NinePatch.isNinePatchChunk(chunk)) {
            NinePatchDrawable(context.resources, bitmap, chunk, Rect(), null)
        } else {
            BitmapDrawable(context.resources, bitmap)
        }
    }


    /**
     * 把Bitmap转Byte
     */
    fun bitmap2Bytes(bitmap: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        return baos.toByteArray()
    }

    fun setNinePathImage(context: Context, imageView: View, bitmap: Bitmap?) {
        if (bitmap == null) return
        val chunk = bitmap.ninePatchChunk
        if (NinePatch.isNinePatchChunk(chunk)) {
            val patchy = NinePatchDrawable(
                context.resources,
                bitmap,
                chunk,
                NinePatchChunk.deserialize(chunk).mPaddings,
                null
            )
            imageView.background = patchy
        }
    }



    fun gifDrawableRequestListener(playTimes : Int,onAnimationStatus: OnAnimationStatus?) : RequestListener<GifDrawable> {
        return object : RequestListener<GifDrawable> {

            override fun onResourceReady(
                resource: GifDrawable,
                model: Any,
                target: Target<GifDrawable>,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                resource.setLoopCount(playTimes)
                resource.registerAnimationCallback(object :
                    Animatable2Compat.AnimationCallback() {
                    override fun onAnimationStart(drawable: Drawable) {
                        super.onAnimationStart(drawable)
                        resource.start()
                        onAnimationStatus?.onAnimationStart()
                    }

                    override fun onAnimationEnd(drawable: Drawable) {
                        super.onAnimationEnd(drawable)
                        onAnimationStatus?.onAnimationEnd()
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
        }
    }
}