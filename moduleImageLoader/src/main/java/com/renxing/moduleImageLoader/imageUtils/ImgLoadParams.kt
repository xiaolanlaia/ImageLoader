package com.renxing.moduleImageLoader.imageUtils

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.renxing.moduleImageLoader.imageUtils.enumUtils.*
import com.renxing.moduleImageLoader.loaderStrategy.control.*


class ImgLoadParams constructor(var context: Context) {
    var url : String? = null
    var id : Int = 0
    var uri : Uri? = null
    var drawable : Drawable? = null
    var colorDrawable : ColorDrawable? = null
    var placeholder : Int = 0
    var error : Int = 0
    var imageView : ImageView? = null
    var imageWidth : Int = 0
    var imageHeight : Int = 0
    var diskcacheStrategyEnum : DiskCacheStrategyEnum? = null
    var registerAnimationCallback : RegisterAnimationCallback? = null
    var rxListener : RXListener? = null
    var setLoopCount : Int = 0
    var intoBitmapTarget : IntoBitmapTarget? =null
    var intoDrawableTarget : IntoDrawableTarget? =null
    var transitionEnum : ArrayList<TransitionEnum> = ArrayList<TransitionEnum>()
    //默认是 RequestBuilderTypeEnum.DRAWABLE
    var requestBuilderTypeEnum : RequestBuilderTypeEnum = RequestBuilderTypeEnum.DRAWABLE
    var fitCenter : Boolean = false
    var centerCrop : Boolean = false
    var centerInside : Boolean = false
    var circleCrop : Boolean = false
    var cornerRadius : Float = 0f
    var borderColor : Int = 0
    var borderWidth : Float = 0f
    var priority : PriorityEnum? = null
    var dontAnimate : Boolean = false
    var crossfade : Boolean = false

    fun load(url : String?) : ImgLoadParams{
        this.url = url
        return this
    }
    fun load(id : Int) : ImgLoadParams{
        this.id = id
        return this
    }
    fun load(uri: Uri?) : ImgLoadParams{
        this.uri = uri
        return this
    }
    fun load(drawable: Drawable?) : ImgLoadParams{
        this.drawable = drawable
        return this
    }
    fun load(colorDrawable: ColorDrawable?) : ImgLoadParams{
        this.colorDrawable = colorDrawable
        return this
    }
    fun placeholder(placeholder : Int) : ImgLoadParams{
        this.placeholder = placeholder
        return this
    }
    fun error(error : Int) : ImgLoadParams{
        this.error = error
        return this
    }


    fun into(imageView: ImageView?) : ImgLoadParams{
        this.imageView = imageView
        return this
    }
    fun override(imageWidth : Int, imageHeight : Int) : ImgLoadParams{
        this.imageWidth = imageWidth
        this.imageHeight = imageHeight
        return this
    }
    fun override(imageWidth : Float, imageHeight : Float) : ImgLoadParams{
        this.imageWidth = imageWidth.toInt()
        this.imageHeight = imageHeight.toInt()
        return this
    }
    fun diskcacheStrategy(diskcacheStrategyEnum : DiskCacheStrategyEnum?) : ImgLoadParams{
        this.diskcacheStrategyEnum = diskcacheStrategyEnum
        return this
    }
    fun registerAnimationCallback(registerAnimationCallback : RegisterAnimationCallback?) : ImgLoadParams{
        this.registerAnimationCallback = registerAnimationCallback
        return this
    }
    fun rxListener(rxListener : RXListener?) : ImgLoadParams{
        this.rxListener = rxListener
        return this
    }
    fun setLoopCount(setLoopCount : Int) : ImgLoadParams{
        this.setLoopCount = setLoopCount
        return this
    }
    fun  intoBitmapTarget(intoBitmapTarget : IntoBitmapTarget?) : ImgLoadParams{
        this.intoBitmapTarget = intoBitmapTarget
        return this
    }
    fun  intoDrawableTarget(intoDrawableTarget : IntoDrawableTarget?) : ImgLoadParams{
        this.intoDrawableTarget = intoDrawableTarget
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
    fun requestBuilderTypeEnum(requestBuilderTypeEnum : RequestBuilderTypeEnum) : ImgLoadParams{
        this.requestBuilderTypeEnum = requestBuilderTypeEnum
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
    fun circleCrop(circleCrop : Boolean) : ImgLoadParams{
        this.circleCrop = circleCrop
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
    fun priority(priority : PriorityEnum) : ImgLoadParams{
        this.priority = priority
        return this
    }
    fun dontAnimate(dontAnimate : Boolean) : ImgLoadParams{
        this.dontAnimate = dontAnimate
        return this
    }
    fun crossfade(crossfade : Boolean) : ImgLoadParams{
        this.crossfade = crossfade
        return this
    }
}