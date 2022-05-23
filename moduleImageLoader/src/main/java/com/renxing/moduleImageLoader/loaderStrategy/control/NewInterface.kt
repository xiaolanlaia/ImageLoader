package com.renxing.moduleImageLoader.loaderStrategy.control

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.renxing.moduleImageLoader.imageUtils.enumUtils.CornerTypeEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.DiskCacheStrategyEnum
import com.renxing.moduleImageLoader.loaderStrategy.glide.target.RXCustomTarget

/**
 *@date    :  2022/5/20 14:44
 *@author  :  WuJianFeng
 * 展示在ImageView的接口
 */
interface NewInterface {
    fun builder(context: Context, urlOrIdOrUri : Any) : NewInterface
    fun builder(context: Context, url : String, width: Int, height: Int) : NewInterface
    fun fitCenter() : NewInterface
    fun centerCrop() : NewInterface
    fun centerInside() : NewInterface
    fun placeholderAndErrImg(placeholder : Int) : NewInterface
    fun placeholderAndErrImg(placeholder : Int, error : Int) : NewInterface
    fun error(id : Int) : NewInterface
    fun diskCacheStrategy(diskCacheStrategyEnum: DiskCacheStrategyEnum) : NewInterface
    fun transition(transition : Boolean) : NewInterface
    fun thumbnail(context: Context, urlOrIdOrUri : Int, thumbnailWidth: Int, thumbnailHeight: Int) : NewInterface
    fun circleCrop() : NewInterface
    fun cornersCrop(cornerRadius : Float) : NewInterface
    fun cornersCrop(cornerRadius : Float, cornerTypeEnum : CornerTypeEnum) : NewInterface
    fun into(imageView: ImageView) : NewInterface
}