package com.renxing.moduleImageLoader.imageUtils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.text.TextUtils
import android.util.TypedValue
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.ByteArrayOutputStream

object ImageLoaderUtils {
    /**
     * 制作一张画布，将四小图合成大图
     */
    fun imageToUtil(leftTopBitmap: Bitmap, rightTopBitmap: Bitmap, leftBottomBitmap: Bitmap, rightBottomBitmap: Bitmap): Bitmap {
        val newValueWidth = compare(intArrayOf(leftTopBitmap.width, rightTopBitmap.width, leftBottomBitmap.width, rightBottomBitmap.width))
        val newValueHeight = compare(intArrayOf(leftTopBitmap.height, rightTopBitmap.height, leftBottomBitmap.height, rightBottomBitmap.height))
        val newWidth = newValueWidth[0] + newValueWidth[1]
        val newHeight = newValueHeight[0] + newValueHeight[1]
        val newBitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(newBitmap)
        canvas.drawBitmap(leftTopBitmap, null, Rect(0, 0, 200, 200), null)
        canvas.drawBitmap(rightTopBitmap, null, Rect(200, 0, 400, 200), null)
        canvas.drawBitmap(leftBottomBitmap, null, Rect(0, 200, 200, 400), null)
        canvas.drawBitmap(rightBottomBitmap, null, Rect(200, 200, 400, 400), null)
        return newBitmap
    }

    /**
     * 从大到下排序，冒泡排序法
     * @param values
     * @return
     */
    private fun compare(values: IntArray): IntArray {
        for (i in 0 until values.size - 1) {
            for (j in 0 until values.size - i - 1) {
                var tamp: Int
                if (values[j] < values[j + 1]) {
                    tamp = values[j]
                    values[j] = values[j + 1]
                    values[j + 1] = tamp
                }
            }
        }
        return values
    }

    fun appendUrl(url: String, width: Int, height: Int, needToPx: Boolean): String {
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

    /**
     * 把Bitmap转Byte
     */
    fun bitmap2Bytes(bitmap: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        return baos.toByteArray()
    }


    fun loadImageUrl(url: String, imageView: ImageView, requestOptions: RequestOptions) {
        Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }

    fun loadImageBytes(bytes: ByteArray, imageView: ImageView, requestOptions: RequestOptions) {
        Glide.with(imageView.context).asBitmap().load(bytes).apply(requestOptions).into(imageView)
    }


    fun dp2px(value: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, Resources.getSystem().displayMetrics)
    }


}