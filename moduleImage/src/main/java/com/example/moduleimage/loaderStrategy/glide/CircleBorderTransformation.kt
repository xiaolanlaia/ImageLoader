package com.example.moduleimage.loaderStrategy.glide

import android.graphics.Bitmap
import androidx.annotation.ColorInt
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.util.Preconditions
import com.bumptech.glide.util.Util
import com.example.moduleimage.imageUtils.GlideTransformationUtils
import java.nio.ByteBuffer
import java.security.MessageDigest

class CircleBorderTransformation(borderWidth: Float, @ColorInt borderColor: Int) : BitmapTransformation() {
    private val borderWidth: Float
    private val borderColor: Int

    // Bitmap doesn't implement equals, so == and .equals are equivalent here.
    override fun transform(
        pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int
    ): Bitmap {
        return GlideTransformationUtils.circleCrop(
            pool,
            toTransform,
            outWidth,
            outHeight,
            borderWidth,
            borderColor
        )
    }

    override fun equals(o: Any?): Boolean {
        if (o is CircleBorderTransformation) {
            return (borderWidth == o.borderWidth
                    && borderColor == o.borderColor)
        }
        return false
    }

    override fun hashCode(): Int {
        val hashCode = Util.hashCode(
            ID.hashCode(),
            Util.hashCode(borderWidth)
        )
        return Util.hashCode(borderColor, hashCode)
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)
        val radiusData = ByteBuffer.allocate(8)
            .putFloat(borderWidth)
            .putInt(borderColor)
            .array()
        messageDigest.update(radiusData)
    }

    companion object {
        // The version of this transformation, incremented to correct an error in a previous version.
        // See #455.
        private const val VERSION = 1
        private const val ID =
            "com.yilahuo.driftbottle.loader.transform.CircleBorderTransformation.$VERSION"
        private val ID_BYTES =
            ID.toByteArray(Key.CHARSET)
    }

    /**
     * Provide the radii to round the corners of the bitmap.
     */
    init {
        Preconditions.checkArgument(
            borderWidth > 0,
            "borderWidth must be more the 0."
        )
        this.borderWidth = borderWidth
        this.borderColor = borderColor
    }
}