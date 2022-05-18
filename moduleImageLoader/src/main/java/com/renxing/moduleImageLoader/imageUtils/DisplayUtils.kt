package com.renxing.moduleImageLoader.imageUtils

import android.app.Activity
import android.content.res.Resources
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import java.lang.Exception


internal object DisplayUtils {


    fun px(dipValue: Float): Int {
        val r = Resources.getSystem()
        val scale = r.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     */
    fun px2dip(pxValue: Float): Int {
        val scale = RXImageLoaderConstant.moduleImageApplication!!.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     */
    fun px2dip(res: Resources, pxValue: Float): Int {
        val scale = res.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    fun dp2px(dp: Float): Int {
        val scale = RXImageLoaderConstant.moduleImageApplication!!.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    fun dp2Px(dp: Int): Int {
        val r = RXImageLoaderConstant.moduleImageApplication!!.resources
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics).toInt()
    }

    /**
     * 将dimen中的值转换为px值，保证尺寸大小不变
     */
    fun dimenToPx(rDimen: Int): Int {
        val r = RXImageLoaderConstant.moduleImageApplication!!.resources
        return r.getDimensionPixelSize(rDimen)
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    fun px2sp(pxValue: Float, fontScale: Float): Int {
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    fun sp2px(spValue: Float, fontScale: Float): Int {
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * 获取屏幕宽、高；返回一个数组，[0] = width, [1] = height
     */
    val displayPxArray: IntArray
        get() {
            val displays = IntArray(2)
            val dm: DisplayMetrics = RXImageLoaderConstant.moduleImageApplication!!.resources.displayMetrics
            displays[0] = dm.widthPixels
            displays[1] = dm.heightPixels
            return displays
        }

    /**
     * 获取屏幕高；返回int
     */
    val displayPxHeight: Int
        get() {
            val dm: DisplayMetrics = RXImageLoaderConstant.moduleImageApplication!!.resources.displayMetrics
            return dm.heightPixels
        }

    /**
     * 获取屏幕宽；返回int
     */
    val displayPxWidth: Int
        get() {
            val dm: DisplayMetrics = RXImageLoaderConstant.moduleImageApplication!!.resources.displayMetrics
            return dm.widthPixels
        }

    /**
     * 获取状态栏高度px
     */
    fun getStatusBarHigh(res: Resources): Int {
        var statusBar = 0
        try {
            val c: Class<*>
            c = Class.forName("com.android.internal.R\$dimen")
            val obj = c.newInstance()
            val field = c.getField("status_bar_height")
            val x = field[obj].toString().toInt()
            statusBar = res.getDimensionPixelSize(x)
        } catch (e: Exception) {
            statusBar = 0
        }
        return statusBar
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param （DisplayMetrics类中属性density）
     * @return
     */
    fun dip2px(dipValue: Float): Int {
        val scale = RXImageLoaderConstant.moduleImageApplication!!.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    fun px2sp(pxValue: Float): Int {
        val fontScale = RXImageLoaderConstant.moduleImageApplication!!.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    fun sp2px(spValue: Float): Int {
        val fontScale = RXImageLoaderConstant.moduleImageApplication!!.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    fun getDm(mActivity: Activity): DisplayMetrics {
        val dm = DisplayMetrics()
        mActivity.windowManager.defaultDisplay.getMetrics(dm)
        return dm
    }

    /**
     * 获取屏幕宽高
     *
     * @return
     */
    val screen: IntArray
        get() {
            val screen = IntArray(2)
            val dm: DisplayMetrics = RXImageLoaderConstant.moduleImageApplication!!.resources.displayMetrics
            screen[0] = dm.widthPixels
            screen[1] = dm.heightPixels
            return screen
        }

    /**
     * 获取屏幕宽高
     *
     * @return
     */
    val screenDpi: Int
        get() {
            val dm: DisplayMetrics = RXImageLoaderConstant.moduleImageApplication!!.resources.displayMetrics
            return dm.densityDpi
        }

    fun hideBottomUIMenu(activity: Activity) {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            val v: View = activity.getWindow().getDecorView()
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            val decorView: View = activity.getWindow().getDecorView()
            val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN)
            decorView.systemUiVisibility = uiOptions
        }
    }

}