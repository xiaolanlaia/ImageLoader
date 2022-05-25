package com.renxing.moduleImageLoader.loaderStrategy.control

import com.bumptech.glide.request.target.Target
import com.renxing.moduleImageLoader.R
import java.util.concurrent.Future

/**
 *@date    :  2022/5/25 8:08
 *@author  :  WuJianFeng
 */
interface RXFutureTarget<R> : Future<R?>, Target<R?>