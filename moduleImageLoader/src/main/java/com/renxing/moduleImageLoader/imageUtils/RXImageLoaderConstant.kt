package com.renxing.moduleImageLoader.imageUtils

import android.app.Application
import com.bumptech.glide.load.engine.DiskCacheStrategy

const val HTTP = "http"
const val HTTPS = "https"
const val PNG_LOWERCASE = "png"
const val PNG_UPPERCASE = "PNG"
const val JPEG_LOWERCASE = "jpeg"
const val JPEG_UPPERCASE = "JPEG"
const val JPG_LOWERCASE = "jpg"
const val JPG_UPPERCASE = "JPG"
const val QUERY_MARK = "?"
const val URL_APPEND_STR = "?imageslim"
const val URL_APPEND_WIDTH = "?imageView2/1/w/"
const val URL_APPEND_HEIGHT = "/h/"
const val interlaceStr = "/interlace/1/q/75"
const val CORNER_SUFFIX = "?roundPic/radius/" //?roundPic/radius/50

/**
 *@author  :  WuJianFeng
 * 对外提供
 */
object RXImageLoaderConstant {

    var moduleImageApplication: Application? = null


    enum class CornerType {
        ALL,
        TOP,
        TOP_LEFT,
        TOP_RIGHT,
        TOP_LEFT_BOTTOM_RIGHT,
        TOP_RIGHT_BOTTOM_LEFT,
        TOP_LEFT_TOP_RIGHT_BOTTOM_RIGHT,
        TOP_RIGHT_BOTTOM_RIGHT_BOTTOM_LEFT,
        BOTTOM,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        LEFT,
        RIGHT,
        DEFAULT
    }

    enum class DiskCacheStrategyEnum{
        NONE,
        ALL,
        AUTOMATIC
    }


}