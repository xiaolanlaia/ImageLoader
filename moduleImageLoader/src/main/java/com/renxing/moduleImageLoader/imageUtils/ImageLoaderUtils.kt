package com.renxing.moduleImageLoader.imageUtils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.NinePatch
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.NinePatchDrawable
import android.text.TextUtils
import android.util.TypedValue
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.ByteArrayOutputStream

internal object ImageLoaderUtils {

    fun appendUrl(url: String, width: Int, height: Int, needToPx: Boolean): String {
        var newUrl = ""
        url.run{
            if (!this.contains(URL_APPEND_WIDTH)) {
                newUrl = if (needToPx) {
                    this + URL_APPEND_WIDTH + DisplayUtils.dp2px(width.toFloat()) + URL_APPEND_HEIGHT + DisplayUtils.dp2px(height.toFloat()) + interlaceStr
                } else {
                    this + URL_APPEND_WIDTH + width + URL_APPEND_HEIGHT + height + interlaceStr
                }
            }
        }
        return newUrl
    }

    fun checkUrl(url: String): Boolean {
        if (TextUtils.isEmpty(url)) {
            return true
        }
        return url.endsWith("null")
    }
    fun activityFinishedOrDestroyed(mContext: Context): Boolean {
        if (mContext is Activity) {
            if (mContext.isFinishing || mContext.isDestroyed) {
                return true
            }
        }

        return false
    }
    fun appendUrl(url: String): String {
        var newUrl = ""
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
        var newUrl = ""
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
        var ninePatchDrawable: NinePatchDrawable? = null
        ninePatchDrawable = if (NinePatch.isNinePatchChunk(chunk)) {
            NinePatchDrawable(context.resources, bitmap, chunk, Rect(), null)
        } else {
            return BitmapDrawable(context.resources, bitmap)
        }
        return ninePatchDrawable
    }


    /**
     * 把Bitmap转Byte
     */
    fun bitmap2Bytes(bitmap: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        return baos.toByteArray()
    }
}