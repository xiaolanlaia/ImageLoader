package com.renxing.moduleImageLoader.imageUtils

import android.app.Application

const val HTTP_LOWERCASE = "http"
const val HTTP_UPPERCASE = "HTTP"
const val HTTPS_LOWERCASE = "https"
const val HTTPS_UPPERCASE = "HTTPS"
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
const val INTERLACE = "/interlace/1/q/75"
const val CORNER_SUFFIX = "?roundPic/radius/" //?roundPic/radius/50
const val VOIMIGO_PRE = "https://voimigo.chongqiwawa6.com/" //

/**
 *@author  :  WuJianFeng
 * 对外提供
 */
object RXImageLoaderConstant {

    var moduleImageApplication: Application? = null
}