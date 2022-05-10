package com.renxing.moduleImage.imageUtils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect

object ImageToUtil {
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
                    this + ModuleImageConstant.URL_APPEND_WIDTH + DisplayUtils.dp2px(width.toFloat()) + ModuleImageConstant.URL_APPEND_HEIGHT + DisplayUtils.dp2px(
                        height.toFloat()
                    ) + ModuleImageConstant.interlaceStr
                } else {
                    this + ModuleImageConstant.URL_APPEND_WIDTH + width + ModuleImageConstant.URL_APPEND_HEIGHT + height + ModuleImageConstant.interlaceStr
                }
            }
        }
        return newUrl
    }
}