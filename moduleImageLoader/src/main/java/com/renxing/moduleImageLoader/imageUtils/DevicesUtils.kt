package com.renxing.moduleImageLoader.imageUtils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.text.format.Formatter
import android.util.Log

/**
 *@date    :  2022/5/13 16:16
 *@author  :  WuJianFeng
 * 设备信息获取帮助类
 */
@SuppressLint("ObsoleteSdkInt")
object DevicesUtils {

    /**
     * 获取手机磁盘剩余大小
     */
    fun getDiskRemainSize(context: Context) {
        val blocksize: Long
        val totalblock: Long
        val availbleblocks: Long
        val stat: StatFs
        val path = Environment.getExternalStorageDirectory()
        stat = StatFs(path.toString());
        //此处进行版本的判断因为在2.3版本中 getBlockSize()等方法还适用
        //之后的有些版本有了新的方法进行替代。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            blocksize = stat.blockSizeLong
            totalblock = stat.blockCountLong
            availbleblocks = stat.availableBlocksLong
        } else {
            blocksize = stat.blockSizeLong
            totalblock = stat.blockCountLong
            availbleblocks = stat.availableBlocksLong
        }

    }



    /**
     * 获取手机内存大小
     *
     * 手机总内存大小   getMemoryInfo(this).totalMem * 1.0/ (1024 * 1024)
     * 手机可用内存大小 getMemoryInfo(this).availMem * 1.0/ (1024 * 1024)
     */
    fun getMemoryInfo(context : Context) : ActivityManager.MemoryInfo {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val mi = ActivityManager.MemoryInfo()
        am.getMemoryInfo(mi)
        return mi
    }

}