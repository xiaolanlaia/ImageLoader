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
import java.lang.StringBuilder

/**
 *@author  :  WuJianFeng
 */
object ImageLoaderUtils {

    fun appendUrl(url: String?, width: Float, height: Float, needToPx: Boolean): String {
        if (TextUtils.isEmpty(url)){
            return ""
        }

        val newUrlSB = StringBuilder()
        //半路经url则补全路径
        if (!url!!.startsWith(HTTP_LOWERCASE) && !url.startsWith(HTTP_UPPERCASE)){
            newUrlSB.append(VOIMIGO_PRE)
        }
        newUrlSB.append(url)

        //若url中存在?，说明是已经拼接过的url，不做任何操作
        if (url.contains(QUERY_MARK)){
            return newUrlSB.toString()
        }

        //添加压缩
        if (!url!!.contains(QUERY_MARK) &&
            (url.endsWith(PNG_LOWERCASE) ||
                    url.endsWith(PNG_UPPERCASE) ||
                    url.endsWith(JPEG_UPPERCASE) ||
                    url.endsWith(JPEG_LOWERCASE) ||
                    url.endsWith(JPG_UPPERCASE) ||
                    url.endsWith(JPG_LOWERCASE))) {

            newUrlSB.append(URL_APPEND_STR).append(STRATEGY_LINK)
        }else{
            newUrlSB.append(QUERY_MARK)

        }

        //拼接固定大小的url
        if (!url.contains(URL_APPEND_WIDTH)) {
            if (needToPx) {
                newUrlSB
                    .append(URL_APPEND_WIDTH)
                    .append(DisplayUtils.dp2px(width))
                    .append(URL_APPEND_HEIGHT)
                    .append(DisplayUtils.dp2px(height))
                    .append(INTERLACE)

            } else {
                newUrlSB
                    .append(URL_APPEND_WIDTH)
                    .append(width.toInt())
                    .append(URL_APPEND_HEIGHT)
                    .append(height.toInt())
                    .append(INTERLACE)
            }
        }


        return newUrlSB.toString()
    }

    fun appendUrl(url: String?, width: Float, height: Float): String {
        if (TextUtils.isEmpty(url)){
            return ""
        }
        val newUrlSB = StringBuilder()
        //半路经url则补全路径
        if (!url!!.startsWith(HTTP_LOWERCASE) && !url.startsWith(HTTP_UPPERCASE)){
            newUrlSB.append(VOIMIGO_PRE)
        }

        newUrlSB.append(url)

        //若url中存在?，说明是已经拼接过的url，不做任何操作
        if (url.contains(QUERY_MARK)){
            return newUrlSB.toString()
        }

        //添加压缩
        if (!url!!.contains(QUERY_MARK) &&
            (url.endsWith(PNG_LOWERCASE) ||
                    url.endsWith(PNG_UPPERCASE) ||
                    url.endsWith(JPEG_UPPERCASE) ||
                    url.endsWith(JPEG_LOWERCASE) ||
                    url.endsWith(JPG_UPPERCASE) ||
                    url.endsWith(JPG_LOWERCASE))) {

            newUrlSB.append(URL_APPEND_STR).append(STRATEGY_LINK)
        }else{
            newUrlSB.append(QUERY_MARK)

        }

        //拼接固定大小的url
        if (!url.contains(URL_APPEND_WIDTH)) {
            newUrlSB
                .append(URL_APPEND_WIDTH)
                .append(DisplayUtils.dp2px(width))
                .append(URL_APPEND_HEIGHT)
                .append(DisplayUtils.dp2px(height))
                .append(INTERLACE)
        }

        return newUrlSB.toString()

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
    fun appendSlim(url: String?): String {
        if (TextUtils.isEmpty(url)){
            return ""
        }

        val newUrlSB = StringBuilder()
        //半路经url则补全路径
        if (!url!!.startsWith(HTTP_LOWERCASE) && !url.startsWith(HTTP_UPPERCASE)){
            newUrlSB.append(VOIMIGO_PRE)
        }
        newUrlSB.append(url)
        //url必须是不包含?，否则说明是已经拼接过的链接，不做操作直接返回
        if (!url!!.contains(QUERY_MARK) &&
            (url.endsWith(PNG_LOWERCASE) ||
                    url.endsWith(PNG_UPPERCASE) ||
                    url.endsWith(JPEG_UPPERCASE) ||
                    url.endsWith(JPEG_LOWERCASE) ||
                    url.endsWith(JPG_UPPERCASE) ||
                    url.endsWith(JPG_LOWERCASE))) {

            newUrlSB.append(URL_APPEND_STR)
        }
        return newUrlSB.toString()
    }

    fun replaceHttpToHttps(url: String?) : String{
        if (TextUtils.isEmpty(url)){
            return ""
        }
        var newUrl: String
        url.run{
            //将http替换为https
            if (this!!.startsWith(HTTP_LOWERCASE) && !startsWith(HTTPS_LOWERCASE)){
                newUrl = replace(HTTP_LOWERCASE, HTTPS_LOWERCASE)
            }else{
                return url!!
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

    fun setNinePathImage(context: Context, imageView: View?, bitmap: Bitmap?) {
        if (bitmap == null || imageView == null) return
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

    fun contextIsError(context: Context?): Boolean {

        if (context == null){
            return true
        }
        if (context is Activity) {
            if ((context as Activity).isFinishing || (context as Activity).isDestroyed) {
                return true
            }
        }
        return false
    }
}