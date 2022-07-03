
package com.renxing.moduleImageLoader.loaderStrategy.glide.config

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView

open class ImageConfig {
    var url: String? = null
        protected set
    var uri : Uri? = null
        protected set
    var drawable : Drawable? = null
        protected set
    var colorDrawable : ColorDrawable? = null
        protected set
    var drawableId = 0
        protected set
    var imageView: ImageView? = null
        protected set
    var placeholder = 0
        protected set
    var errorPic = 0
        protected set
}