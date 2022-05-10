package com.example.moduleimage.loaderStrategy.glide

import android.content.res.Resources
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.moduleimage.loaderStrategy.glide.GlideRoundedCornersTransform.CornerType
import android.graphics.Bitmap
import android.util.TypedValue
import android.widget.ImageView
import com.example.moduleimage.loaderStrategy.control.ImageLoaderInterface
import java.io.ByteArrayOutputStream

object ImageLoaderGlide : ImageLoaderInterface {
    override fun loadImageUrl(url: String?, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(url)
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