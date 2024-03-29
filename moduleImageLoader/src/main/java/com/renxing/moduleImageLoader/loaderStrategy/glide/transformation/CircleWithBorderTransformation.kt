package com.renxing.moduleImageLoader.loaderStrategy.glide.transformation

import android.content.res.Resources
import android.graphics.*
import androidx.annotation.ColorInt
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.util.Util
import java.security.MessageDigest

/**
 * 带边框的圆形
 * @author : BaoZhou
 * @date : 2019/3/22 21:49
 */
class CircleWithBorderTransformation(borderWidth: Float, @ColorInt borderColor: Int) :
    BitmapTransformation() {
    private val mBorderPaint: Paint = Paint()
    private val mBorderWidth: Float = Resources.getSystem().displayMetrics.density * borderWidth
    private val ID = "com.bumptech.glide.transformations.FillSpace"
    private val ID_ByTES = ID.toByteArray(CHARSET)
    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        return circleCrop(toTransform)!!
    }

    private fun circleCrop(source: Bitmap?): Bitmap? {
        if (source == null) {
            return null
        }
        val size = (source.width.coerceAtMost(source.height) - mBorderWidth / 2).toInt()
        val x = (source.width - size) / 2
        val y = (source.height - size) / 2
        val squared = Bitmap.createBitmap(source, x, y, size, size)
        val result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)
        val paint = Paint()
        paint.shader = BitmapShader(squared, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.isAntiAlias = true
        val r = size / 2f
        canvas.drawCircle(r, r, r, paint)
        val borderRadius = r - mBorderWidth / 2
        canvas.drawCircle(r, r, borderRadius, mBorderPaint)
        return result
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_ByTES)
    }

    override fun equals(o: Any?): Boolean {
        if (o is CircleWithBorderTransformation) {
            return mBorderWidth == o.mBorderWidth
        }
        return false
    }

    override fun hashCode(): Int {
        return Util.hashCode(
            ID.hashCode(),
            Util.hashCode(mBorderWidth)
        )
    }

    init {
        mBorderPaint.isDither = true
        mBorderPaint.isAntiAlias = true
        mBorderPaint.color = borderColor
        mBorderPaint.style = Paint.Style.STROKE
        mBorderPaint.strokeWidth = mBorderWidth
    }
}