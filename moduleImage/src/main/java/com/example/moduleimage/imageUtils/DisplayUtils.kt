package com.example.moduleimage.imageUtils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Surface
import android.view.View
import java.lang.Exception

/**
 * author : Irene
 * e-mail : 821970751@qq.com
 * date   : 2020/3/17 12:05
 * desc   :
 */
object DisplayUtils {
    /**
     * 得到该手机的drawable文件夹密码
     *
     * @param context
     */
    fun getDensityString(context: Context): String {
        val density = getDipScale(context)
        val desintyString: String = when {
            0.75 == density.toDouble() -> {
                "ldpi"
            }
            1.0 == density.toDouble() -> {
                "mdpi"
            }
            1.5 == density.toDouble() -> {
                "hdpi"
            }
            2.0 == density.toDouble() -> {
                "xhdpi"
            }
            3.0 == density.toDouble() -> {
                "xxhdpi"
            }
            4.0 == density.toDouble() -> {
                "xxxhdpi"
            }
            else -> {
                "hdpi"
            }
        }
        return desintyString
    }

    fun getDipScale(context: Context): Float {
        return context.resources.displayMetrics.density
    }

    fun px(dipValue: Float): Int {
        val r = Resources.getSystem()
        val scale = r.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     */
    fun px2dip(pxValue: Float): Int {
        val scale = AppConfig.application!!.resources.displayMetrics.density
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
        val scale = AppConfig.application!!.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    fun dpToPx(dp: Int): Int {
        val r = AppConfig.application!!.resources
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics).toInt()
    }

    /**
     * 将dimen中的值转换为px值，保证尺寸大小不变
     */
    fun dimenToPx(rDimen: Int): Int {
        val r = AppConfig.application!!.resources
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
            val dm: DisplayMetrics = AppConfig.application!!.resources.displayMetrics
            displays[0] = dm.widthPixels
            displays[1] = dm.heightPixels
            return displays
        }

    /**
     * 获取屏幕高；返回int
     */
    val displayPxHeight: Int
        get() {
            val dm: DisplayMetrics = AppConfig.application!!.resources.displayMetrics
            return dm.heightPixels
        }

    /**
     * 获取屏幕宽；返回int
     */
    val displayPxWidth: Int
        get() {
            val dm: DisplayMetrics = AppConfig.application!!.resources.displayMetrics
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
        val scale = AppConfig.application!!.resources.displayMetrics.density
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
        val fontScale = AppConfig.application!!.resources.displayMetrics.scaledDensity
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
        val fontScale = AppConfig.application!!.resources.displayMetrics.scaledDensity
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
            val dm: DisplayMetrics = AppConfig.application!!.resources.displayMetrics
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
            val dm: DisplayMetrics = AppConfig.application!!.resources.displayMetrics
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
    /**
     * Return the bitmap of screen.
     *
     * @param activity          The activity.
     * @param isDeleteStatusBar True to delete status bar, false otherwise.
     * @return the bitmap of screen
     */
    /**
     * Return the bitmap of screen.
     *
     * @param activity The activity.
     * @return the bitmap of screen
     */
    @JvmOverloads
    fun screenShot(activity: Activity, isDeleteStatusBar: Boolean = false): Bitmap? {
        val decorView: View = activity.getWindow().getDecorView()
        decorView.isDrawingCacheEnabled = true
        decorView.setWillNotCacheDrawing(false)
        val bmp = decorView.drawingCache ?: return null
        val dm = DisplayMetrics()
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm)
        val ret: Bitmap
        ret = if (isDeleteStatusBar) {
            val resources: Resources = activity.getResources()
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            val statusBarHeight = resources.getDimensionPixelSize(resourceId)
            Bitmap.createBitmap(
                bmp,
                0,
                statusBarHeight,
                dm.widthPixels,
                dm.heightPixels - statusBarHeight
            )
        } else {
            Bitmap.createBitmap(bmp, 0, 0, dm.widthPixels, dm.heightPixels)
        }
        decorView.destroyDrawingCache()
        return ret
    }

    /**
     * Return whether screen is landscape.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    val isLandscape: Boolean
        get() = (AppConfig.application!!.resources.configuration.orientation
                == Configuration.ORIENTATION_LANDSCAPE)

    /**
     * Return whether screen is portrait.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    val isPortrait: Boolean
        get() = (AppConfig.application!!.resources.configuration.orientation
                == Configuration.ORIENTATION_PORTRAIT)

    /**
     * Return the rotation of screen.
     *
     * @param activity The activity.
     * @return the rotation of screen
     */
    fun getScreenRotation(activity: Activity): Int {
        return when (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> 0
        }
    }
}