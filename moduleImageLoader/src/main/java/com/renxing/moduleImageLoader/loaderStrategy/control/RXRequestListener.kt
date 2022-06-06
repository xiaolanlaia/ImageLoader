package com.renxing.moduleImageLoader.loaderStrategy.control

import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 *@date    :  2022/5/25 8:47
 *@author  :  WuJianFeng
 */
interface RXRequestListener<R> : RequestListener<R> {

    override fun onLoadFailed(
        e: GlideException?, model: Any, target: Target<R>, isFirstResource: Boolean
    ): Boolean


    override fun onResourceReady(
        resource: R,
        model: Any,
        target: Target<R>,
        dataSource: DataSource,
        isFirstResource: Boolean
    ): Boolean
}