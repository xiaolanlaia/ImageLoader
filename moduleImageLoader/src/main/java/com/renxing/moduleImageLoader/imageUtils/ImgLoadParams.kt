package com.renxing.moduleImageLoader.imageUtils

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.renxing.moduleImageLoader.imageUtils.enumUtils.DiskCacheStrategyEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.ScaleTypeEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.TransitionEnum
import com.renxing.moduleImageLoader.loaderStrategy.control.RXRequestListener
import com.renxing.moduleImageLoader.loaderStrategy.glide.target.RXCustomTarget

class ImgLoadParams constructor(var context: Context) {
    var url : String? = null
    var id : Int = 0
    var uri : Uri? = null
    var drawable : Drawable? = null
    var colorDrawable : ColorDrawable? = null
    var placeholder : Int = 0
    var imageView : ImageView? = null
    var imageWidth : Int = 0
    var imageHeight : Int = 0
    var diskcacheStrategyEnum : DiskCacheStrategyEnum? = null
    var rxRequestListener : RXRequestListener<Drawable?>? = null
    var rxCustomTarget : RXCustomTarget<Drawable?>? =null
    var transitionEnum : ArrayList<TransitionEnum> = ArrayList<TransitionEnum>()
    var scaleTypeEnum : ScaleTypeEnum? = null
    var asGif : Boolean = false
    var fitCenter : Boolean = false
    var centerCrop : Boolean = false
    var centerInside : Boolean = false
    var cornerRadius : Float = 0f
    var borderColor : Int = 0
    var borderWidth : Float = 0f

    fun load(url : String) : ImgLoadParams{
        this.url = url
        return this
    }
    fun load(id : Int) : ImgLoadParams{
        this.id = id
        return this
    }
    fun load(uri: Uri) : ImgLoadParams{
        this.uri = uri
        return this
    }
    fun load(drawable: Drawable) : ImgLoadParams{
        this.drawable = drawable
        return this
    }
    fun load(colorDrawable: ColorDrawable) : ImgLoadParams{
        this.colorDrawable = colorDrawable
        return this
    }
    fun placeholder(placeholder : Int) : ImgLoadParams{
        this.placeholder = placeholder
        return this
    }


    fun into(imageView: ImageView) : ImgLoadParams{
        this.imageView = imageView
        return this
    }
    fun size(imageWidth : Int,imageHeight : Int) : ImgLoadParams{
        this.imageWidth = imageWidth
        this.imageHeight = imageHeight
        return this
    }
    fun diskcacheStrategyEnum(diskcacheStrategyEnum : DiskCacheStrategyEnum) : ImgLoadParams{
        this.diskcacheStrategyEnum = diskcacheStrategyEnum
        return this
    }
    fun rxRequestListener(rxRequestListener : RXRequestListener<Drawable?>) : ImgLoadParams{
        this.rxRequestListener = rxRequestListener
        return this
    }
    fun rxCustomTarget(rxCustomTarget : RXCustomTarget<Drawable?>) : ImgLoadParams{
        this.rxCustomTarget = rxCustomTarget
        return this
    }
    fun transitionEnum(transitionEnum: TransitionEnum) : ImgLoadParams{
        this.transitionEnum.add(transitionEnum)
        return this
    }
    fun transitionEnum(transitionEnum1: TransitionEnum,transitionEnum2: TransitionEnum) : ImgLoadParams{
        this.transitionEnum.add(transitionEnum1)
        this.transitionEnum.add(transitionEnum2)
        return this
    }
    fun scaleType(scaleTypeEnum: ScaleTypeEnum) : ImgLoadParams{
        this.scaleTypeEnum = scaleTypeEnum
        return this
    }
    fun asGif(asGif : Boolean) : ImgLoadParams{
        this.asGif = asGif
        return this
    }
    fun fitCenter(fitCenter : Boolean) : ImgLoadParams{
        this.fitCenter = fitCenter
        return this
    }
    fun centerCrop(centerCrop : Boolean) : ImgLoadParams{
        this.centerCrop = centerCrop
        return this
    }
    fun centerInside(centerInside : Boolean) : ImgLoadParams{
        this.centerInside = centerInside
        return this
    }
    fun cornerRadius(cornerRadius : Float) : ImgLoadParams{
        this.cornerRadius = cornerRadius
        return this
    }
    fun borderColor(borderColor : Int) : ImgLoadParams{
        this.borderColor = borderColor
        return this
    }
    fun borderWidth(borderWidth : Float) : ImgLoadParams{
        this.borderWidth = borderWidth
        return this
    }
}