package com.renxing.moduleImageLoader.loaderStrategy.glide.target

import android.graphics.drawable.Drawable
import com.bumptech.glide.request.transition.Transition

/**
 *@date    :  2022/6/6 14:36
 *@author  :  WuJianFeng
 */
class RXCustomTargetDrawable : RXCustomTarget<Drawable>(){
    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
    }

    override fun onLoadCleared(placeholder: Drawable?) {
    }
}