package com.example.moduleimage.loaderStrategy.glide

import android.content.res.Resources
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.moduleimage.loaderStrategy.glide.GlideRoundedCornersTransform.CornerType
import android.graphics.Bitmap
import android.util.TypedValue
import android.widget.ImageView
import com.example.moduleimage.ImageLoaderInterface
import java.io.ByteArrayOutputStream

object ImageLoaderGlide : ImageLoaderInterface{
    override fun loadImageUrl(url: String?, image: ImageView) {
        Glide.with(image.context)
            .load(url)
            .into(image)
    }

    override fun loadCircleImage(url: String, image: ImageView) {
        val requestOptions = RequestOptions().circleCrop()
        loadImageUrl(url, image, requestOptions)
    }

    override fun loadRoundedCornersImage(url: String, image: ImageView, radius: Float) {
        val requestOptions =
            RequestOptions().transform(RoundedCorners((dp2px(radius) + 0.5f).toInt()))
        loadImageUrl(url, image, requestOptions)
    }

    override fun loadRoundedCornersImage(url: String, image: ImageView, radius: Float, cornerType: CornerType) {
        val requestOptions = RequestOptions().optionalTransform(GlideRoundedCornersTransform(dp2px(radius) + 0.5f, cornerType))
        loadImageUrl(url, image, requestOptions)
    }

    override fun loadRoundedCornersImage(bm: Bitmap, image: ImageView, radius: Float, cornerType: CornerType) {
        val requestOptions = RequestOptions().optionalTransform(GlideRoundedCornersTransform(dp2px(radius) + 0.5f, cornerType))
        loadImageBytes(bitmap2Bytes(bm), image, requestOptions)
    }

    /**
     * 把Bitmap转Byte
     */
    private fun bitmap2Bytes(bm: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos)
        return baos.toByteArray()
    }


    private fun loadImageUrl(url: String, image: ImageView, requestOptions: RequestOptions) {
        Glide.with(image.context)
            .load(url)
            .apply(requestOptions)
            .into(image)
    }

    private fun loadImageBytes(bytes: ByteArray, image: ImageView, requestOptions: RequestOptions) {
        Glide.with(image.context).asBitmap().load(bytes).apply(requestOptions).into(image)
    }


    fun dp2px(value: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, Resources.getSystem().displayMetrics)
    }
}