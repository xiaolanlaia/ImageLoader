package com.renxing.moduleImageLoader.imageUtils

internal object DisplayUtils {

    fun dp2px(dp: Float): Int {
        val scale = RXImageLoaderConstant.moduleImageApplication!!.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

}