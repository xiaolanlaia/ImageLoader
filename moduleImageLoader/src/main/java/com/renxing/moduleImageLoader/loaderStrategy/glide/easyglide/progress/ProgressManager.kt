package com.renxing.moduleImageLoader.loaderStrategy.glide.easyglide.progress

import android.text.TextUtils
import java.util.concurrent.ConcurrentHashMap

/**
 * 进度监听器管理类
 * 加入图片加载进度监听，加入Https支持
 * @author : BaoZhou
 * @date : 2019/3/17 1:49
 */
object ProgressManager {
    private val listenersMap = ConcurrentHashMap<String, OnProgressListener>()


    fun addListener(url: String, listener: OnProgressListener?) {
        if (url.isNotBlank() && listener != null) {
            listenersMap[url] = listener
            listener.onProgress(false, 1, 0, 0)
        }
    }

    fun removeListener(url: String) {
        if (!TextUtils.isEmpty(url)) {
            listenersMap.remove(url)
        }
    }

    fun getProgressListener(url: String): OnProgressListener? {
        return if (TextUtils.isEmpty(url) || listenersMap.size == 0) {
            null
        } else listenersMap[url]
    }
}