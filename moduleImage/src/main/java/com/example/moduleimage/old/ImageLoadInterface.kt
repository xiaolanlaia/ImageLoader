package com.renxing.image.old

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.request.target.CustomTarget

interface ImageLoadInterface {
    /**
     * 图片加载
     * @param url 路径
     * @param iv  显示控件
     */
    fun loadIv(mContext: Context, url: String, iv: ImageView)
    fun loadIv(mContext: Context, id: Int, iv: ImageView)
    fun loadIv(mContext: Context, url: String, iv: ImageView, placeholderAndErrorId: Int)
    fun loadAvatar(mContext: Context, url: String, iv: ImageView)
    fun loadAvatarSmall(mContext: Context, url: String, iv: ImageView, withPX: Int, heightPX: Int)
    fun loadIvCenterInside(mContext: Context, id: Int, iv: ImageView)
    fun loadIvCenterInside(mContext: Context, url: String, iv: ImageView)
    fun loadIvCenterInsideNoMemory(mContext: Context, url: String, iv: ImageView, withPX: Int, heightPX: Int)
    fun loadIvNoType(mContext: Context, url: String, iv: ImageView)
    fun loadIvHomeMixFragment(mContext: Context, url: String, iv: ImageView, placeholderAndErrorId: Int)
    fun loadIvHomeFragment(mContext: Context, url: String, iv: ImageView, placeholderAndErrorId: Int)
    fun loadIvNoMemoryCacheInChatRoom(mContext: Context, url: String, iv: ImageView, placeholderAndErrorId: Int)
    fun loadIvCircleNoCache(mContext: Context, uri: Uri, iv: ImageView)
    fun loadIvNoCache(mContext: Context, url: String, iv: ImageView)
    fun loadIvNoCache(mContext: Context, uri: Uri, iv: ImageView)
    fun loadIvNoMemoryCache(mContext: Context, url: String, iv: ImageView)
    fun loadIvWithRoundDP(mContext: Context, url: String, iv: ImageView, defaultIv: Int, dp: Int)
    fun loadCircle(mContext: Context, url: String, iv: ImageView)
    fun loadGifIvOnceNoMemoryInChatRoom(mContext: Context, url: String, iv: ImageView, mOnAnimationStatus : OnAnimationStatus)
    fun loadGifIvOnceNoMemoryInChatRoom(mContext: Context, resId: Int, iv: ImageView, mOnAnimationStatus: OnAnimationStatus)
    fun loadGifForever(mContext: Context, url: String, iv: ImageView)
    fun loadGifForeverNoCacheInChatRoom(mContext: Context, drawableId: Int, iv: ImageView)
    fun clearImageAllCache(context: Context)
    fun getCacheSizeByte(context: Context): Long
    fun loadImageWithCustomTarget(context: Context, url: String, customTarget: CustomTarget<Bitmap>)
    fun loadBitmapWithCustomTarget(context: Context, url: String, customTarget: CustomTarget<Bitmap>)
    fun initChatRoomBackgroundWithDrawable(context: Context, url: String, view: View, resId: Int)
    fun updateChatRoomBackgroundWithDrawable(context: Context, url: String, view: View, resId: Int)
    fun load9PngWithResId(context: Context, url: String, view: View, resId: Int)
    fun loadIvWithRoundPX(mContext: Context, url: String, iv: ImageView, defaultIv: Int, px: Int)
    fun loadIvWithCircle(mContext: Context, url: String, iv: ImageView, defaultIv: Int)
    fun loadIvWithCircleBorder(mContext: Context, url: String, iv: ImageView, defaultIv: Int, borderColor: Int, borderWidth: Float)
    fun fixAvatarUrl(url: String, withPX: Int, heightPX: Int): String
    interface OnAnimationStatus {
        fun onAnimationStart()
        fun onAnimationEnd()
    }


}